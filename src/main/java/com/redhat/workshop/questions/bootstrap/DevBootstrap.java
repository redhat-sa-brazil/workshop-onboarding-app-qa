package com.redhat.workshop.questions.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.Question;
import com.redhat.workshop.questions.models.repositories.QuestionRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private QuestionRepository questionRepo;
	
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
		q2.getOptions().add(new Option("Sim"));
		q2.getOptions().add(new Option("Não"));
		
		questionRepo.save(q2);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}
}
