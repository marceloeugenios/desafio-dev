package br.com.bycoders.parser.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserUtilTest {

    private static final String CNAB_DIRETORIO = "src/test/resources/arquivos/";

    @Test
    @DisplayName("Processa o arquivo CNAB com sucesso")
    public void deveProcessarArquivoComSucesso() throws IOException {
        var quantidadeTransacoes = 21;
        var arquivo = CNAB_DIRETORIO + "cnab-correto.txt";
        var multipartFile = new MockMultipartFile("arquivo", arquivo,
                "multipart/form-data", getConteudoCnab(arquivo));

        assertEquals(quantidadeTransacoes, ParserUtil.parseFile(multipartFile).size());
    }

    @Test
    @DisplayName("Deve lancar exception por extensao invalida")
    public void deveLancarExceptionPorExtensaoInvalida() throws IOException {
        var arquivo = CNAB_DIRETORIO + "cnab-extensao-errada.csv";
        var multipartFile = new MockMultipartFile("arquivo", arquivo,
                "multipart/form-data", getConteudoCnab(arquivo));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseFile(multipartFile));

        assertTrue(exception.getMessage().contains("Extensao de arquivo invalida!"));

    }

    private static byte[] getConteudoCnab(String fileName) throws IOException {
        var file = new File(fileName);
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
        return bytes;
    }
}