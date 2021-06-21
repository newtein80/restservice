package com.nile.apiservice.factory.controller;

import java.util.List;

import com.nile.apiservice.factory.model.dto.CityRequest;
import com.nile.apiservice.factory.model.entity.City;
import com.nile.apiservice.factory.service.CityService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/v1/nileapi/city")
public class CityController {
    private final CityService cityService;

    @Operation(summary = "도시 현황", description = "<big>도시 현황을 조회</big>") // 한 개의 operation(즉 API URL과 Method)을 선언. value : API에 대한 간략한 설명(제목같은 느낌으로)을 작성. notes: 더 자세한 설명을 작성
    // @ApiResponses({ // operation의 가능한 response를 명시 --> SwaggerConfiguration 클래스로 이동
    //     @ApiResponse(code = 200, message = "OK !!"), // code : 응답코드를 작성. message : 응답에 대한 설명을 작성. responseHeaders : 응답 헤더
    //     @ApiResponse(code = 500, message = "Internal Server Error !!"),
    //     @ApiResponse(code = 404, message = "Not Found !!")
    // })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<City> getCities() {
        return this.cityService.getCities();
    }

    @Operation(summary = "도시 등록", description = "신규 도시 등록")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City addCity(
        @Parameter(name = "도시 정보", required = true) @RequestBody City cityDto
    ) {
        return this.cityService.saveCity(cityDto);
    }

    @Operation(summary = "도시 상세 - by 도시명", description = "<strong>도시 상세 내용</strong>을 조회")
    @GetMapping("/{cityname}")
    @ResponseStatus(HttpStatus.OK)
    public City getCity(
        @Parameter(name = "도시명", required = true, example = "1")  @PathVariable String cityname
    ) {
        return this.cityService.getCityByName(cityname);
    }

    @Operation(summary = "도시 상세 - by 도시명 2nd", description = "<strong>도시 상세 내용</strong>을 조회")
    @GetMapping("/getCity")
    @ResponseStatus(HttpStatus.OK)
    public City getCityInfo(
        @Parameter(name = "도시명", required = true, example = "1") String cityname
    ) {
        return this.cityService.getCityByName(cityname);
    }

    @Operation(summary = "도시 등록 - by request'dto", description = "신규 도시 등록")
    @PostMapping("/saveCity")
    @ResponseStatus(HttpStatus.CREATED)
    public City addCityWithCountry(
        @Parameter(name = "도시 정보", required = true) @RequestBody CityRequest cityRequest
    ) {
        return this.cityService.addCity(cityRequest);
    }

}
