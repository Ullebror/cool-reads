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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Information about categories and their reading recommendations")
public class CategoryRestController {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ReadingRecommendationRepository recommendationRepository;

	@Operation(summary = "Categories", description = "Fetches all the categories")
	@GetMapping("")
	public List<Category> getCategories() {
		return categoryRepository.findAllByOrderByNameAsc();
	}

	@Operation(summary = "Category by id", description = "Fetches a specific category by id")
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " does not exist"));

	}

	@Operation(summary = "Reading recommendations of a category", description = "Fetches all the reading recommendations of a specific category")
	@GetMapping("/{id}/recommendations")
	public List<Recommendation> getRecommendationsByCategoryId(@PathVariable Long id) throws ResponseStatusException {
		if (!categoryRepository.findById(id).isEmpty()) {
			return recommendationRepository.findByCategoryIdOrderByCreationDateDesc(id);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id " + id + " does not exist");
	}

}
