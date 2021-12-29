package com.jojoldu.book.freelecspringboot2webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing test 할때 @WebMvcTest가 같이 스캔하기 때문에 따로 분리시킴.
@SpringBootApplication
public class FreelecSpringboot2WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelecSpringboot2WebserviceApplication.class, args);
	}
}
