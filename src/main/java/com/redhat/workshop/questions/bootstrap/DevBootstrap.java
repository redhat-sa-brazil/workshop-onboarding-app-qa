package com.redhat.workshop.questions.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.Question;
import com.redhat.workshop.questions.models.repositories.QuestionRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private QuestionRepository questionRepo;
	
	public DevBootstrap(QuestionRepository qr) {
		this.questionRepo = qr;
	}
	
	public void initData() {
		Question q1 = new Question();
		q1.setDescription("Você conhece Docker?");
		q1.setEnabled(false);
		q1.getOptions().add(new Option("Sim"));
		q1.getOptions().add(new Option("Não"));
		questionRepo.save(q1);
		
		q1 = new Question();
		q1.setDescription("Você conhece Kubernetes?");
		q1.setEnabled(true);
		q1.getOptions().add(new Option("Sim"));
		q1.getOptions().add(new Option("Não"));
		questionRepo.save(q1);
		
		q1 = new Question();
		q1.setDescription("Você conhece o DevOps");
		q1.setEnabled(true);
		q1.getOptions().add(new Option("Sim"));
		q1.getOptions().add(new Option("Não"));
		questionRepo.save(q1);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}
}
