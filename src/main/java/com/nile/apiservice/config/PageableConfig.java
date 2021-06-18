package com.nile.apiservice.config;

import java.util.List;

import com.nile.apiservice.common.page.CustomPageResourceAssembler;
import com.nile.apiservice.common.page.PageableArgumentResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageableConfig implements WebMvcConfigurer{
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageableArgumentResolver());
    }

    @Bean(name="customPageResourceAssembler")
    @Primary
    public CustomPageResourceAssembler<?> customPagedResourceAssembler(){
        return new CustomPageResourceAssembler<>(new HateoasPageableHandlerMethodArgumentResolver(),10);
    }
}