package br.com.desafio.parser.servico.impl;

import br.com.desafio.parser.dto.ExtratoDto;
import br.com.desafio.parser.model.Arquivo;
import br.com.desafio.parser.model.Transacao;
import br.com.desafio.parser.model.TransacaoTipo;
import br.com.desafio.parser.repository.ArquivoRepository;
import br.com.desafio.parser.repository.TransacaoRepository;
import br.com.desafio.parser.servico.TransacaoServico;
import br.com.desafio.parser.servico.TransacaoTipoServico;
import br.com.desafio.parser.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoServicoImpl implements TransacaoServico {

    private final ArquivoRepository arquivoRepository;
    private final TransacaoRepository transacaoRepository;

    private final TransacaoTipoServico transacaoTipoServico;

    @Override
    @Transactional
    public Arquivo uploadTransacao(MultipartFile multipartFile) {

        List<Transacao> transacoes = ParserUtil.parseFile(multipartFile);

        validarTiposTransacoes(transacoes);

        var arquivo = new Arquivo(multipartFile.getOriginalFilename(), UUID.randomUUID());

        var arquivoSalvo = arquivoRepository.save(arquivo);

        transacoes = transacoes.stream()
                .peek(transacao -> transacao.setArquivo(arquivo))
                .collect(Collectors.toList());

        transacaoRepository.saveAll(transacoes);

        return arquivoSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExtratoDto> extratoPorLoja() {
        return transacaoRepository.extratoAgrupadoPorLoja();
    }

    private void validarTiposTransacoes(List<Transacao> transacoes) {
        var tiposTransacoesValidas = transacaoTipoServico.buscaTodos()
                .stream()
                .map(TransacaoTipo::getId)
                .collect(Collectors.toList());

        var transacoesComTiposInvalidos = transacoes.stream()
                .filter(transacao -> !tiposTransacoesValidas.contains(transacao.getTransacaoTipo().getId()))
                .map(Transacao::getTransacaoTipo)
                .map(TransacaoTipo::getId)
                .collect(Collectors.toSet());

        if (!transacoesComTiposInvalidos.isEmpty()) {
            var tiposInvalidos = transacoesComTiposInvalidos
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            var erro = format("Tipos de Transações inválidas: %s", tiposInvalidos);

            log.error("{}", erro);
            throw new IllegalArgumentException(erro);
        }
    }
}
