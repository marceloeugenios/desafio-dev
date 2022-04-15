package br.com.bycoders.parser.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    @DisplayName("Quando CPF valido entao retorna true")
    void whenCpfForValidoThenSucesso() {
        String cpfValido = "09620676017";
        assertTrue(Util.isCpfValido(cpfValido));
    }

    @Test
    @DisplayName("Quando CPF valido com formatacao entao retorna true")
    void whenCpfForValidoComFormatacaoThenSucesso() {
        String cpfValido = "096.206.760-17";
        assertTrue(Util.isCpfValido(cpfValido));
    }

    @Test
    @DisplayName("Quando CPF invalido entao retorna false")
    void whenCpfForInvalidoThenRetornaFalse() {
        String cpfValido = "09620676019";
        assertFalse(Util.isCpfValido(cpfValido));
    }

    @Test
    @DisplayName("Quando CPF invalido com menos digitos entao retorna false")
    void whenCpfForComMenosDigitosThenRetornaFalse() {
        String cpfValido = "9620676017";
        assertFalse(Util.isCpfValido(cpfValido));
    }

    @Test
    @DisplayName("Quando CPF for apenas com digitos repetidos entao retorna false")
    void whenCpfForDigitosRepetidosThenNaoValido() {
        String cpfValido = "11111111111";
        assertFalse(Util.isCpfValido(cpfValido));
    }
}