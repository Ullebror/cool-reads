package fi.haagahelia.coolreads.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@ManyToOne
	@JoinColumn(name = "categoryid", referencedColumnName = "id")
	private Category category;

	public Recommendation() {
	}

	public Recommendation(String title, String link, String description, LocalDateTime creationDate) {
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
