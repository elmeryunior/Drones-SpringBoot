package com.drones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

@Entity
public class Dron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "serial", length = 100, unique = true)
	@NonNull
	private String serial;
	@NonNull
	@Column(name = "model")
	private String model;
	@Column(name = "weight_limit")
	@NonNull
	private Integer weight_limit;
	@Column(name = "battery_capacity")
	@NonNull
	private Integer battery_capacity;
	@Column(name = "state")
	@NonNull
	private String state;

	public Dron() {
		super();
	}

	public Dron(String serial, String model, Integer weight_limit, Integer battery_capacity, String state) {
		super();
		this.serial = serial;
		this.model = model;
		this.weight_limit = weight_limit;
		this.battery_capacity = battery_capacity;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWeight_limit() {
		return weight_limit;
	}

	public void setWeight_limit(Integer weight_limit) {
		this.weight_limit = weight_limit;
	}

	public Integer getBattery_capacity() {
		return battery_capacity;
	}

	public void setBattery_capacity(Integer battery_capacity) {
		this.battery_capacity = battery_capacity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
