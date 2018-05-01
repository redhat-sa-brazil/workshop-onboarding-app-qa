package com.redhat.workshop.questions.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redhat.workshop.questions.models.Vote;

@RepositoryRestResource(collectionResourceRel = "vote", path = "vote", exported = false)
public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
	Optional<Vote> findByEmail(String email);
}
