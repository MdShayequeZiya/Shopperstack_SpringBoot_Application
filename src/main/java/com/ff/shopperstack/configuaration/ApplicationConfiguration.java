package com.ff.shopperstack.configuaration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationConfiguration {
	
	
	@Bean
	public OpenAPI usersMicroServices() {
		
		Server localhost = new Server();
		localhost.setUrl("http://hostname:8080");
		localhost.setDescription("Development environment");

		Contact contact = new Contact();
		contact.setEmail("msz@gmail.in");
		contact.setName("Shopperstack");
		contact.setUrl("https://themsz.com");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Shopperstack RESTful Web Service documentation").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage Shopperstack.")
				.termsOfService("https://themsz.com/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(localhost));
	
		
		
	}

}
