package com.his.ar.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.his.ar.ws.consumer.SsnValidatorService;

@Configuration
public class SsnConsumerConfig {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the  specified in
		// pom.xml
		marshaller.setContextPath("federalGov.wsdl");
		return marshaller;
	}

	@Bean
	public SsnValidatorService movieClient(Jaxb2Marshaller marshaller) {
		SsnValidatorService client = new SsnValidatorService();
		client.setDefaultUri("http://localhost:9090/service/ssn-details");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
