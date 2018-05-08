package com.redhat.workshop.questions.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.Question;
import com.redhat.workshop.questions.models.Answer;
import com.redhat.workshop.questions.models.repositories.QuestionRepository;
import com.redhat.workshop.questions.models.repositories.AnswerRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private AnswerRepository responseRepository;
	
	public DevBootstrap(QuestionRepository qr) {
		this.questionRepo = qr;
	}
	
	public void initData() {
		Question q1 = new Question();
		q1.setDescription("Qual sua cor favorita?");
		q1.setEnabled(true);
		q1.getOptions().add(new Option("Vermelho"));
		q1.getOptions().add(new Option("Azul"));
		q1.getOptions().add(new Option("Amarelo"));
		
		questionRepo.save(q1);
		
		Question q2 = new Question();
		q2.setDescription("Você conhece docker?");
		q2.setEnabled(false);
		Option op1 = new Option("Sim");
		Option op2 = new Option("Não");
		q2.getOptions().add(op1);
		q2.getOptions().add(op2);
		
		questionRepo.save(q2);
		
		Answer vote = new Answer();
		vote.setComment("Nunca ouvi falar");
		vote.setEmail("a@a.com");
		vote.setOption(op1);
		
		responseRepository.save(vote);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}
}
