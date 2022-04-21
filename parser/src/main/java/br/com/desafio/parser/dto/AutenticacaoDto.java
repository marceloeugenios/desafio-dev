package br.com.desafio.parser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AutenticacaoDto {

    @JsonProperty("access_token")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String accessToken;
    @JsonProperty("expires_in")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long expiresIn;
    @JsonProperty("refresh_expires_in")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long refreshExpiresIn;
    @JsonProperty("refresh_token")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String refreshToken;
    @JsonProperty("token_type")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String tokenType;
    @JsonProperty("not-before-policy")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String notBeforePolicy;
    @JsonProperty("session_state")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String sessionState;
    @JsonProperty("scope")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String scope;

}
