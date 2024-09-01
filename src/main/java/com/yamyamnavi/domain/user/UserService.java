package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.request.UserUpdateRequest;
import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.error.UserNotFoundException;
import com.yamyamnavi.support.utils.GeometryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppender userAppender;
    private final UserLoginProcessor userLoginProcessor;
    private final UserValidator userValidator;
    private final UserFinder userFinder;
    private final TokenIssuer tokenIssuer;
    private final UserRepository userRepository;

    /**
     * 새로운 사용자를 생성합니다.
     *
     * @param user 생성할 사용자 정보
     * @return 생성된 사용자 정보
     */
    @Transactional
    public User createUser(User user) {
        userValidator.validateEmail(user.getEmail());
        return userAppender.append(user);
    }

    /**
     * 사용자 로그인을 처리합니다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 발급되는 토큰 정보
     */
    @Transactional
    public TokenResponse signIn(String email, String password) {
        return userLoginProcessor.process(email, password);
    }

    /**
     * 이메일로 사용자 정보를 조회합니다.
     *
     * @param email 조회할 사용자의 이메일
     * @return 조회된 사용자 정보
     */
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userFinder.findByEmailOrThrow(email);
    }

    /**
     * 사용자 로그아웃을 처리합니다.
     *
     * @param email 로그아웃할 사용자의 이메일
     */
    @Transactional
    public void logout(String email) {
        tokenIssuer.revokeToken(email);
    }

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param email 업데이트할 사용자의 이메일
     * @param request 업데이트할 사용자 정보를 담고 있는 객체
     * @return 업데이트된 사용자 정보
     * @throws UserNotFoundException 지정된 이메일의 사용자를 찾을 수 없는 경우
     */
    @Transactional
    public User updateUser(String email, UserUpdateRequest request) {
        User user = userFinder.findByEmailOrThrow(email);

        if (request.password() != null) {
            user.setPassword(request.password());
        }
        if (request.latitude() != null && request.longitude() != null) {
            user.setLocation(GeometryUtils.getPoint(request.longitude(), request.latitude()));
        }

        return userRepository.save(user);
    }
}