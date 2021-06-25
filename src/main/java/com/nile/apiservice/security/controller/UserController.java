package com.nile.apiservice.security.controller;

import com.nile.apiservice.common.model.response.CommonResult;
import com.nile.apiservice.common.model.response.ListResult;
import com.nile.apiservice.common.model.response.SingleResult;
import com.nile.apiservice.security.entity.User;
import com.nile.apiservice.security.exception.exceptions.CUserNotFoundException;
import com.nile.apiservice.security.repository.UserJpaRepository;
import com.nile.apiservice.security.service.ResponseService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/nileapi/user")
public class UserController {
    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService; // 결과를 처리할 Service

    // https://oingdaddy.tistory.com/272

    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 access_token", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "String"))
    })
    @Operation(summary = "회원 리스트 조회", description = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepository.findAll());
    }

    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 access_token", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "String"))
    })
    @Operation(summary = "회원 단건 조회", description = "회원번호(msrl)로 회원을 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<User> findUser() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepository.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 access_token", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "String"))
    })
    @Operation(summary = "회원 수정", description = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @Parameter(name = "회원번호", required = true) @RequestParam long msrl,
            @Parameter(name = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepository.save(user));
    }

    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "로그인 성공 후 access_token", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "String"))
    })
    @Operation(summary = "회원 삭제", description = "회원번호(msrl)로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @Parameter(name = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepository.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
