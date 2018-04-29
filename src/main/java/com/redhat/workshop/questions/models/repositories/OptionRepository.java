package com.redhat.workshop.questions.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redhat.workshop.questions.models.Option;

@RepositoryRestResource(collectionResourceRel = "option", path = "option", exported = false)
public interface OptionRepository extends PagingAndSortingRepository<Option, Long>{

}
