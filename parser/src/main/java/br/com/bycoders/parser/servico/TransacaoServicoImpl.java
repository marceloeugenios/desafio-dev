package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.Transacao;
import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.repository.ArquivoRepository;
import br.com.bycoders.parser.repository.TransacaoRepository;
import br.com.bycoders.parser.repository.TransacaoTipoRepository;
import br.com.bycoders.parser.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoServicoImpl implements TransacaoServico {

    private final ArquivoRepository arquivoRepository;
    private final TransacaoRepository transacaoRepository;
    private final TransacaoTipoRepository transacaoTipoRepository;

    @Override
    @Transactional
    public Arquivo uploadTransacao(MultipartFile multipartFile) {

        List<Transacao> transacoes = ParserUtil.parseFile(multipartFile);

        Arquivo arquivo = new Arquivo();
        arquivo.setDataUpload(LocalDateTime.now());
        arquivo.setNome(multipartFile.getOriginalFilename());
        arquivo.setUsuarioId(UUID.randomUUID());

        var savedArquivo = arquivoRepository.save(arquivo);

        var todos = transacoes.stream()
                   .peek(transacao -> transacao.configurarArquivo(arquivo)).collect(Collectors.toList());

        transacaoRepository.saveAll(todos);

        return savedArquivo;
    }

    @Override
    @Transactional
    public TransacaoTipo salvarTransacaoTipo(TransacaoTipo transacaoTipo) {
        return transacaoTipoRepository.save(transacaoTipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransacaoTipo> buscarTodosTransacaoTipo() {
        return transacaoTipoRepository.findAll();
    }
}
