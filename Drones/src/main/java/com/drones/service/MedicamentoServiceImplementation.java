package com.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;
import com.drones.entity.Medicamento;
import com.drones.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImplementation implements MedicamentoService{

	@Autowired
	private MedicamentoRepository repository;
	
	@Override
	public List<Medicamento> listaMedicamentos() {
		return repository.findAll();
	}

	@Override
	public Medicamento guardarMedicamento(Medicamento medicamento) {
		return repository.save(medicamento);
	}

	@Override
	public Medicamento obtenerMedicamentoId(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public Medicamento actualizarMedicamento(Medicamento medicamento) {
		return repository.save(medicamento);
	}

	@Override
	public void eliminarMedicamento(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public List<Medicamento> listaMedicamentosByDron(String numSerie) {
		return repository.listaMedicamentosByDron(numSerie);
	}

}
