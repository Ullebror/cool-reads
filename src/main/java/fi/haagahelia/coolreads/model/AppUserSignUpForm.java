package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AppUserSignUpForm {
	@NotEmpty(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username = "";

	@NotEmpty(message = "Password is required")
	@Size(min = 6, message = "Password must have at least 6 characters")
	private String password_hash = "";

	@NotEmpty(message = "Password is required")
	@Size(min = 6, message = "Password must have at least 6 characters")
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

}
