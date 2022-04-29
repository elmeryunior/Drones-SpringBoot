package com.drones.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String nombre;
	@Column
	private Integer peso;
	@Column
	private String codigo;
	@Column
	private String imagen;
	@Column
    private String numSerieDron; 
	
	public Medicamento() {
		super();
	}

	public Medicamento(String nombre, Integer peso, String codigo, String imagen, String numSerieDron) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.codigo = codigo;
		this.imagen = imagen;
		this.numSerieDron = numSerieDron;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNumSerieDron() {
		return numSerieDron;
	}

	public void setNumSerieDron(String numSerieDron) {
		this.numSerieDron = numSerieDron;
	}

	
}
