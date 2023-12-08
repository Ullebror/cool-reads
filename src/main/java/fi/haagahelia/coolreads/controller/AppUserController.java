package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		return "register";
	}

	// sign up form
	@GetMapping("/signup")
	public String singupForm(Model model) {
		model.addAttribute("signupform", new AppUserSignUpForm());
		return "signup";
	}
	
	// save user
	@PostMapping("saveuser")
	public String saveAppUser(@Valid @ModelAttribute("signupform") AppUserSignUpForm signUpForm, BindingResult bindingResult) {
		
		return "redirect:/";
	}
	
	
}
