package com.redhat.workshop.questions.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Options")
public class Option{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max = 70, min = 1, message = "Opção deve ter no minimo 1 e no máximo 70")
	private String description;
	
	@OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	@JsonIgnore
	private List<Answer> votes = new ArrayList<Answer>();
	
	public Option() {
		
	}
	
	public Option(String description) {
		this.description = description;
	}
}
