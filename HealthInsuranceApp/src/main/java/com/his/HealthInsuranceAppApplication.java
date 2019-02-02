package com.his;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.his.util.EmailService;

@SpringBootApplication
public class HealthInsuranceAppApplication {

	private static Logger logger =LoggerFactory.getLogger(HealthInsuranceAppApplication.class);
	public static void main(String[] args) {
		logger.debug("HealthInsuranceAppApplication::main() is loaded...");
		SpringApplication.run(HealthInsuranceAppApplication.class, args);
		logger.debug("HealthInsuranceAppApplication::main() is un-loaded...");
	}

}

