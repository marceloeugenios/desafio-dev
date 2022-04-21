package br.com.desafio.parser.servico;

import br.com.desafio.parser.model.TransacaoTipo;

import java.util.List;

public interface TransacaoTipoServico {

    TransacaoTipo salva(TransacaoTipo transacaoTipo);

    List<TransacaoTipo> buscaTodos();

}
