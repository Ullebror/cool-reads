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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
	@NotNull
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private AppUser user;

	public Recommendation(String title, String link, String description, Category category) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.category = category;
	}

}
