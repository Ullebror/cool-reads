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

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ReadingRecommendationController.class);
	
		// Add category
		@GetMapping("/addcategory")
		public String addCategory(Model model) {
			model.addAttribute("category", new Category());
			model.addAttribute("errormessage", "");
			log.info("form");
			return "addcategory";
		}
		
		@GetMapping("/categorylist")
		public String listCategory(Model model) {
			model.addAttribute("categories", categoryRepository.findAll());
			
			return "categorylist";
		}

		// Save new recommendation
		@RequestMapping(value = "/addcategory", method = RequestMethod.POST)
		public String saveCategoryOrRedirect(@ModelAttribute("category") Category category, Model model) {
			if (categoryRepository.findByName(category.getName()) != null) {
				model.addAttribute("errormessage", "This category already exists.");
				return "addcategory";
			}
			    categoryRepository.save(category);
			    return "redirect:/categorylist";
		}

}
