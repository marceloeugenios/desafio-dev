package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.dto.AutenticacaoDto;
import br.com.bycoders.parser.util.Credencial;

import java.util.Optional;

public interface AutenticacaoServico {

    Optional<AutenticacaoDto> login(Credencial credencial);

    void logout(String refreshToken) throws Exception;
}
