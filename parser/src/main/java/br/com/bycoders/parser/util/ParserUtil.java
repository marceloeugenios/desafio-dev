package br.com.bycoders.parser.util;

import br.com.bycoders.parser.model.Transacao;
import br.com.bycoders.parser.model.TransacaoTipo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class ParserUtil {

    public List<Transacao> parseFile(MultipartFile multipartFile) {
        if (Objects.isNull(multipartFile) || Objects.isNull(multipartFile.getOriginalFilename())) {
            throw new IllegalArgumentException("Arquivo invalido!");
        }
        if (!multipartFile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("Extensao de arquivo invalida!");
        }
        try (var inputStream = multipartFile.getInputStream()) {
            var linhas = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            if (linhas.isEmpty()) {
                throw new IllegalArgumentException("Arquivo vazio!");
            }
            return linhas.stream()
                    .map(ParserUtil::parseToTransacao)
                    .collect(Collectors.toList());
        } catch (IOException ioex) {
            log.error("Erro no parser do arquivo: ", ioex);
            throw new IllegalArgumentException(ioex.getMessage());
        }
    }

    private Transacao parseToTransacao(String linha) {
        log.info("{}", linha);
        var tipo = Integer.parseInt(linha.substring(0, 1));
        var dataTransacao = linha.substring(1, 9);
        var valor = Double.parseDouble(linha.substring(9, 19)) / 100;
        var cpf = linha.substring(19, 30);
        var cartao = linha.substring(30, 42);
        var horaTransacao = linha.substring(42, 48);
        var donoLoja = linha.substring(48, 62).trim();
        var nomeLoja = linha.substring(62).trim();

        var dataHoraCompleta = dataTransacao + " " + horaTransacao;
        var dateTime = DataUtil.fromStringToLocalDateTime(dataHoraCompleta);
        return Transacao.builder()
                .transacaoTipo(new TransacaoTipo(tipo))
                .dataTransacao(dateTime)
                .cartao(cartao)
                .cpf(cpf)
                .lojaDono(donoLoja)
                .lojaNome(nomeLoja)
                .valor(valor)
                .build();
    }
}
