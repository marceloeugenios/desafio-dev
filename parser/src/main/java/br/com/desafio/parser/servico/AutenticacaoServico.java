package br.com.desafio.parser.servico;

import br.com.desafio.parser.dto.AutenticacaoDto;
import br.com.desafio.parser.util.Credencial;

import java.util.Optional;

public interface AutenticacaoServico {

    Optional<AutenticacaoDto> login(Credencial credencial);

    void logout(String refreshToken) throws Exception;
}
