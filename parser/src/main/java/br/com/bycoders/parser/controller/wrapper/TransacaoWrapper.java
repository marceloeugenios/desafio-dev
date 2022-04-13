package br.com.bycoders.parser.controller.wrapper;

import br.com.bycoders.parser.model.Transacao;
import br.com.bycoders.parser.util.TransacaoNatureza;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.bycoders.parser.util.TransacaoNatureza.*;

@Getter
public class TransacaoWrapper {

    private final List<Transacao> dados;
    private final Map<TransacaoNatureza, Double> extrato;
    @JsonIgnore
    private final double defaultValue = 0;

    public TransacaoWrapper(List<Transacao> transacoes) {
        dados = transacoes;
        if (Objects.nonNull(dados) && !dados.isEmpty()) {
            extrato = dados.stream()
                    .collect(Collectors.groupingBy(transacao -> transacao.getTransacaoTipo().getTransacaoNatureza(),
                            Collectors.summingDouble(Transacao::getValor)));
            extrato.put(SALDO, getSaldo());
        } else {
            extrato = new HashMap<>();
            extrato.put(ENTRADA, defaultValue);
            extrato.put(SAIDA, defaultValue);
            extrato.put(SALDO, defaultValue);
        }
    }

    @JsonIgnore
    private Double getSaldo() {
        double saldo = extrato.getOrDefault(ENTRADA, defaultValue) - extrato.getOrDefault(SAIDA, defaultValue);
        return Double.valueOf(new DecimalFormat("#####.##").format(saldo));

    }
}
