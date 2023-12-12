package fi.haagahelia.coolreads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.coolreads.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	AppUser findByUsername(String username);
	
	Optional<AppUser> findOneByUsername(String username);

}