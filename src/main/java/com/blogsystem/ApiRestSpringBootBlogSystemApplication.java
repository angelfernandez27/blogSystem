package com.blogsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiRestSpringBootBlogSystemApplication {
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiRestSpringBootBlogSystemApplication.class, args);
	}

}
