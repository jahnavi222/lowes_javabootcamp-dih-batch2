package com.lowes.empapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpConfDispServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context
          = new AnnotationConfigWebApplicationContext();
        //context.setConfigLocation("com.lowes.empapp.config");
        context.register(EmpPackageScan.class);
        
        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = container
          .addServlet("dispatcher", new DispatcherServlet(context));
      
        
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }*/
	 // Method 1
   @Override protected Class<?>[] getRootConfigClasses()
    {
	   return new Class[] { EmpPackageScan.class };
	}
    
  
    // Method 2
    // Registering the Spring config file
    @Override protected Class<?>[] getServletConfigClasses()
    {
  
        Class aClass[] = { EmpPackageScan.class };
  
        return aClass;
    }
  
    // Method 3
    // Add mapping url
    @Override protected String[] getServletMappings()
    {
        String arr[] = { "/" };
        return arr;
    }

}