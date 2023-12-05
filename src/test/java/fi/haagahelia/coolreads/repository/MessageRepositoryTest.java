/*package fi.haagahelia.coolreads.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import fi.haagahelia.coolreads.model.Message;

@DataJpaTest
class MessageRepositoryTest {
	@Autowired
	private MessageRepository messageRepository;

	@Test
	void findAllReturnsEmptyListWhenNoMessagesExist() {
		List<Message> messages = messageRepository.findAll();

		assertEquals(0, messages.size());
	}

	@Test
	void findAllReturnsListOfMessagesWhenMessagesExist() {
		messageRepository.save(new Message("Hello world!"));
		List<Message> messages = messageRepository.findAll();

		assertEquals(1, messages.size());
		assertEquals("Hello world!", messages.get(0).getContent());
	}
}
*/