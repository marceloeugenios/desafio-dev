package br.com.bycoders.parser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Schema(name = "Extrato")
public class ExtratoDTO {

    @Schema(description = "Nome da Loja", example = "Loja Desafio Dev")
    private String loja;
    @Schema(description = "Valor total de entrada em reais", defaultValue = "0", example = "200.0")
    private Double entrada;
    @Schema(description = "Valor total de saída em reais", defaultValue = "0", example = "150.0")
    private Double saida;
    @Getter(AccessLevel.NONE)
    @Schema(description = "Saldo total em reais. Cálculo: ('valor de entrada' - 'valor de saída')", defaultValue = "0", example = "50.0")
    private Double saldo;

    public ExtratoDTO(String lojaNome, Double entrada, Double saida) {
        this.loja = lojaNome;
        this.entrada = Optional.ofNullable(entrada).orElse(0d);
        this.saida = Optional.ofNullable(saida).orElse(0d);
    }

    public Double getSaldo() {
        double saldo = this.entrada - this.saida;
        return Double.valueOf(new DecimalFormat("#####.##").format(saldo));
    }
}