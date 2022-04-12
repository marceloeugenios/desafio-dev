package br.com.bycoders.parser.util;

import br.com.bycoders.parser.model.Transacao;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ParserUtil {

    public List<Transacao> parseFile(MultipartFile multipartFile) {

        // logica para parsear o arquivo

        return new ArrayList<>();
    }
}
