package com.todo.todo.Launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import com.todo.todo.config.TodosConfig;
import com.todo.todo.dto.Todo;
import com.todo.todo.repository.TodoRep;

@SpringBootApplication
@EntityScan(basePackages = "com.todo.todo.dto")
@ComponentScan(basePackages = "com.todo.todo")
@EnableJpaRepositories(basePackages ="com.todo.todo.repository" )
public class TodoApplication {

	public static void main(String[] args) {

		// ApplicationContext apcntx=new
		// AnnotationConfigApplicationContext(TodosConfig.class);
		// String[] springcmps=apcntx.getBeanDefinitionNames();
		// for(String beans:springcmps)
		// System.out.println(beans+" configured beans......... ");
		SpringApplication.run(TodoApplication.class, args);

		/*
		 * ConfigurableApplicationContext ctxt =
		 * SpringApplication.run(TodoApplication.class, args);
		 * String[] beans = ctxt.getBeanDefinitionNames();
		 * 
		 * for(String bean : beans)
		 * System.out.println("     ...........beans ................"+bean);
		 */
	}

}
