package br.com.bycoders.parser.model;

import br.com.bycoders.parser.util.TransacaoNatureza;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transacao_tipo")
public class TransacaoTipo {

    @Id
    private Integer id;
    @Column(length = 200)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "transacao_natureza", length = 50)
    private TransacaoNatureza transacaoNatureza;

    public TransacaoTipo(Integer id) {
        this.id = id;
    }
}