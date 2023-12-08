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

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.CategoryRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryRestControllerTest {
	@Autowired
	CategoryRepository categoryRepository;

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

}
