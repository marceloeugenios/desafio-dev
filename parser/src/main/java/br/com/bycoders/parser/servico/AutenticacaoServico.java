package br.com.bycoders.parser.servico;

import br.com.bycoders.parser.dto.AutenticacaoDTO;
import br.com.bycoders.parser.util.Credencial;

public interface AutenticacaoServico {

    AutenticacaoDTO login(Credencial credencial);

    void logout(String refreshToken) throws Exception;
}
