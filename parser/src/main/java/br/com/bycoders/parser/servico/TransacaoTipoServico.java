package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.model.TransacaoTipo;

import java.util.List;

public interface TransacaoTipoServico {

    TransacaoTipo salva(TransacaoTipo transacaoTipo);

    List<TransacaoTipo> buscaTodos();

}
