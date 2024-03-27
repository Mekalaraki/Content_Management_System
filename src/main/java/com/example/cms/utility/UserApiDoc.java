package com.example.cms.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class UserApiDoc {
	@Bean
	 Info info ()
	 {
		return new Info().title("Content Management System")
				.description("RESTFUL API with basic CRUD operation")
				.version("v1").contact(contact());
	 }
	@Bean
	Contact contact()
	{
		return new  Contact().email("abc@gmail").name("rakesh").url("rakesh.com");
	}

	@Bean
	OpenAPI openAPI()
	{
		
		return new OpenAPI().info(info());
	}

}
