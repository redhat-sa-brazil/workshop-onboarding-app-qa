package com.redhat.workshop.questions.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.redhat.workshop.questions.models.Answer;
import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.repositories.AnswerRepository;
import com.redhat.workshop.questions.models.repositories.OptionRepository;

@RestController
public class AnswerController {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private AnswerRepository answerRepo;

	@Autowired
	private OptionRepository optionRepo;
	
	@RequestMapping(path = "/rest/answer",method = RequestMethod.POST)
	public ResponseEntity<?> answer(@RequestBody Answer answer) {
		
		if (isAnswerDuplicated(answer.getEmail())) {
			return ResponseEntity.badRequest().body("Questão já respondida!");
		}
		
		Optional<Option> option = optionRepo.findById(answer.getOption().getId());
		
		if (option.isPresent()) {
			
			Option opt = option.get();
			
			if (!isQuestionEnabled(opt)) {
				return ResponseEntity.badRequest().body("Questão não está ativada!");
			}
			answer.setOption(opt);
		}
		else
			return ResponseEntity.notFound().build();
		
		Answer a = answerRepo.save(answer);
		
		messagingTemplate.convertAndSend("/instructor",a);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(answer.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	private boolean isAnswerDuplicated(String email) {
		return this.answerRepo.findByEmail(email).isPresent();
	}
	
	private boolean isQuestionEnabled(Option option) {
		return option.getQuestion().getEnabled();
	}
}
