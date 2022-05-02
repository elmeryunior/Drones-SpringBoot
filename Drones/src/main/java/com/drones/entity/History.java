package com.drones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "serial")
	@NonNull
	private String serial;
	
	@Column(name = "battery_nivel")
	@NonNull
	private Integer battery_nivel;
	
	@Column(name = "fecha")
	@NonNull
	public String fecha;
	
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(String serial, Integer battery_nivel, String fecha) {
		super();
		this.serial = serial;
		this.battery_nivel = battery_nivel;
		this.fecha = fecha;
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

	public Integer getBattery_nivel() {
		return battery_nivel;
	}

	public void setBattery_nivel(Integer battery_nivel) {
		this.battery_nivel = battery_nivel;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
