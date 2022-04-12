package br.com.bycoders.parser.model;

import br.com.bycoders.parser.util.TransacaoNatureza;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transacao_tipo")
public class TransacaoTipo {

    @Id
    private Integer id;
    @Column(length = 200)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "transacao_natureza")
    private TransacaoNatureza transacaoNatureza;

}