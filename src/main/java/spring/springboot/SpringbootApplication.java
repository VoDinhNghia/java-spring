package spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import spring.springboot.services.UserService;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@EnableAutoConfiguration()
@EnableJpaRepositories(basePackages = { "spring.springboot.repositories" })
@EntityScan(basePackages = { "spring.springboot.entities" })
public class SpringbootApplication {
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return userService.initAccountAdmin();
	}

}
