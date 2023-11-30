package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.Recommendation;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ReadingRecommendationRepository recommendationRepository;

	@GetMapping("")
	public List<Category> getCategories() {
		return categoryRepository.findAllByOrderByNameAsc();
	}

	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " does not exist"));

	}

	@GetMapping("/{id}/recommendations")
	public List<Recommendation> getRecommendationsByCategoryId(@PathVariable Long id) {
		if (categoryRepository.findById(id).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " does not exist");
		}
		return recommendationRepository.findByCategoryIdOrderByCreationDateDesc(id);
	} 

}
