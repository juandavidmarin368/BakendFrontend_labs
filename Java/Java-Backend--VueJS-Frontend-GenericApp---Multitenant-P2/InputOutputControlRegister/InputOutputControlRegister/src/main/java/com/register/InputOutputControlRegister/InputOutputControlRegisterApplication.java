package com.register.InputOutputControlRegister;

import javax.sql.DataSource;

import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Controllers.CountingTime;
import com.register.InputOutputControlRegister.ApplicationLayer.JDBC.Dao.CargoPerfilesDAO;
import com.register.InputOutputControlRegister.MultiTenantSettings.TenantDataSources.DataSourceMap;
import com.register.InputOutputControlRegister.MultiTenantSettings.TenantInterceptorRoutingDataSource.CustomRoutingDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = "com.register.InputOutputControlRegister")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = "com.register.InputOutputControlRegister")
public class InputOutputControlRegisterApplication {

	CargoPerfilesDAO ct = new CargoPerfilesDAO();

	public static void main(String[] args) {
		SpringApplication.run(InputOutputControlRegisterApplication.class, args);
	}

	@Bean
    public DataSource dataSource(){
        CustomRoutingDataSource customDataSource=new CustomRoutingDataSource();
		customDataSource.setTargetDataSources(DataSourceMap.getDataSourceHashMap());
		System.out.println("reading from datasource..");
        return customDataSource;
	}

	/*
	@Scheduled(cron = "0/20 * * * * ?")
    public void publish() {
	   
		ct.satartCounting();
    }
	*/


}
