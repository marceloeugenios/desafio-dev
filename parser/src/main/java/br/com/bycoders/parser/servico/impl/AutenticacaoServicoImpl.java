package br.com.bycoders.parser.servico.impl;

import br.com.bycoders.parser.dto.AutenticacaoDTO;
import br.com.bycoders.parser.error.NaoAutenticadoException;
import br.com.bycoders.parser.servico.AutenticacaoServico;
import br.com.bycoders.parser.util.Credencial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AutenticacaoServicoImpl implements AutenticacaoServico {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.token-uri}")
    private String keycloakTokenUri;

    @Value("${app.user-info-uri}")
    private String keycloakUserInfo;

    @Value("${app.logout}")
    private String keycloakLogout;

    @Value("${app.authorization-grant-type}")
    private String grantType;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${app.scope}")
    private String scope;


    @Override
    public AutenticacaoDTO login(Credencial credencial) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", grantType);
        map.add("client_secret", clientSecret);
        map.add("username", credencial.getUsuario());
        map.add("password", credencial.getSenha());

        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
            var auth = restTemplate.postForObject(keycloakTokenUri, request, AutenticacaoDTO.class);
            log.info("Retorno KeyCloak: {}", auth);
            return auth;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new NaoAutenticadoException("Problema na autenticação: " + e.getMessage());
        }
    }

    @Override
    public void logout(String refreshToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, null);
        restTemplate.postForObject(keycloakLogout, request, String.class);
    }
}
