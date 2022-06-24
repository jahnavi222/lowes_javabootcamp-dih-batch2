package com.labs.spring.carapp;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;



public class AppMAin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Assemble the objects
				AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
				System.out.println("No of Beans:"+context.getBeanDefinitionCount() );
				for(String beanname:context.getBeanDefinitionNames()) {
				
				System.out.println(beanname);
				Car bmw =context.getBean("bmw",Car.class);
				System.out.println(bmw.getColor()+bmw.getManufacturer()+bmw.getModel()+bmw.getColor()+bmw.getEngine());
				if(Arrays.asList("bmw","nexon").contains(beanname)) {
					System.out.println(bmw.getColor()+""+bmw.getManufacturer()+""+bmw.getModel()+""+bmw.getColor()+""+bmw.getEngine());
					
				}
				}
				

				context.registerShutdownHook();
	}

}
