package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.dto.ExtratoDTO;
import br.com.bycoders.parser.model.Arquivo;
import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.servico.TransacaoServico;
import br.com.bycoders.parser.servico.TransacaoTipoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static org.springframework.http.MediaType.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/transacao")
public class TransacaoController {

    private final TransacaoServico transacaoServico;
    private final TransacaoTipoServico transacaoTipoServico;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            operationId = "arquivoUpload",
            summary = "Faz a importação de arquivo txt no layout do **CNAB**",
            description = "Arquivo de exemplo e informações de sua estrutura pode ser encontrado em **https://github.com/marceloeugenios/desafio-dev**.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Importação realizada com sucesso - Transações cadastradas")
            }
    )
    @RolesAllowed("admin")
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Arquivo> postArquivoTransacao(@RequestParam("arquivo") MultipartFile multipartFile) {

        log.info("Fazendo upload: {}", multipartFile.getOriginalFilename());

        var arquivo = transacaoServico.uploadTransacao(multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(arquivo);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "lojasExtrato",
            summary = "Retorna o Extrato por loja com o saldo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Extrato retornado com sucesso")
            }
    )
    @RolesAllowed("user")
    @GetMapping(value = "/extrato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtratoDTO>> getExtratoPorLojas() {

        var extrato = transacaoServico.extratoPorLoja();

        return ResponseEntity.ok(extrato);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            operationId = "transacaoTipoCadastro",
            summary = "Cadastra Tipo de Transação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de Transação cadastrada com sucesso")
            }
    )
    @RolesAllowed("user")
    @PostMapping(value = "/tipos", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TransacaoTipo> postTransacaoTipo(@RequestBody TransacaoTipo transacaoTipo) {

        transacaoTipo = transacaoTipoServico.salva(transacaoTipo);

        return ResponseEntity.ok(transacaoTipo);

    }

    @Operation(
            operationId = "transacaoTiposObter",
            summary = "Retorna a lista de tipos de transações",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipos de Transações retornadas com sucesso")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tipos", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransacaoTipo>> getTransacaoTipos() {

        var tipos = transacaoTipoServico.buscaTodos();

        return ResponseEntity.ok(tipos);

    }
}
