package com.jojoldu.book.freelecspringboot2webservice.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.LoginUserArgumentResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer { //LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer 에 추가

	private final LoginUserArgumentResolver loginUserArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가 해야 한다. 이 같은 방식으로 HandlerMethodArgumentResolver를 추가
		argumentResolvers.add(loginUserArgumentResolver);
	}
}
