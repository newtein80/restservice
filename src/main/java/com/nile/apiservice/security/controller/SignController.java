package com.nile.apiservice.security.controller;

import java.util.Collections;

import com.nile.apiservice.common.model.response.CommonResult;
import com.nile.apiservice.common.model.response.SingleResult;
import com.nile.apiservice.security.JwtTokenProvider;
import com.nile.apiservice.security.entity.User;
import com.nile.apiservice.security.exception.exceptions.CEmailSigninFailedException;
import com.nile.apiservice.security.repository.UserJpaRepository;
import com.nile.apiservice.security.service.ResponseService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/sign")
public class SignController {

    private final UserJpaRepository userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;


    @Operation(summary = "로그인", description = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@Parameter(name = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @Parameter(name = "비밀번호", required = true) @RequestParam String password) {

        User user = userJpaRepo.findByUid(id).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @Operation(summary = "가입", description = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@Parameter(name = "회원ID : 이메일", required = true) @RequestParam String id,
                               @Parameter(name = "비밀번호", required = true) @RequestParam String password,
                               @Parameter(name = "이름", required = true) @RequestParam String name) {

        userJpaRepo.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }
    
}