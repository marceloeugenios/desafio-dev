package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.dto.ExtratoDto;
import br.com.bycoders.parser.model.Arquivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransacaoServico {

    Arquivo uploadTransacao(MultipartFile multipartFile);

    List<ExtratoDto> extratoPorLoja();

}
