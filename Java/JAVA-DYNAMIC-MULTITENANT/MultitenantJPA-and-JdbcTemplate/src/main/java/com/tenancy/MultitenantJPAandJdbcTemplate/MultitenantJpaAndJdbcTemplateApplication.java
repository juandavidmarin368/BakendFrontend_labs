package com.tenancy.MultitenantJPAandJdbcTemplate;

import javax.sql.DataSource;

import com.tenancy.MultitenantJPAandJdbcTemplate.MultiTenantSettings.TenantDataSources.DataSourceMap;
import com.tenancy.MultitenantJPAandJdbcTemplate.MultiTenantSettings.TenantInterceptorRoutingDataSource.CustomRoutingDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan(basePackages = "com.tenancy.MultitenantJPAandJdbcTemplate")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = "com.tenancy.MultitenantJPAandJdbcTemplate")
public class MultitenantJpaAndJdbcTemplateApplication {

	private static ConfigurableApplicationContext context;
	private static String[] args;
	public static void main(String[] args) {
		//SpringApplication.run(MultitenantJpaAndJdbcTemplateApplication.class, args);

		MultitenantJpaAndJdbcTemplateApplication.args = args;
		MultitenantJpaAndJdbcTemplateApplication.context = SpringApplication.run(MultitenantJpaAndJdbcTemplateApplication.class, args);
	}


	@Bean
    public DataSource dataSource(){
        CustomRoutingDataSource customDataSource=new CustomRoutingDataSource();
		customDataSource.setTargetDataSources(DataSourceMap.getDataSourceHashMap());
		System.out.println("reading from datasource..");
        return customDataSource;
	}
	
	public static void restart() {
		// close previous context
		context.close();
	
		// and build new one
		MultitenantJpaAndJdbcTemplateApplication.context = SpringApplication.run(MultitenantJpaAndJdbcTemplateApplication.class, args);
	
	}


}
