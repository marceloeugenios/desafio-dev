package br.com.bycoders.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Table
@Entity
@Builder
@ToString
@EqualsAndHashCode
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Double valor;
    @NotNull
    @Column(name = "cpf", length = 11)
    private String cpf;
    @NotNull
    @Column(name = "cartao", length = 12)
    private String cartao;
    @NotNull
    private LocalDateTime dataTransacao;
    @NotNull
    @Column(name = "loja_dono", length = 100)
    private String lojaDono;
    @NotNull
    @Column(name = "loja_nome", length = 100)
    private String lojaNome;
    @NonNull
    @JoinColumn(name = "_transacao_tipo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TransacaoTipo transacaoTipo;
    @Setter
    @JsonIgnore
    @JoinColumn(name = "_arquivo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Arquivo arquivo;

    @Tolerate
    public Transacao() {

    }
}
