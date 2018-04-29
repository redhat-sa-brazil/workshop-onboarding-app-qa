package com.redhat.workshop.questions.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "option")
public class Option implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max = 70, min = 1, message = "Opção deve ter no minimo 1 e no máximo 70")
	private String option;
	
	public Option() {
		// TODO Auto-generated constructor stub
	}
	
	public Option(String option) {
		this.option = option;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", option=" + option + "]";
	}
}
