package br.com.bycoders.parser.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransacaoNatureza {

    ENTRADA("+"),
    SAIDA("-");

    private String sinal;
}
