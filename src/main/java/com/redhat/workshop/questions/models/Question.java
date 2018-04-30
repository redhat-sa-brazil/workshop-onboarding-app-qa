package com.redhat.workshop.questions.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "Question")
@Data
public class Question{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max = 200, min = 5, message = "Descrição deve ter no minimo 5 e no máximo 200 chars")
	private String description;
	
	@OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	@Column(nullable = false)
	private List<Option> options = new ArrayList<Option>();
	
	@Column(columnDefinition = "BOOLEAN")
	@NotNull
	private Boolean enabled;
}
