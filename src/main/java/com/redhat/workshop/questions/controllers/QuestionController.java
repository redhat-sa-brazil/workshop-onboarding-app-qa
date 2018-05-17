package com.redhat.workshop.questions.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.workshop.questions.models.Question;
import com.redhat.workshop.questions.models.repositories.QuestionRepository;

@RestController
public class QuestionController {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@RequestMapping(path = "/rest/question/{id}/activate",method = RequestMethod.POST)
	public ResponseEntity<?> activate(@PathVariable Long id) {
		
		Optional<Question> questioOpt = questionRepo.findById(id);
		
		if(questioOpt.isPresent()) {
			
			Question q1 = questioOpt.get();
			
			if(q1.getEnabled()) {
				return ResponseEntity.badRequest().body("Questão já ativada!");
			}
			else {
				q1.setEnabled(true); 
				questionRepo.save(q1);
				messagingTemplate.convertAndSend("/student",q1);
				return ResponseEntity.ok().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(path = "/rest/question/{id}/desactivate",method = RequestMethod.POST)
	public ResponseEntity<?> desactivate(@PathVariable Long id) {
		
		Optional<Question> questionOpt = questionRepo.findById(id);
		
		if(questionOpt.isPresent()) {
			
			Question q1 = questionOpt.get();
			
			if(!q1.getEnabled()) {
				return ResponseEntity.badRequest().body("Questão já desativada!");
			}
			else {
				q1.setEnabled(false); 
				questionRepo.save(q1);
				messagingTemplate.convertAndSend("/student",q1);
				return ResponseEntity.ok().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
