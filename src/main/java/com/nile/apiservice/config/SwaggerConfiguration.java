package com.nile.apiservice.config;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
    private String version = "V1";
    private String title = "REST API " + version;

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(
            new Info()
            .title(title)
            .description("dest")
            .contact(new Contact().name("contact name").url("www.nilesoft.co.kr").email("admin@nilesoft.co.kr"))
            .version(version)
        );
    }

    @Bean
    public OpenApiCustomiser customiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(opertion -> {
                ApiResponses apiResponses = opertion.getResponses();

                ApiResponse apiResponse200 = new ApiResponse().description("Ok --- ")
                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                apiResponses.addApiResponse("200", apiResponse200);

                ApiResponse apiResponse404 = new ApiResponse().description("Not found --- ")
                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                apiResponses.addApiResponse("404", apiResponse404);

                ApiResponse apiResponse500 = new ApiResponse().description("Internal Server Error --- ")
                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                apiResponses.addApiResponse("500", apiResponse500);

            }));
        };
    }
}
