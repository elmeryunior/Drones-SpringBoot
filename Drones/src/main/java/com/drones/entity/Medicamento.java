package com.drones.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Column(unique = true)
	private String nombre;
	@NotBlank
	@Column
	private Integer peso;
	@NotBlank
	@Column
	private String codigo;
	@NotBlank
	@Column
	private String imagen;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dron dron;
	
	public Medicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicamento(Integer id, @NotBlank String nombre, @NotBlank Integer peso, @NotBlank String codigo,
			@NotBlank String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peso = peso;
		this.codigo = codigo;
		this.imagen = imagen;
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

	public Dron getDron() {
		return dron;
	}

	public void setDron(Dron dron) {
		this.dron = dron;
	}

	
}
