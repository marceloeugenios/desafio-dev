package br.com.bycoders.parser.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Table
@Entity
@NoArgsConstructor
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Schema(description = "Nome do arquivo", maxLength = 200, required = true)
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;
    @NotNull
    @Schema(description = "Data em que o arquivo foi carregado", required = true)
    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;
    @NotNull
    @Schema(description = "Identificador do usuario que fez a carga do arquivo", required = true)
    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    public Arquivo(String nome, UUID usuarioId) {
        this.dataUpload = LocalDateTime.now();
        this.nome = nome;
        this.usuarioId = usuarioId;
    }
}
