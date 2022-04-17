package br.com.bycoders.parser.servico.impl;

import br.com.bycoders.parser.dto.ExtratoDto;
import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.Transacao;
import br.com.bycoders.parser.repository.ArquivoRepository;
import br.com.bycoders.parser.repository.TransacaoRepository;
import br.com.bycoders.parser.servico.TransacaoServico;
import br.com.bycoders.parser.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoServicoImpl implements TransacaoServico {

    private final ArquivoRepository arquivoRepository;
    private final TransacaoRepository transacaoRepository;

    @Override
    @Transactional
    public Arquivo uploadTransacao(MultipartFile multipartFile) {

        List<Transacao> transacoes = ParserUtil.parseFile(multipartFile);

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
}
