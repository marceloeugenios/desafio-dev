package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.dto.AutenticacaoDTO;
import br.com.bycoders.parser.util.Credencial;

import java.util.Optional;

public interface AutenticacaoServico {

    Optional<AutenticacaoDTO> login(Credencial credencial);

    void logout(String refreshToken) throws Exception;
}
