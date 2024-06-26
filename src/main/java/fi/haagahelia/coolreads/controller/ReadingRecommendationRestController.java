package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.Recommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "https://localhost:5173")
@Tag(name="Recommendations", description="Information about all the reading recommendations")
public class ReadingRecommendationRestController {
	@Autowired
	private ReadingRecommendationRepository recommendationRepository;
	
	 @Operation(
			    summary = "Reading recommendations",
			    description = "Fetches all the reading recommendations"
			  )
	@GetMapping("")
	public List<Recommendation> getRecommendations(){
		return recommendationRepository.findAllByOrderByCreationDateDesc();
		
	}
	
	

}
