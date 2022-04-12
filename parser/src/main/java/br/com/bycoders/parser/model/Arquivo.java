package br.com.bycoders.parser.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
