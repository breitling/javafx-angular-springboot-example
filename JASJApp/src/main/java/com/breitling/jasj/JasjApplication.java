package com.breitling.jasj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasjApplication
{	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(JasjApplication.class);

	public static void main(String... args) 
	{
		@SuppressWarnings("unused")
		var context = SpringApplication.run(JasjApplication.class, args);
		FxApp.main(args);
	}
}
