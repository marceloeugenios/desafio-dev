package br.com.desafio.parser.servico;

import br.com.desafio.parser.dto.ExtratoDto;
import br.com.desafio.parser.model.Arquivo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransacaoServico {

    Arquivo uploadTransacao(MultipartFile multipartFile);

    List<ExtratoDto> extratoPorLoja();

}
