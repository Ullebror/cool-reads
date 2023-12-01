package fi.haagahelia.coolreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.haagahelia.coolreads.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
