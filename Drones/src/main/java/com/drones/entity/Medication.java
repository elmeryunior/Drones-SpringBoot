package com.drones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	@Column(name = "name",unique = true)
	@Pattern(regexp = "^([a-zA-Z0-9]+[_-])*[a-zA-Z0-9])+$", message = "Allowed only letters, numbers, -, _")
	private String name;
	@NonNull
	@Column(name = "weight")
	private Integer weight;
	@NonNull
	@Column(name = "code")
	@Pattern(regexp = "^[A-Z_0-9]+$", message = "Allowed only upper case letters, underscore and numbers")
	private String code;
	@NonNull
	@Column(name = "image")
	private String image;
	@NonNull
	@Column(name = "numSerieDron")
    private String numSerieDron; 
	
	public Medication() {
		super();
	}



	public Medication(@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Allowed only letters, numbers, -, _") String name,
			Integer weight,
			@Pattern(regexp = "^[A-Z0-9]+$", message = "Allowed only upper case letters, underscore and numbers") String code,
			String image, String numSerieDron) {
		super();
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
		this.numSerieDron = numSerieDron;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNumSerieDron() {
		return numSerieDron;
	}

	public void setNumSerieDron(String numSerieDron) {
		this.numSerieDron = numSerieDron;
	}

}
