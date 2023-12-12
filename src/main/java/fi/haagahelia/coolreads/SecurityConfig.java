package fi.haagahelia.coolreads;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers(antMatcher("/"), antMatcher("/signup"), antMatcher("/saveuser"),
				antMatcher("/register"), antMatcher("/categorylist"), antMatcher("/frontend/**"), antMatcher("/api/**"),
				antMatcher("/error")).permitAll().anyRequest().authenticated());

		http.formLogin((form) -> form.permitAll());
		http.logout((logout) -> logout.permitAll());

		return http.build();
	}
}
