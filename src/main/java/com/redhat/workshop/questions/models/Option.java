package com.redhat.workshop.questions.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "option")
@Data
public class Option{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max = 70, min = 1, message = "Opção deve ter no minimo 1 e no máximo 70")
	private String option;
	
	public Option(String option) {
		this.option = option;
	}
}
