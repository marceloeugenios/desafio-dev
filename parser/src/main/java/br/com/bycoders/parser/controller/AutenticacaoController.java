package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.dto.AutenticacaoDto;
import br.com.bycoders.parser.error.NaoAutenticadoException;
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
                    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
            }
    )
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AutenticacaoDto> login(@Valid @RequestBody Credencial credencial) {

        log.info("Fazendo login: {}", credencial.getUsuario());

        var token =  autenticacaoServico.login(credencial);

        return token.map(ResponseEntity::ok)
                .orElseThrow(() -> new NaoAutenticadoException("Usuário/Senha não encontrado!"));

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

        log.info("Fazendo logout: {}", refreshToken);

        autenticacaoServico.logout(refreshToken);

        log.info("Logoff realizado com sucesso");

        return ResponseEntity.ok("{\"mensagem\": \"Logout realizado com sucesso!\"}");

    }
}
