package com.springMongoDBLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringMongoDbLoginApplication implements CommandLineRunner{

	private final UserRepository userRepository;
	
	@Autowired
	public SpringMongoDbLoginApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDbLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		
		for(User user: userRepository.findAll()) {
			System.out.println(user);
		}
	}

}
