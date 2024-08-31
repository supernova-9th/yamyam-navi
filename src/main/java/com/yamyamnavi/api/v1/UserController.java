package com.yamyamnavi.api.v1;

import com.yamyamnavi.api.v1.converter.UserConverter;
import com.yamyamnavi.api.v1.request.SignInRequest;
import com.yamyamnavi.api.v1.request.UserCreateRequest;
import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.api.v1.response.UserResponse;
import com.yamyamnavi.domain.user.UserService;
import com.yamyamnavi.support.response.ResultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public ResultResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        var user = userService.createUser(userConverter.toUser(request));
        return new ResultResponse<>(userConverter.toUserResponse(user));
    }

    @PostMapping("/sign-in")
    public ResultResponse<TokenResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return new ResultResponse<>(userService.signIn(request.email(), request.password()));
    }
}