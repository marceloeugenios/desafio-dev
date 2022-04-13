package br.com.bycoders.parser.model;

import br.com.bycoders.parser.util.TransacaoNatureza;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transacao_tipo")
public class TransacaoTipo {

    @Id
    private Integer id;
    @NotNull
    @Column(length = 200, nullable = false)
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transacao_natureza", length = 50, nullable = false)
    private TransacaoNatureza transacaoNatureza;

    public TransacaoTipo(Integer id) {
        this.id = id;
    }
}