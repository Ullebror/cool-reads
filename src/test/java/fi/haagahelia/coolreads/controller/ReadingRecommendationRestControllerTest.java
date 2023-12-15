package fi.haagahelia.coolreads.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.Recommendation;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadingRecommendationRestControllerTest {
	@Autowired
	ReadingRecommendationRepository recommendationRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	AppUserRepository userRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		//deletes everything from the repositories before tests.
		recommendationRepository.deleteAll();
		categoryRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void getRecommendationsReturnsEmptyListWhenNoRecommendationsExist() throws Exception {
		this.mockMvc.perform(get("/api/recommendations"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void getRecommendationsReturnsListOfRecommendationsWhenRecommendationsExist() throws Exception {
		Category category = new Category("Scrum Guides");
		AppUser user = new AppUser("Testman", "TestingTheTests1", "USER");
		Recommendation firstRecommendation = new Recommendation("The Scrum Guide 2020", "https://scrumguides.org/scrum-guide.html", "All you need to know about Scrum", category, user);
		Recommendation secondRecommendation = new Recommendation("What is Scrum?", "https://www.scrum.org/resources/what-scrum-module", "A deep dive to Scrum", category, user);
		categoryRepository.save(category);
		userRepository.save(user);
		recommendationRepository.saveAll(List.of(firstRecommendation, secondRecommendation));
		
		this.mockMvc.perform(get("/api/recommendations"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			//the API is ordered by the date and time it was added on, so the first value is the latest added, so "What is Scrum?" is 0.
			.andExpect(jsonPath("$[1].title").value("The Scrum Guide 2020"))
			.andExpect(jsonPath("$[1].id").value(firstRecommendation.getId()))
			.andExpect(jsonPath("$[0].title").value("What is Scrum?"))
			.andExpect(jsonPath("$[0].id").value(secondRecommendation.getId()));
	}

}
