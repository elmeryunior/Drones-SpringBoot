package com.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.drones.entity.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM `medicamento` WHERE `num_serie_dron`= :numSerie")
	List<Medicamento> listaMedicamentosByDron(@Param("numSerie") String numSerie);
}
