package com.lowes.empapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class EmpDataBaseConfig {
	


		@Bean
		public MysqlDataSource getDataSource() {

			MysqlDataSource bds = new MysqlDataSource();
			bds.setDatabaseName("jdbctraining");
			bds.setUrl("jdbc:mysql://localhost:3306/jdbctraining");
			bds.setUser("training");
			bds.setPassword("training");

			return bds;
		}

}

