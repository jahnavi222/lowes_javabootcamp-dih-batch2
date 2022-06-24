package com.labs.spring.boot.helloworldspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

 
@SpringBootApplication
public class HelloWorldSpringBootApplication implements CommandLineRunner {
	@Autowired
	ApplicationContext contxt;
	Logger logger=LoggerFactory.getLogger(HelloWorldSpringBootApplication.class);

	public static void main(String[] args) {
		System.out.println("test1");
		SpringApplication.run(HelloWorldSpringBootApplication.class, args);
		//SpringApplication.run(HelloWorldSpringBootApplication.class, args);
		System.out.println("test2");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("test3");
		System.out.println("context" + contxt);
		System.out.println("no of beans" +contxt.getBeanDefinitionCount());
		
		for(String beans:contxt.getBeanDefinitionNames()) {
			System.out.println("no of beans" +beans);
			
		}
		Greetings greetings = contxt.getBean("greetings",Greetings.class);
		greetings.setMessage("hellow spring boot");
		System.out.println(greetings.getMessage());
		logger.info(greetings.getMessage());
	}
	
}
