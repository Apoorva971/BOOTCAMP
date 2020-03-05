package com.tothenew.bootcamp.springFramework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringFrameworkApplication {
	@Autowired
	//@Qualifier("LazyWorker")
	private Worker work;
	public static void main(String[] args) {
	ApplicationContext applicationContext = SpringApplication.run(SpringFrameworkApplication.class, args);
	SpringApplication.run(SpringFrameworkApplication.class, args);
	Manager manager = applicationContext.getBean(Manager.class);
manager.managework();
/*@Bean
		Worker execellentWorker(){
	return  new ExecellentWorker();
		}*/
	/*@Bean
	Worker lazyWorker(){
		return new LazyWorker();*/
	}
	}

