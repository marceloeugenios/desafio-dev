package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.TransacaoTipo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransacaoServico {

    Arquivo uploadTransacao(MultipartFile multipartFile);

    TransacaoTipo salvarTransacaoTipo(TransacaoTipo transacaoTipo);

    List<TransacaoTipo> buscarTodosTransacaoTipo();

}
