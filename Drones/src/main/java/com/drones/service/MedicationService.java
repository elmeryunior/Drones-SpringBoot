package com.drones.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;
import com.drones.entity.Medication;

@Service
public interface MedicationService {

	public List<Medication> listMedication();
	
	public List<Medication> listMedicationByDron(String numSerie);
	
	public Medication saveMedication(Medication medication);
}
