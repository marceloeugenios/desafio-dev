package br.com.bycoders.parser.servico.impl;

import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.repository.ArquivoRepository;
import br.com.bycoders.parser.repository.TransacaoRepository;
import br.com.bycoders.parser.repository.TransacaoTipoRepository;
import br.com.bycoders.parser.servico.TransacaoServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        return null;
    }

    @Override
    @Transactional
    public TransacaoTipo salvar(TransacaoTipo transacaoTipo) {
        return transacaoTipoRepository.save(transacaoTipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransacaoTipo> findAll() {
        return transacaoTipoRepository.findAll();
    }
}
