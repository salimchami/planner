package io.edukativ.myskoolin.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.edukativ.myskoolin.front.web.rest.vm.LoginVM;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EndPointCaller {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EndPointCaller(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions authenticateAndPerform(String nni, String password, MockHttpServletRequestBuilder request) throws Exception {
        String idToken = login(nni, password)
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        JwtAuthenticationDTO jwtAuthenticationDTO = objectMapper.readValue(idToken, JwtAuthenticationDTO.class);
        String bearerToken = jwtAuthenticationDTO.getId_token();

        return mockMvc.perform(request
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken));
    }

    public ResultActions login(String username, String password) throws Exception {
        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(username);
        loginVM.setPassword(password);
        loginVM.setRememberMe(true);
        final ObjectMapper objectMapper = new ObjectMapper();
        return mockMvc.perform(post("/api/authenticate")
            .content(objectMapper.writeValueAsString(loginVM))
            .contentType(MediaType.APPLICATION_JSON)
        );
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class JwtAuthenticationDTO {
        private String id_token;

        public String getId_token() {
            return id_token;
        }

        public void setId_token(String idToken) {
            this.id_token = idToken;
        }
    }

}
