package com.redhat.workshop.questions.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.redhat.workshop.questions.models.Answer;
import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.WebSocketResponse;
import com.redhat.workshop.questions.models.repositories.AnswerRepository;
import com.redhat.workshop.questions.models.repositories.OptionRepository;

@Controller
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepo;

	@Autowired
	private OptionRepository optionRepo;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	@SendTo("/question/message")
	@MessageMapping("/instructor")
	public String instructorMessage(String message) {
		return message;
	}

	@SendTo("/answer/message")
	@MessageMapping("/student")
	public WebSocketResponse studentMessage(Answer answer) {
		
		if (isAnswerDuplicated(answer.getEmail())) {
			return WebSocketResponse.builder()
						.code(1)
						.message("Questão já respondida!")
						.build();
		}

		Optional<Option> option = optionRepo.findById(answer.getOption().getId());

		if (option.isPresent())
			answer.setOption(option.get());
		else
			return WebSocketResponse.builder()
					.code(2)
					.message("Item da questão não encontrada!")
					.build();
		
		Answer a = answerRepo.save(answer);
		
		messagingTemplate.convertAndSend("/question/message",a);
		
		return WebSocketResponse.builder()
				.code(0)
				.message("Resposta salva com sucesso!")
				.build();
	}

	private boolean isAnswerDuplicated(String email) {
		return this.answerRepo.findByEmail(email).isPresent();
	}
}
