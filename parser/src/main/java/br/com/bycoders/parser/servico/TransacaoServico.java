package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.dto.TransacaoDTO;
import br.com.bycoders.parser.model.Arquivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransacaoServico {

    Arquivo uploadTransacao(MultipartFile multipartFile);

    List<TransacaoDTO> exratoPorLojas();

}
