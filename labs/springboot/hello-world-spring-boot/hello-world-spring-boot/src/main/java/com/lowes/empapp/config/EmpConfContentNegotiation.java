package com.lowes.empapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
@Configuration
public class EmpConfContentNegotiation {
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(true).
	    favorParameter(true).
	    parameterName("mediaType").
	    ignoreAcceptHeader(false).
	    useJaf(false).
	    defaultContentType(MediaType.APPLICATION_JSON).
	    mediaType("xml", MediaType.APPLICATION_XML). 
	    mediaType("json", MediaType.APPLICATION_JSON); 
	}

}
