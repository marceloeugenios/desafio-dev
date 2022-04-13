package br.com.bycoders.parser.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", length = 200)
    private String nome;
    @Column(name = "data_upload")
    private LocalDateTime dataUpload;
    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

}
