package com.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;
import com.drones.repository.DronRepository;

@Service
public class DronServiceImplementation implements DronService{
	
	@Autowired
	private DronRepository repository;

	@Override
	public List<Dron> listDrones() {
		return repository.findAll();
	}

	@Override
	public List<Dron> listDronEnables() {
		return repository.listDronEnables();
	}

	@Override
	public boolean existsByNumSerie(String numSerie) {
		return repository.existsBySerial(numSerie);
	}

	@Override
	public Integer getIdByNumSerie(String numSerie) {
		return repository.getIdBySerial(numSerie);
	}

	@Override
	public Integer getBattery(String numSerie) {
		return repository.getBattery(numSerie);
	}

	@Override
	public Dron saveDron(Dron dron) {
		return repository.save(dron);
	}

	@Override
	public Dron findById(Integer id) {
		return repository.findById(id).get();
	}



}
