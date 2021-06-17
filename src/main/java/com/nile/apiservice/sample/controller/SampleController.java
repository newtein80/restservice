package com.nile.apiservice.sample.controller;

import java.util.List;

import com.nile.apiservice.sample.model.dto.SampleDTO;
import com.nile.apiservice.sample.service.SampleService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/sample")
public class SampleController {

    private final SampleService sampleService;

    @Operation(summary = "샘플 현황", description = "<big>샘플 현황을 조회</big>") // 한 개의 operation(즉 API URL과 Method)을 선언. value : API에 대한 간략한 설명(제목같은 느낌으로)을 작성. notes: 더 자세한 설명을 작성
    // @ApiResponses({ // operation의 가능한 response를 명시 --> SwaggerConfiguration 클래스로 이동
    //     @ApiResponse(code = 200, message = "OK !!"), // code : 응답코드를 작성. message : 응답에 대한 설명을 작성. responseHeaders : 응답 헤더
    //     @ApiResponse(code = 500, message = "Internal Server Error !!"),
    //     @ApiResponse(code = 404, message = "Not Found !!")
    // })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SampleDTO> getSamples() {
        return this.sampleService.getSamples();
    }

    @Operation(summary = "샘플 상세", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/{sampleId}")
    @ResponseStatus(HttpStatus.OK)
    public SampleDTO getSample(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long sampleId
    ) {
        return this.sampleService.getSample(sampleId);
    }

    @Operation(summary = "샘플 등록", description = "신규 샘플 등록")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SampleDTO addSample(
        @Parameter(name = "샘플 정보", required = true) @RequestBody SampleDTO sampleDTO
    ) {
        return this.sampleService.addSample(sampleDTO.getSampleTitle(), sampleDTO.getSampleContent());
    }

    @Operation(summary = "샘플 수정", description = "샘플 정보 수정")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SampleDTO updateSample(
        @Parameter(name = "샘플 KEY", required = true, example = "1") @PathVariable long sampleId,
        @Parameter(name = "수정된 샘플 정보", required = true) @RequestBody SampleDTO sampleDTO
    ) {
        return this.sampleService.updateSample(sampleId, sampleDTO.getSampleTitle(), sampleDTO.getSampleContent());
    }

    @Operation(summary = "샘플 삭제", description = "샘플 정보 삭제")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteSample(
        @Parameter(name = "샘플 KEY", required = true, example = "1") @PathVariable long sampleId
    ) {
        this.sampleService.deleteSample(sampleId);
    }
}
