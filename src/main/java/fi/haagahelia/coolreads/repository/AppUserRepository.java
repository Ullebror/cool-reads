package fi.haagahelia.coolreads.repository;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.coolreads.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	
	AppUser findByUsername(String username);

}