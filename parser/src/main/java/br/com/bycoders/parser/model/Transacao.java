package br.com.bycoders.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Schema(description = "Valor da Transação", required = true, format = "0000.00")
    @Column(name = "valor", nullable = false)
    private Double valor;
    @NotEmpty
    @Max(value = 11, message = "CPF deve conter no máximo 11 dígitos")
    @Schema(description = "CPF da reponsável pela transação", maxLength = 11, required = true)
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;
    @NotEmpty
    @Schema(description = "Cartão da transação", maxLength = 12, required = true)
    @Column(name = "cartao", length = 12, nullable = false)
    private String cartao;
    @NotNull
    @Schema(description = "Data em que a transação foi realizada - Formato 24h", format = "dd-MM-yyyy hh:mm:ss", required = true)
    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;
    @NotEmpty
    @Schema(description = "Nome do dono da loja", maxLength = 100, required = true)
    @Column(name = "loja_dono", length = 100, nullable = false)
    private String lojaDono;
    @NotEmpty
    @Schema(description = "Nome da loja", maxLength = 100, required = true)
    @Column(name = "loja_nome", length = 100, nullable = false)
    private String lojaNome;
    @NonNull
    @Schema(description = "Determina se a Natureza da Transação é de ENTRADA ou SAÍDA. " +
            "SALDO é utilizado no extrato apenas (não válido para transações)")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_transacao_tipo", nullable = false)
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
