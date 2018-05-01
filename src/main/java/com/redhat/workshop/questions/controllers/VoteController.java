package com.redhat.workshop.questions.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.Vote;
import com.redhat.workshop.questions.models.repositories.OptionRepository;
import com.redhat.workshop.questions.models.repositories.VoteRepository;

@RestController
@RequestMapping("/rest/vote")
public class VoteController {
	
	@Autowired
	private VoteRepository voteRepo;
	
	@Autowired
	private OptionRepository optionRepo;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> vote(@RequestBody Vote vote) {
		if(isDuplicateVote(vote.getEmail())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		Optional<Option> option = optionRepo.findById(vote.getOption().getId());
		
		if(option.isPresent())
			vote.setOption(option.get());
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(voteRepo.save(vote),HttpStatus.OK);
	}
	
	private boolean isDuplicateVote(String email) {
		return this.voteRepo.findByEmail(email).isPresent();
	}
}
