package com.labs.spring.carapp;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	@Bean(autowire=Autowire.BY_TYPE)
	//@Bean(autowire=Autowire.BY_TYPE,initMethod="init",destroyMethod="destroy")
	//constructor injection
	public Car bmw() {
		Car bmw= new Car("xt","BMW","WHITE", null);
		
		bmw.setEngine(electic());
		return  bmw;
		
		
	}
	public Car nexon() {
		Car bmw= new Car("dt","TATA","BLUE",null);
		
		bmw.setEngine(petrol());
		return bmw;
	}
		
	public Engine petrol() {
		return new Engine("petrol",2000);
		
	}
	@Bean(autowireCandidate=false)
	public Engine engine() {
		return new Engine("engine",2000);
		
	}
	
	
	@Bean(autowireCandidate=false)
	@Primary
	public Engine electic() {
		return new Engine("electic",2000);
		
	}

}
