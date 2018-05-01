package com.redhat.workshop.questions.autoconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.redhat.workshop.questions.models.Option;
import com.redhat.workshop.questions.models.Question;
import com.redhat.workshop.questions.models.Vote;

@Configuration
public class EntityConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Question.class);
		config.exposeIdsFor(Vote.class);
		config.exposeIdsFor(Option.class);
	}
}
