package br.com.desafio.parser.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserUtilTest {

    @Test
    @DisplayName("Processa o arquivo CNAB com sucesso")
    public void deveProcessarArquivoComSucesso() throws IOException {
        var quantidadeTransacoes = 21;
        var arquivo = Constantes.ARQUIVO_ORIGINAL;
        var multipartFile = new MockMultipartFile("arquivo", arquivo, "multipart/form-data", Util.getConteudoCnab(arquivo));

        Assertions.assertEquals(quantidadeTransacoes, ParserUtil.parseFile(multipartFile).size());
    }

    @Test
    @DisplayName("Deve lancar exception por extensao invalida")
    public void deveLancarExceptionPorExtensaoInvalida() throws IOException {
        var arquivo = Constantes.CNAB_DIRETORIO + "cnab-extensao-errada.csv";
        var multipartFile = new MockMultipartFile("arquivo", arquivo,
                "multipart/form-data", Util.getConteudoCnab(arquivo));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ParserUtil.parseFile(multipartFile));

        assertTrue(exception.getMessage().contains("Extensao de arquivo invalida!"));

    }
}