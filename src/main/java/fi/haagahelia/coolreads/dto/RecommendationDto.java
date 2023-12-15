package fi.haagahelia.coolreads.dto;

import fi.haagahelia.coolreads.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecommendationDto {
	
	@NotBlank(message = "title is required")
	private String title;
	
	@NotBlank(message = "link is required")
	@Pattern(regexp = "^(https?://.+)$")
	private String link;
	
	@NotBlank(message = "description is required")
	private String description;
	
	@NotNull
	private Category category;
	
	
}