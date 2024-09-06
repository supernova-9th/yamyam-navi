package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.config.TestSecurityConfig;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserService;
import com.yamyamnavi.security.LoginUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @Mock
    private User mockUser;

    @Mock
    private LoginUser mockLoginUser;

    @Test
    public void 신규_사용자_등록한다() throws Exception {
        UserResponse mockUserResponse = new UserResponse(1L, "test@example.com", 37.5116, 126.9272);
        when(userService.createUser(any(User.class), any(String.class))).thenReturn(mockUser);
        when(userConverter.convertToUserResponse(any(User.class))).thenReturn(mockUserResponse);

        mockMvc.perform(post("/api/v1/users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\",\"address\":\"서울특별시 동작구 여의대방로54길 18\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void 사용자_로그인한다() throws Exception {
        TokenResponse mockResponse = new TokenResponse("access_token", "refresh_token");
        when(userService.signIn(any(String.class), any(String.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/users/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void 사용자_비밀번호_변경한다() throws Exception {
        UserResponse mockUserResponse = new UserResponse(1L, "test@example.com", 37.5116, 126.9272);
        when(userService.changePassword(any(String.class), any(String.class))).thenReturn(mockUser);
        when(userConverter.convertToUserResponse(any(User.class))).thenReturn(mockUserResponse);
        when(mockLoginUser.getEmail()).thenReturn("test@example.com");

        mockMvc.perform(put("/api/v1/users/me/password")
                        .with(SecurityMockMvcRequestPostProcessors.user(mockLoginUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"newPassword\":\"newpassword123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void 사용자_위치_정보_업데이트한다() throws Exception {
        UserResponse mockUserResponse = new UserResponse(1L, "test@example.com", 33.5063, 126.4922);
        when(userService.updateLocation(any(String.class), any(String.class))).thenReturn(mockUser);
        when(userConverter.convertToUserResponse(any(User.class))).thenReturn(mockUserResponse);
        when(mockLoginUser.getEmail()).thenReturn("test@example.com");

        mockMvc.perform(put("/api/v1/users/me/location")
                        .with(SecurityMockMvcRequestPostProcessors.user(mockLoginUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\":\"제주특별자치도 제주시 특별자치도, 공항로 2\"}"))
                .andExpect(status().isOk());
    }
}