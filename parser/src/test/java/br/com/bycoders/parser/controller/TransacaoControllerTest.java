package br.com.bycoders.parser.controller;

import br.com.bycoders.parser.dto.ExtratoDto;
import br.com.bycoders.parser.model.TransacaoTipo;
import br.com.bycoders.parser.util.Constantes;
import br.com.bycoders.parser.util.TransacaoNatureza;
import br.com.bycoders.parser.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(value = "desafio", roles = {"admin", "user"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
class TransacaoControllerTest {

    private static final String BASE_URL = "/api/v1/transacao";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Arquivo original importado com sucesso")
    void whenArquivoInformadoForValidoThenImportaComSucesso() throws Exception {

        importarArquivo(Constantes.ARQUIVO_ORIGINAL);

    }

    @Test
    @DisplayName("Arquivo com mais transacoes importado com sucesso")
    void whenArquivoInformadoForValidoComMaisTransacoesThenImportaComSucesso() throws Exception {

        importarArquivo(Constantes.ARQUIVO_MAIS_TRANSACOES);

    }

    @Test
    @DisplayName("Arquivo invalido retorna erro de validacao")
    void whenArquivoInformadoForVazioThenRetornaBadRequest() throws Exception {

        importarArquivoBadRequest(Constantes.ARQUIVO_VAZIO);

    }

    @Test
    @DisplayName("Arquivo com CPF 09620676019 invalido retorna erro de validacao")
    void whenArquivoInformadoConterAlgumCpfInvalidoThenRetornaBadRequest() throws Exception {

        importarArquivoBadRequest(Constantes.ARQUIVO_COM_CPFS_INVALIDOS);

    }

    @Test
    @DisplayName("Arquivo com tipo de transação '0' invalido retorna erro de validacao")
    void whenArquivoInformadoConterTipoTransacaoInvalidoThenRetornaBadRequest() throws Exception {

        importarArquivoBadRequest(Constantes.ARQUIVO_COM_TIPOS_TRANSACOES_INVALIDAS);

    }

    @Test
    @DisplayName("Arquivo com cartao invalido retorna erro de validacao")
    void whenArquivoInformadoConterCartaoInvalidoThenRetornaBadRequest() throws Exception {

        importarArquivoBadRequest(Constantes.ARQUIVO_COM_CARTAO_INVALIDOS);

    }

    @Test
    @DisplayName("Arquivo com data/horario invalido retorna erro de validacao")
    void whenArquivoInformadoConterDataHorarioThenRetornaBadRequest() throws Exception {

        importarArquivoBadRequest(Constantes.ARQUIVO_COM_DATAS_INVALIDAS);

    }

    @Test
    @DisplayName("Arquivo inexistente na request retorna erro de validacao")
    void whenNaoTiverArquivoNaRequisicaoThenRetornaBadRequest() throws Exception {

        importarArquivoNullBadRequest();

    }

    @Test
    @DisplayName("Retorna extrato quando tiver transacoes importadas")
    void whenExtratoForGeradoComRegistrosNaBaseDeDadosThenRetornaResultado() throws Exception {

        importarArquivo(Constantes.ARQUIVO_ORIGINAL);

        String url = String.format("%s/extrato", BASE_URL);

        String response = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        var extrato = objectMapper.readValue(response, new TypeReference<List<ExtratoDto>>() {
        });

        assertNotNull(extrato);
        assertEquals(5, extrato.size());
    }

    @Test
    @DisplayName("Cadastro novo tipo de transacao quando os dados informados forem validos")
    void whenDadosInformadosParaTipoDeTransacaoForemValidosThenCadastraComSucesso() throws Exception {

        String url = String.format("%s/tipos", BASE_URL);

        TransacaoTipo tp = new TransacaoTipo();
        tp.setId(100);
        tp.setDescricao("NOVO TIPO");
        tp.setTransacaoNatureza(TransacaoNatureza.SAIDA);

        String response = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(tp)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        var tipo = objectMapper.readValue(response, new TypeReference<TransacaoTipo>() {
        });

        assertNotNull(tipo);
        assertEquals(100, tp.getId());
    }

    @Test
    @DisplayName("Nao cadastro novo tipo de transacao quando os dados informados forem invalidos")
    void whenDadosInformadosParaTipoDeTransacaoForemInvalidosThenRetornaBadRequest() throws Exception {

        String url = String.format("%s/tipos", BASE_URL);

        TransacaoTipo tp = new TransacaoTipo();
        tp.setId(100);
        tp.setDescricao("NOVO TIPO INVALIDO");
        tp.setTransacaoNatureza(TransacaoNatureza.SALDO);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(tp)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Retorna tipos de transacoes quando os dados estiverem cadastrados")
    void whenTiposDeTransacoesExistiremThenRetornaComSucesso() throws Exception {

        String url = String.format("%s/tipos", BASE_URL);

        String response = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        var transacaoTipos = objectMapper.readValue(response, new TypeReference<List<TransacaoTipo>>() {
        });

        assertNotNull(transacaoTipos);
        assertTrue(transacaoTipos.size() >= 9);
    }

    /**
     * Metodos auxiliares para evitar duplicacao
     */

    private void importarArquivo(String nomeArquivo) throws Exception {
        MockMultipartFile multipartFile = getMockedFile(nomeArquivo);

        String url = String.format("%s/upload", BASE_URL);

        mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    private void importarArquivoBadRequest(String nomeArquivo) throws Exception {
        MockMultipartFile multipartFile = getMockedFile(nomeArquivo);

        String url = String.format("%s/upload", BASE_URL);

        mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private void importarArquivoNullBadRequest() throws Exception {
        var multipartFile = new MockMultipartFile("arquivo", new byte[]{});
        String url = String.format("%s/upload", BASE_URL);
        mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private MockMultipartFile getMockedFile(String nomeArquivo) throws IOException {
        return new MockMultipartFile("arquivo", nomeArquivo, "multipart/form-data",
                Util.getConteudoCnab(nomeArquivo));
    }
}