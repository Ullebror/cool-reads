package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password_hash", nullable = false)
	private String password_hash;

	@Column(name = "role", nullable = false)
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Recommendation> recommendations;

	public AppUser(String username, String password_hash, String role) {
		super();
		this.username = username;
		this.password_hash = password_hash;
		this.role = role;
	}
	
}