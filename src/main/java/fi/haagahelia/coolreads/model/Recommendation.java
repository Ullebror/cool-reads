package fi.haagahelia.coolreads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Recommendation {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable=false)
	private String title;
	private String link;
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
