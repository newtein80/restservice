package com.nile.apiservice.factory.controller;

import com.nile.apiservice.factory.model.dto.CourseRequest;
import com.nile.apiservice.factory.model.entity.Course;
import com.nile.apiservice.factory.service.CourseSerivce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/course")
public class CourseController {

    private final CourseSerivce courseSerivce;

    @Operation(summary = "코스 등록 - by request'dto", description = "신규 코스 등록")
    @PostMapping("/saveCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCityWithCountry(
        @Parameter(name = "도시 정보", required = true) @RequestBody CourseRequest courseRequest
    ) {
        return this.courseSerivce.addCourseWithContents(courseRequest);
    }
}
