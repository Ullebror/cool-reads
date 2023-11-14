package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.coolreads.repository.MessageRepository;
import fi.haagahelia.coolreads.dto.AddMessageDto;
import fi.haagahelia.coolreads.model.Message;

@Controller
public class MessageController {
	@Autowired
	private MessageRepository messageRepository;
	
	@GetMapping("/messagelist")
	public String listMessages(Model model) {
		List<Message> messages = messageRepository.findAll();
		model.addAttribute("messages", messages);
		return "messagelist";
	}
	
	@GetMapping("/react-messages")
	public String renderReactMessageList(Model model) {
		return "reactmessagelist";
	}

	@GetMapping("/messages/add")
	public String renderAddMessageForm() {
		return "addmessage";
	}

	@PostMapping("/messages/add")
	public String addMessage(@ModelAttribute AddMessageDto message) {
		Message newMessage = new Message(message.getContent());
		messageRepository.save(newMessage);

		return "redirect:/";
	}
}
