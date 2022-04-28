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
	@NotBlank(message = "Debe introducir un número de serie")
	@Column(length = 100,unique = true)
	private String numSerie;
	@NotBlank(message = "Debe seleccionar un modelo")
	@Column
	private String modelo;
	@NotBlank(message = "Este campo es obligatorio")
	@Column
	private Integer limitePeso;
	@NotBlank(message = "Este campo es obligatorio")
	@Column
	private Double capacidadBateria;
	@NotBlank(message = "Debe seleccionar un estado")
	@Column
	private String estado;
	
	public Dron() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dron(@NotBlank(message = "Debe introducir un número de serie") @Max(100) String numSerie,
			@NotBlank(message = "Debe seleccionar un modelo") String modelo,
			@NotBlank(message = "Este campo es obligatorio") Integer limitePeso,
			@NotBlank(message = "Este campo es obligatorio") Double capacidadBateria,
			@NotBlank(message = "Debe seleccionar un estado") String estado) {
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

	public Double getCapacidadBateria() {
		return capacidadBateria;
	}

	public void setCapacidadBateria(Double capacidadBateria) {
		this.capacidadBateria = capacidadBateria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
