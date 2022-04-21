package br.com.desafio.parser.model;

import br.com.desafio.parser.util.TransacaoNatureza;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Descrição do Tipo da Transação")
    @Column(length = 200, nullable = false)
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Natureza da Transação")
    @Column(name = "transacao_natureza", length = 50, nullable = false)
    private TransacaoNatureza transacaoNatureza;

    public TransacaoTipo(Integer id) {
        this.id = id;
    }
}