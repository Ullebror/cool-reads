package fi.haagahelia.coolreads.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.coolreads.model.Recommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

import java.util.List;

@Controller
public class ReadingRecommendationController {
	@Autowired
	private ReadingRecommendationRepository readingRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ReadingRecommendationController.class);

	// Add new recommendation
	@GetMapping("/add")
	public String addRecommendation(Model model) {
		model.addAttribute("recommendation", new Recommendation());
		log.info("form");
		return "addrecommendation";
	}

	// Save new recommendation
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveRecommendation(@ModelAttribute("recommendation") Recommendation recommendation) {
	    readingRepository.save(recommendation);
	    return "redirect:/";
	}

	@GetMapping("/")
	public String listRecommendation(Model model) {
		List<Recommendation> recommendations = readingRepository.findAll();
		model.addAttribute("recommendations", recommendations);
		return "recommendationlist";
	}
}
