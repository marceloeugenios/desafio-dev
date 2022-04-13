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
    @Column(name = "valor", nullable = false)
    private Double valor;
    @NotNull
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;
    @NotNull
    @Column(name = "cartao", length = 12, nullable = false)
    private String cartao;
    @NotNull
    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;
    @NotNull
    @Column(name = "loja_dono", length = 100, nullable = false)
    private String lojaDono;
    @NotNull
    @Column(name = "loja_nome", length = 100, nullable = false)
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
