package com.drones.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;

@Service
public interface DronService {

	public List<Dron> listDrones();
	
	public List<Dron> listDronEnables();
	
	public Dron saveDron(Dron dron);
	
	public Dron findById(Integer id);
	
	public boolean existsByNumSerie(String numSerie);
	
	public Integer getIdByNumSerie(String numSerie);
	
	public Integer getBattery(String numSerie);
}
