package fi.haagahelia.coolreads.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.coolreads.dto.RecommendationDto;
import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Recommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import jakarta.validation.Valid;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;

//import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReadingRecommendationController {
	@Autowired
	private ReadingRecommendationRepository readingRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AppUserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(ReadingRecommendationController.class);

	// Add new recommendation
	@GetMapping("/add")
	public String addRecommendation(Model model) {
		model.addAttribute("recommendation", new RecommendationDto());
		model.addAttribute("categories", categoryRepository.findAll());
		log.info("form");
		return "addrecommendation";
	}

	// Save new recommendation
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveRecommendation(@Valid @ModelAttribute("recommendation") RecommendationDto recommendation,
			BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (bindingResult.hasErrors()) { //checks that the recommendation is Valid
			model.addAttribute("recommendation", recommendation);
			return "addrecommendation";
		}

		AppUser user = userRepository.findByUsername(userDetails.getUsername());

		if (user == null) { // checks if user is signed in
			return "redirect:/";
		}
		Recommendation newRecommendation = new Recommendation(recommendation.getTitle(), recommendation.getLink(),
				recommendation.getDescription(), recommendation.getCategory(), user);
		readingRepository.save(newRecommendation);
		return "redirect:/";
	}

	@GetMapping("/")
	public String listRecommendation(Model model) {
		List<Recommendation> recommendations = readingRepository.findAll();
		model.addAttribute("recommendations", recommendations);
		return "recommendationlist";
	}

	@GetMapping("/createdDate")
	public String listRecommendationByDateCreatedDesc(Model model) {
		List<Recommendation> recommendations = readingRepository.findAllByOrderByCreationDateDesc();
		model.addAttribute("recommendations", recommendations);
		return "recommendationlist";
	}

	@GetMapping("/edit/{id}")
	public String editReadingRecommendation(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {

		model.addAttribute("categories", categoryRepository.findAll());
		Recommendation recommendation = readingRepository.findById(id).orElse(null);
		//checks that the user is the one that added the recommendation
		if (userDetails.getUsername().equalsIgnoreCase(recommendation.getUser().getUsername())) {
			if (recommendation != null) {
				model.addAttribute("recommendation", recommendation);
				return "editrecommendation";
			}
		}

		return "redirect:/";
	}

	@PostMapping("/saveEditedReadingRecommendation")
	public String saveEditedRecommendation(@ModelAttribute Recommendation recommendationForm,
			@AuthenticationPrincipal UserDetails userDetails) {
		//checks that the user is the one that added the recommendation
		if (userDetails.getUsername().equalsIgnoreCase(recommendationForm.getUser().getUsername())) {
			readingRepository.save(recommendationForm);
			return "redirect:/";
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized user");
		}

	}

	@PostMapping("/delete/{id}")
	public String deleteRecommendation(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {
		Recommendation recommendation = readingRepository.findById(id).orElse(null);
		//checks that the user is the one that added the recommendation
		if (userDetails.getUsername().equalsIgnoreCase(recommendation.getUser().getUsername())) {
			readingRepository.deleteById(id);
		}

		return "redirect:/";
	}
}
