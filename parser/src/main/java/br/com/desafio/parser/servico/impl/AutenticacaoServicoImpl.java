package br.com.desafio.parser.servico.impl;

import br.com.desafio.parser.dto.AutenticacaoDto;
import br.com.desafio.parser.error.NaoAutenticadoException;
import br.com.desafio.parser.servico.AutenticacaoServico;
import br.com.desafio.parser.util.Credencial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class AutenticacaoServicoImpl implements AutenticacaoServico {

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REFRESH_TOKEN = "refresh_token";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * KeyCloak variables
     */
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    /**
     * Aux app variables
     */
    @Value("${app.logout}")
    private String keycloakLogout;
    @Value("${app.token-uri}")
    private String keycloakTokenUri;
    @Value("${app.user-info-uri}")
    private String keycloakUserInfo;
    @Value("${app.authorization-grant-type}")
    private String grantType;
    @Value("${app.scope}")
    private String scope;

    @Override
    public Optional<AutenticacaoDto> login(Credencial credencial) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(CLIENT_ID, clientId);
        map.add(CLIENT_SECRET, clientSecret);
        map.add("grant_type", grantType);
        map.add("username", credencial.getUsuario());
        map.add("password", credencial.getSenha());

        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
            var authResponse = restTemplate.exchange(keycloakTokenUri, HttpMethod.POST, request,
                    new ParameterizedTypeReference<AutenticacaoDto>() {
                    });
            return Optional.ofNullable(authResponse.getBody());
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new NaoAutenticadoException("Problema na autenticação: " + e.getMessage());
        }
    }

    @Override
    public void logout(String refreshToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(CLIENT_ID, clientId);
        map.add(CLIENT_SECRET, clientSecret);
        map.add(REFRESH_TOKEN, refreshToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
        restTemplate.exchange(keycloakLogout, HttpMethod.POST, request,
                new ParameterizedTypeReference<String>() {
                });
    }
}
