package br.com.desafio.parser.servico.impl;

import br.com.desafio.parser.model.TransacaoTipo;
import br.com.desafio.parser.repository.TransacaoTipoRepository;
import br.com.desafio.parser.servico.TransacaoTipoServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.desafio.parser.util.TransacaoNatureza.SALDO;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoTipoServicoImpl implements TransacaoTipoServico {

    private final TransacaoTipoRepository transacaoTipoRepository;

    @Override
    @Transactional
    public TransacaoTipo salva(TransacaoTipo transacaoTipo) {
        if (SALDO.equals(transacaoTipo.getTransacaoNatureza())) {
            throw new IllegalArgumentException("Natureza da transação inválida!");
        }
        return transacaoTipoRepository.save(transacaoTipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransacaoTipo> buscaTodos() {
        return transacaoTipoRepository.findAll();
    }

}
