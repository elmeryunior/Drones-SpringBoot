package com.drones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drones.entity.Dron;

@Service
public interface DronService {

	public List<Dron> listaDrones();
	
	public Dron guardarDron(Dron dron);

	public Dron obtenerDronId(Integer id);

	public Dron actualizarDron(Dron dron);

	public void eliminarDron(Integer id);
	
	public boolean existsByNumSerie(String numSerie);
	
	public Integer getIdByNumSerie(String numSerie);
}
