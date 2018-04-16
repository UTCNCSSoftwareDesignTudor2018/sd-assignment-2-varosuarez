package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@EnableMongoRepositories("data.repository")
@EnableJpaRepositories("data.repository")
@EntityScan("data.entity")
@ComponentScan({"main","data","data.entity","data.repository","controller"})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
}
