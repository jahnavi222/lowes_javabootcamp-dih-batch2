package com.labs.spring.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



	public class HelloWorldSpring {
		public static void main(String[] args)  {
			
			Greeetings grt= new Greeetings();
			grt.setMessage("Hello Spring");
			System.out.println(grt.message);
			
			
			//create application container
			try {
				ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config.xml");
				System.out.println(ctx);
				System.out.println("No of beans" +ctx.getBeanDefinitionCount() );
				System.out.println("No of beans" +ctx.getApplicationName());
				
				Greeetings  greeetings=ctx.getBean("grt1",Greeetings.class);
			
				System.out.println(greeetings.getMessage());
				Greeetings  greeetings1=ctx.getBean("grt2",Greeetings.class);
				
				System.out.println(greeetings1.getMessage());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
