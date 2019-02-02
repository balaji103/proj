package com.his;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * this class initialized the spring boot 
 * @author balaji
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
	/**
	 * 
	 */
	public ServletInitializer() {
		super();
	}

	/**
	 * 
	 */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(HealthInsuranceAppApplication.class);
	}

}

