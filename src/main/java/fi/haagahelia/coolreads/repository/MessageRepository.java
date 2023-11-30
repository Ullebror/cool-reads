package fi.haagahelia.coolreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fi.haagahelia.coolreads.model.Message;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {
}
