package com.drones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
public class Dron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 100,unique = true)
	private String numSerie;
	@Column
	private String modelo;
	@Column
	private Integer limitePeso;
	@Column
	private Integer capacidadBateria;
	@Column
	private String estado;
	
	public Dron() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dron(String numSerie, String modelo, Integer limitePeso, Integer capacidadBateria, String estado) {
		super();
		this.numSerie = numSerie;
		this.modelo = modelo;
		this.limitePeso = limitePeso;
		this.capacidadBateria = capacidadBateria;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getLimitePeso() {
		return limitePeso;
	}

	public void setLimitePeso(Integer limitePeso) {
		this.limitePeso = limitePeso;
	}

	public Integer getCapacidadBateria() {
		return capacidadBateria;
	}

	public void setCapacidadBateria(Integer capacidadBateria) {
		this.capacidadBateria = capacidadBateria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
