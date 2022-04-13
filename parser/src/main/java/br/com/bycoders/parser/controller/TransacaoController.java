package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.dto.TransacaoDTO;
import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.servico.TransacaoServico;
import br.com.bycoders.parser.servico.TransacaoTipoServico;
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

    private final TransacaoServico transacaoServico;
    private final TransacaoTipoServico transacaoTipoServico;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<Arquivo> postArquivoTransacao(@RequestParam("arquivo") MultipartFile multipartFile) {

        log.info("Fazendo upload: {}", multipartFile.getOriginalFilename());

        var arquivo = transacaoServico.uploadTransacao(multipartFile);

        return ResponseEntity.ok(arquivo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/extrato", produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<List<TransacaoDTO>> getExtratoPorLojas() {

        var extrato = transacaoServico.extratoPorLoja();

        return ResponseEntity.ok(extrato);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/tipos", produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<TransacaoTipo> postTransacaoTipo(@RequestBody TransacaoTipo transacaoTipo) {

        transacaoTipo = transacaoTipoServico.salvar(transacaoTipo);

        return ResponseEntity.ok(transacaoTipo);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tipos", produces = APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<List<TransacaoTipo>> getTransacaoTipos() {

        var tipos = transacaoTipoServico.buscarTodos();

        return ResponseEntity.ok(tipos);

    }
}
