package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name="Users", description="Information about the users of Cool Reads")
public class AppUserRestController {
	@Autowired
	private AppUserRepository userRepository;

	@Operation(summary = "Users", description = "Fetches all the users")
	@GetMapping("")
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Operation(summary = "Current user", description = "Fetches the current user but only shows it if logged in.")
	@GetMapping("/current")
	public AppUser getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required");
		}

		return userRepository.findOneByUsername(userDetails.getUsername())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required"));
	}
}