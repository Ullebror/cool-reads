package fi.haagahelia.coolreads.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
	@Size(min = 8, message = "Password must have at least 6 characters")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Paasword must contain at least one letter and one number")
	private String password_hash = "";

	@NotEmpty(message = "Password is required")
	@Size(min = 8, message = "Password must have at least 6 characters")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Paasword must contain at least one letter and one number")
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

}
