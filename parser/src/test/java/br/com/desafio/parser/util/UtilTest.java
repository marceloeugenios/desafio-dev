package br.com.desafio.parser.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

    @Test
    @DisplayName("Quando CPF valido entao retorna sucesso")
    void whenCpfForValidoThenSucesso() {
        String cpf = "09620676017";
        assertTrue(Util.isCpfValido(cpf));
    }

    @Test
    @DisplayName("Quando CPF valido com formatacao entao retorna sucesso")
    void whenCpfForValidoComFormatacaoThenSucesso() {
        String cpf = "096.206.760-17";
        assertTrue(Util.isCpfValido(cpf));
    }

    @Test
    @DisplayName("Quando CPF invalido entao retorna erro validacao")
    void whenCpfForInvalidoThenRetornaFalse() {
        String cpf = "09620676019";
        assertFalse(Util.isCpfValido(cpf));
    }

    @Test
    @DisplayName("Quando CPF invalido com menos digitos entao retorna erro validacao")
    void whenCpfForComMenosDigitosThenRetornaFalse() {
        String cpf = "9620676017";
        assertFalse(Util.isCpfValido(cpf));
    }

    @Test
    @DisplayName("Quando CPF for apenas com digitos repetidos entao retorna erro validacao")
    void whenCpfForDigitosRepetidosThenNaoValido() {
        String cpf = "11111111111";
        assertFalse(Util.isCpfValido(cpf));
    }
}