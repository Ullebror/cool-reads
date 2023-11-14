package fi.haagahelia.coolreads.dto;

public class AddRecommendationDto {
	private String content;

	public AddRecommendationDto(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
