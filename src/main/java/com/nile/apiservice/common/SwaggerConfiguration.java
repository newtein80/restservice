package com.nile.apiservice.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private String version = "V1";
    private String title = "REST API " + version;

    private ApiInfo apiInfo(String version, String title) {
        return new ApiInfo(
            title,
            "Swagger ApiInfo Description",
            version,
            "www.nilesoft.co.kr",
            new Contact("Manager", "www.nilesoft.co.kr", "apimanager@nilesoft.co.kr"),
            "license",
            "www.nilesoft-license.co.kr",
            Collections.emptyList()// new ArrayList<>()
        );
    }

    @Bean
    public Docket apiDock() {
        List<ResponseMessage> responseMessage = new ArrayList<>();

        responseMessage.add(new ResponseMessageBuilder().code(200).message("OK !!").build());
        responseMessage.add(new ResponseMessageBuilder().code(404).message("NOT FOUND !!").build());
        responseMessage.add(new ResponseMessageBuilder().code(500).message("INTERNAL SERVER ERROR !!").build());

        return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false) // false로 설정하면, swagger에서 제공해주는 응답코드 ( 200,401,403,404 )에 대한 기본 메시지를 제거합니다. 불필요한 응답코드와 메시지를 제거하기 위함이며, 컨트롤러에서 명시해줄 것입니다.
        .groupName(version) // Docket Bean이 한 개일 경우 기본 값은 default이므로, 생략가능, 여러 Docket Bean을 생성했을 경우 groupName이 충돌하지 않아야 하므로, 여기서는 각 Docket Bean의 버전을 명시
        .select() // ApiSelectorBuilder를 생성
        .apis(RequestHandlerSelectors.basePackage("com.nile.apiservice")) // api 스펙이 작성되어 있는 패키지를 지정. 즉, 컨트롤러가 존재하는 패키지를 basepackage로 지정하여, RequestMapping( GetMapping, PostMapping ... )이 선언된 API를 문서화
        .paths(PathSelectors.ant("/v1/nileapi/**")) // apis()로 선택되어진 API중 특정 path 조건에 맞는 API들을 다시 필터링하여 문서화
        .build()
        .pathMapping("/")
        .apiInfo(apiInfo(title, version)) // 제목, 설명 등 문서에 대한 정보들을 보여주기 위해 호출
        .globalResponseMessage(RequestMethod.GET, responseMessage)
        ;
    }
}
