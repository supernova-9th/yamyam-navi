package com.yamyamnavi.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.api.v1.request.UserChangePasswordRequest;
import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.request.UserUpdateLocationRequest;
import com.yamyamnavi.config.TestSecurityConfig;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserService;
import com.yamyamnavi.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    void 사용자_생성_테스트() throws Exception {
        UserCreateRequest request = new UserCreateRequest("test@example.com", "Password123", 37.5665, 126.9780);
        User user = User.builder().email("test@example.com").build();

        when(userConverter.convertToUser(any(UserCreateRequest.class))).thenReturn(user);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void 비밀번호_변경_테스트() throws Exception {
        UserChangePasswordRequest request = new UserChangePasswordRequest("newPassword123");
        User user = User.builder().email("test@example.com").build();

        when(userService.changePassword(any(), any())).thenReturn(user);

        mockMvc.perform(put("/api/v1/users/me/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void 위치_정보_업데이트_테스트() throws Exception {
        UserUpdateLocationRequest request = new UserUpdateLocationRequest("서울시 동작구");
        User user = User.builder().email("test@example.com").build();

        when(userService.updateLocation(any(), any())).thenReturn(user);

        mockMvc.perform(put("/api/v1/users/me/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}