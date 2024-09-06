package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(TokenController.class)
@Import(TestSecurityConfig.class)
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenIssuer tokenIssuer;

    @Test
    public void 토큰_재발급_요청이_성공적으로_처리된다() throws Exception {
        TokenResponse mockResponse = new TokenResponse("new_access_token", null);
        when(tokenIssuer.reissueAccessToken(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/tokens/reissue")
                        .with(csrf())
                        .header("Authorization", "Bearer refresh_token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}