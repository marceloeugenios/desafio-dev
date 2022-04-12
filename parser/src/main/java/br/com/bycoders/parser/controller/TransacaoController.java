package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.servico.TransacaoServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/transacao")
public class TransacaoController {

    private final TransacaoServico transacaoTipoServico;

    @ResponseStatus
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<?> postArquivoTransacao(@RequestParam("arquivo")MultipartFile arquivo) {

        log.info("Fazendo upload: {}", arquivo.getOriginalFilename());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tipos", produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<List<TransacaoTipo>> getTiposTransacoes() {

        var all = transacaoTipoServico.findAll();

        return ResponseEntity.ok(all);

    }
}
