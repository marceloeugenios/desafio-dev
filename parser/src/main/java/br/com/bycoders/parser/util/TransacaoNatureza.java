package br.com.bycoders.parser.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransacaoNatureza {

    SALDO,
    ENTRADA,
    SAIDA
}