package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.dto.AutenticacaoDTO;
import br.com.bycoders.parser.servico.AutenticacaoServico;
import br.com.bycoders.parser.util.Credencial;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoServico autenticacaoServico;

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "login",
            summary = "Faz a autenticacao do usuário na aplicação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso")
            }
    )
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AutenticacaoDTO> login(@Valid @RequestBody Credencial credencial) {

        var token =  autenticacaoServico.login(credencial);

        return ResponseEntity.ok(token);

    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "logout",
            summary = "Faz o logout do usuário na aplicação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso")
            }
    )
    @PostMapping(value = "/logout", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout(@RequestParam(name = "refresh_token") String refreshToken) throws Exception {

        autenticacaoServico.logout(refreshToken);

        return ResponseEntity.ok("{\"mensagem\": \"Logout realizado com sucesso!\"}");

    }
}
