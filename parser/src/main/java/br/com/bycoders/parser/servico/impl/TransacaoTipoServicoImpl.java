package br.com.bycoders.parser.servico.impl;

import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.repository.TransacaoTipoRepository;
import br.com.bycoders.parser.servico.TransacaoTipoServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoTipoServicoImpl implements TransacaoTipoServico {

    private final TransacaoTipoRepository transacaoTipoRepository;

    @Override
    @Transactional
    public TransacaoTipo salvar(TransacaoTipo transacaoTipo) {
        return transacaoTipoRepository.save(transacaoTipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransacaoTipo> buscarTodos() {
        return transacaoTipoRepository.findAll();
    }

}
