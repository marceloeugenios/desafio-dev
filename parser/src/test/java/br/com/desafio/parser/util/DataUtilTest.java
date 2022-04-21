package br.com.desafio.parser.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataUtilTest {

    @Test
    void whenDataStringCorretaThenFormataPadrao() {
        String dataHoraString = "20200101 000223";
        String dataHoraEsperada = "2020-01-01T00:02:23";
        var dataFormatada = DataUtil.fromStringToLocalDateTime(dataHoraString);
        assertEquals(dataHoraEsperada, dataFormatada.toString());
    }
}