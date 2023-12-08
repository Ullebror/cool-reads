package fi.haagahelia.coolreads;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.repository.AppUserRepository;

@SpringBootApplication
public class CoolReadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolReadsApplication.class, args);
	}

	@Bean
	public CommandLineRunner coolReadsDemo(AppUserRepository userRepository) {
		return (args) -> {
			AppUser userOne = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			userRepository.save(userOne);
		};
	}
}
