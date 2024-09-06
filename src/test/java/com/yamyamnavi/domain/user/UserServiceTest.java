package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.location.GeocodeService;
import com.yamyamnavi.domain.location.GeoPoint;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.error.InvalidPasswordException;
import com.yamyamnavi.support.error.UserRegistrationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GeocodeService geocodeService;

    @MockBean
    private TokenIssuer tokenIssuer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 사용자_생성한다() {
        User user = User.builder().email("test@example.com").password("password123").build();
        GeoPoint geoPoint = new GeoPoint(37.5665, 126.9780);
        when(geocodeService.getGeoPointFromAddress(any())).thenReturn(geoPoint);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user, "서울특별시 동작구 여의대방로54길 18");

        assertNotNull(createdUser);
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals(37.5665, createdUser.getLatitude());
        assertEquals(126.9780, createdUser.getLongitude());
    }

    @Test
    public void 사용자_로그인한다() {
        String email = "test@example.com";
        String password = "password123";
        User user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));
        when(tokenIssuer.createToken(email)).thenReturn(new TokenResponse("access_token", "refresh_token"));

        TokenResponse response = userService.signIn(email, password);

        assertNotNull(response);
        assertNotNull(response.accessToken());
        assertNotNull(response.refreshToken());
    }

    @Test
    public void 중복된_이메일로_사용자_생성시_예외_발생한다() {
        User user = User.builder().email("existing@example.com").password("password123").build();
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(UserRegistrationException.class, () -> userService.createUser(user, "서울특별시 동작구 여의대방로54길 18"));
    }

    @Test
    public void 잘못된_비밀번호로_로그인시_예외_발생한다() {
        String email = "test@example.com";
        String wrongPassword = "wrongpassword";
        User user = User.builder().email(email).password(passwordEncoder.encode("correctpassword")).build();
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));

        assertThrows(InvalidPasswordException.class, () -> userService.signIn(email, wrongPassword));
    }
}