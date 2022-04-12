package br.com.bycoders.parser.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @Column(name = "cpf", length = 11)
    private String cpf;
    @Column(name = "cartao", length = 12)
    private String cartao;
    private LocalDateTime dataTransacao;
    @Column(name = "loja_dono", length = 100)
    private String lojaDono;
    @Column(name = "loja_nome", length = 100)
    private String lojaNome;

}
