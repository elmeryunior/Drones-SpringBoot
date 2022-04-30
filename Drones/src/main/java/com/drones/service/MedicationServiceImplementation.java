package com.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;
import com.drones.entity.Medication;
import com.drones.repository.MedicationRepository;

@Service
public class MedicationServiceImplementation implements MedicationService{

	@Autowired
	private MedicationRepository repository;

	@Override
	public List<Medication> listMedication() {
		return repository.findAll();
	}

	@Override
	public List<Medication> listMedicationByDron(String numSerie) {
		return repository.listMedicationByDron(numSerie);
	}

	@Override
	public Medication saveMedication(Medication medication) {
		return repository.save(medication);
	}
	
	
}
