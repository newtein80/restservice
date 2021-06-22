package com.nile.apiservice;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.nile.apiservice.common.repository.BaseRepositoryImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
// @EnableJpaRepositories(repositoryImplementationPostfix = "")
public class ApiserviceApplication {

	private static final String dateFormat = "yyyy-MM-dd";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	public static void main(String[] args) {
		SpringApplication.run(ApiserviceApplication.class, args);
	}

	// * Date convert
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
		// ! 응답 json의 date format을 지정하는 부분
		return builder ->{
			builder.simpleDateFormat(dateTimeFormat);
			// ! google-search: spring.jackson.time-zone korea
			// ! https://jojoldu.tistory.com/361
			// ! https://wky.kr/13
			builder.timeZone(TimeZone.getTimeZone("Asia/Seoul"));
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
		};
	}

}
