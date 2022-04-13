package br.com.bycoders.parser.dto;

import lombok.Getter;

import java.text.DecimalFormat;
import java.util.Optional;

@Getter
public class TransacaoDTO {

    private final String lojaNome;
    private final Double entrada;
    private final Double saida;

    public TransacaoDTO(String lojaNome, Double entrada, Double saida) {
        this.lojaNome = lojaNome;
        this.entrada = Optional.ofNullable(entrada).orElse(0d);
        this.saida = Optional.ofNullable(saida).orElse(0d);
    }

    public Double getSaldo() {
        double saldo = this.entrada - this.saida;
        return Double.valueOf(new DecimalFormat("#####.##").format(saldo));
    }
}