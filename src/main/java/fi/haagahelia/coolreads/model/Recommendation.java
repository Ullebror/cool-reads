package fi.haagahelia.coolreads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Recommendation {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String title;

	// not empty, starts with http:// or https://
	@Column(nullable = false)
	@NotBlank
	@Pattern(regexp = "^(https?://.+)$")
	private String link;

	@Column(nullable = false)
	@NotBlank
	private String description;

	public Recommendation() {
	}

	public Recommendation(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
