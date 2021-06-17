package com.nile.apiservice.sample.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "샘플 상세 정보 DTO", description = "샘플 Data Transfer Object")
public class SampleDTO {
    @NotBlank
    @NotNull
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private String sampleTitle;

    @Schema(name="sampleContent", description = "내용", required = true, example = "ex) 내용", nullable = true)
    private String sampleContent;

    @Schema(name="viewcount", description = "조회수", required = false, example = "ex) 내용", nullable = true)
    private Long viewcount;
}