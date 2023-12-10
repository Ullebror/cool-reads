package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.AppUserSignUpForm;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import jakarta.validation.Valid;

@Controller
public class AppUserController {

	@Autowired
	private AppUserRepository appUserRepository;

	// log in form
	@GetMapping("/register")
	public String registerForm(Model model) {
		return "registration";
	}

	// sign up form
	@GetMapping("/signup")
	public String singupForm(Model model) {
		model.addAttribute("signupform", new AppUserSignUpForm());
		return "signup";
	}

	// save user
	@PostMapping("/saveuser")
	public String saveAppUser(@Valid @ModelAttribute("signupform") AppUserSignUpForm signUpForm,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signUpForm.getPassword_hash().equals(signUpForm.getPasswordCheck())) { // check password match
				String pwd = signUpForm.getPassword_hash();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				AppUser newUser = new AppUser();

				newUser.setPassword_hash(hashPwd);
				newUser.setUsername(signUpForm.getUsername());
				newUser.setRole("USER");
				if (appUserRepository.findByUsername(signUpForm.getUsername()) == null) { // Check if user exists
					appUserRepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/register";
	}

}
