package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.api.v1.request.SignInRequest;
import com.yamyamnavi.api.v1.request.UserChangePasswordRequest;
import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.request.UserUpdateLocationRequest;
import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserService;
import com.yamyamnavi.security.LoginUser;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "사용자", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 새로운 사용자를 생성합니다.
     *
     * @param request 사용자 생성에 필요한 정보를 담은 객체
     * @return 생성된 사용자 정보
     */
    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    public ResultResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = userConverter.convertToUser(request);
        User createdUser = userService.createUser(user, request.address());
        return new ResultResponse<>(userConverter.convertToUserResponse(createdUser));
    }

    /**
     * 사용자 로그인을 처리합니다.
     *
     * @param request 로그인에 필요한 정보를 담은 객체
     * @return 로그인 성공 시 발급되는 토큰 정보
     */
    @PostMapping("/sign-in")
    @Operation(summary = "사용자 로그인", description = "사용자 로그인을 처리합니다.")
    public ResultResponse<TokenResponse> signIn(@Valid @RequestBody SignInRequest request) {
        TokenResponse tokenResponse = userService.signIn(request.email(), request.password());
        return new ResultResponse<>(tokenResponse);
    }

    /**
     * 사용자 비밀번호를 변경합니다.
     *
     * @param loginUser 로그인에 필요한 정보를 담은 객체
     * @param request 업데이트할 사용자 정보를 담고 있는 객체
     * @return 업데이트 된 고객 정보
     */
    @PutMapping("/me/password")
    @Operation(summary = "비밀번호 변경", description = "로그인한 사용자의 비밀번호를 변경합니다.")
    public ResultResponse<UserResponse> changePassword(@AuthenticationPrincipal LoginUser loginUser, @Valid @RequestBody UserChangePasswordRequest request) {
        User updatedUser = userService.changePassword(loginUser.getEmail(), request.newPassword());
        return new ResultResponse<>(userConverter.convertToUserResponse(updatedUser));
    }

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param loginUser 업데이트할 사용자의 이메일
     * @param request 업데이트할 사용자 정보를 담고 있는 객체
     * @return 업데이트 된 사용자 정보
     */
    @PutMapping("/me/location")
    @Operation(summary = "위치 정보 업데이트", description = "로그인한 사용자의 위치 정보를 주소를 기반으로 업데이트합니다.")
    public ResultResponse<UserResponse> updateLocation(@AuthenticationPrincipal LoginUser loginUser, @Valid @RequestBody UserUpdateLocationRequest request) {
        User updatedUser = userService.updateLocation(loginUser.getEmail(), request.address());
        return new ResultResponse<>(userConverter.convertToUserResponse(updatedUser));
    }

}