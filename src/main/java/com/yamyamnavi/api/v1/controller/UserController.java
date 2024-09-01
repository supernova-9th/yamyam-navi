package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.api.v1.request.SignInRequest;
import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.request.UserUpdateRequest;
import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.UserService;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        var user = userService.createUser(userConverter.convertToUser(request));
        return new ResultResponse<>(userConverter.convertToUserResponse(user));
    }

    /**
     * 사용자 로그인을 처리합니다.
     *
     * @param request 로그인에 필요한 정보를 담은 객체
     * @return 로그인 성공 시 발급되는 토큰 정보
     */
    @PostMapping("/sign-in")
    @Operation(summary = "사용자 로그인", description = "사용자 로그인을 처리하고 토큰을 발급합니다.")
    public ResultResponse<TokenResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return new ResultResponse<>(userService.signIn(request.email(), request.password()));
    }

    /**
     * 사용자 정보를 업데이트합니다.
     *
     * @param email 업데이트할 사용자의 이메일
     * @param request 업데이트할 사용자 정보를 담고 있는 객체
     * @return 업데이트된 사용자 정보
     */
    @PutMapping("/{email}")
    @Operation(summary = "사용자 정보 업데이트", description = "사용자 정보를 업데이트합니다.")
    public ResultResponse<UserResponse> updateUser(@PathVariable String email, @Valid @RequestBody UserUpdateRequest request) {
        var updatedUser = userService.updateUser(email, request);
        return new ResultResponse<>(userConverter.convertToUserResponse(updatedUser));
    }
}