package com.redhat.workshop.questions.models;

import java.io.Serializable;
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

@Entity
@Table(name = "question")
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;

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
	
	private Boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", options=" + options + ", active=" + active
				+ "]";
	}
	
	
}
