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
	public List<Dron> listaDrones() {
		return repository.findAll();
	}

	@Override
	public Dron guardarDron(Dron dron) {
		return repository.save(dron);
	}

	@Override
	public Dron obtenerDronId(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public Dron actualizarDron(Dron dron) {
		return repository.save(dron);
	}

	@Override
	public void eliminarDron(Integer id) {	
		repository.deleteById(id);
	}

	@Override
    public boolean existsByNumSerie(String numSerie){
        return repository.existsByNumSerie(numSerie);
    }

	@Override
	public Integer getIdByNumSerie(String numSerie) {
		return repository.getIdByNumSerie(numSerie);
	}

	@Override
	public List<Dron> listaDronEnables() {
		return repository.listaDronEnables();
	}

	@Override
	public Integer getBattery(String numSerie) {
		return repository.getBattery(numSerie);
	}

}
