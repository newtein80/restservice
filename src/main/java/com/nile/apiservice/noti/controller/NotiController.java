package com.nile.apiservice.noti.controller;

import java.util.List;

import com.nile.apiservice.noti.entity.Noti;
import com.nile.apiservice.noti.service.NotiService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/noti")
public class NotiController {

    private final NotiService notiService;

    @Operation(summary = "샘플 현황", description = "<big>샘플 현황을 조회</big>",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK !!"), // code : 응답코드를 작성. message : 응답에 대한 설명을 작성. responseHeaders : 응답 헤더
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
        }
    ) // 한 개의 operation(즉 API URL과 Method)을 선언. value : API에 대한 간략한 설명(제목같은 느낌으로)을 작성. notes: 더 자세한 설명을 작성
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Noti> getSamples() {
        return this.notiService.getAllNotis();
    }

    /**
     * @PathVariable, @RequestBody, @RequestParam
     */

    /**
     * 
     * @param id
     * @return
     */
    @Operation(summary = "샘플 상세", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Noti getSample(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getNoti(id);
    }
    
}
