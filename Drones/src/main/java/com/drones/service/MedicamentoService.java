package com.drones.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.drones.entity.Dron;
import com.drones.entity.Medicamento;

@Service
public interface MedicamentoService {

	public List<Medicamento> listaMedicamentos();
	
	public List<Medicamento> listaMedicamentosByDron(Integer dronId);
	
	public Medicamento guardarMedicamento(Medicamento medicamento);

	public Medicamento obtenerMedicamentoId(Integer id);

	public Medicamento actualizarMedicamento(Medicamento medicamento);

	public void eliminarMedicamento(Integer id);
}
