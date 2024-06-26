package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.Recommendation;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryRestControllerTest {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ReadingRecommendationRepository recommendationRepository;
	
	@Autowired
	AppUserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		// deletes everything from the repository before tests.
		categoryRepository.deleteAll();
	}

	@Test
	public void getCategoriesReturnsEmptyListWhenNoCategoriesExist() throws Exception {
		this.mockMvc.perform(get("/api/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void getCategoryByIdAndReturnsNotFoundWhenCategoryDoesNotExist() throws Exception {
		this.mockMvc.perform(get("/api/categories/1"))
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void getCategoryByIdAndReturnsCategoryWhenCategoryExist() throws Exception {
		Category category = new Category("Scrum Guides");
		categoryRepository.save(category);
		
		this.mockMvc.perform(get("/api/categories/" + category.getId()))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getCategoriesReturnsListOfCategoriesWhenCategoriesExist() throws Exception {
		Category firstCategory = new Category("Scrum Guides");
		Category secondCategory = new Category("Video Game News");
		Category thirdCategory = new Category("Miscellaneous");
		categoryRepository.saveAll(List.of(firstCategory, secondCategory, thirdCategory));
		
		this.mockMvc.perform(get("/api/categories"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		//The categories are in alphabetical order, so M, S, V.
		.andExpect(jsonPath("$[1].name").value("Scrum Guides"))
		.andExpect(jsonPath("$[1].id").value(firstCategory.getId()))
		.andExpect(jsonPath("$[2].name").value("Video Game News"))
		.andExpect(jsonPath("$[2].id").value(secondCategory.getId()))
		.andExpect(jsonPath("$[0].name").value("Miscellaneous"))
		.andExpect(jsonPath("$[0].id").value(thirdCategory.getId()));
	}
	
	@Test
	public void getRecommendationsByCategoryIdReturnsEmptyListWhenCategoryDoesNotHaveRecommendations() throws Exception {
		Category firstCategory = new Category("Scrum Guides");
		categoryRepository.save(firstCategory);
		this.mockMvc.perform(get("/api/categories/"+ firstCategory.getId() + "/recommendations"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void getRecommendationsByCategoryIdReturnsListOfRecommendationsWhenCategoryHasRecommendations() throws Exception {
		Category category = new Category("Scrum Guides");
		AppUser user = new AppUser("Testman", "TestingTheTests1", "USER");
		Recommendation firstRecommendation = new Recommendation("The Scrum Guide 2020", "https://scrumguides.org/scrum-guide.html", "All you need to know about Scrum", category, user);
		Recommendation secondRecommendation = new Recommendation("What is Scrum?", "https://www.scrum.org/resources/what-scrum-module", "A deep dive to Scrum", category, user);
		userRepository.save(user);
		categoryRepository.save(category);
		recommendationRepository.saveAll(List.of(firstRecommendation, secondRecommendation));
		
		this.mockMvc.perform(get("/api/categories/" + category.getId() + "/recommendations"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void getRecommendationsByCategoryIdReturnsNotFoundWhenCategoryDoesNotExist() throws Exception {
		this.mockMvc.perform(get("/api/categories/1/recommendations"))
		.andExpect(status().isNotFound());
	}

}
