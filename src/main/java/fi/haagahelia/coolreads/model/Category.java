package fi.haagahelia.coolreads.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Category {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	@NotBlank
	private String name;
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
