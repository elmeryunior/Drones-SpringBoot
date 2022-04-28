package com.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.drones.entity.Dron;

@Repository
public interface DronRepository extends JpaRepository<Dron, Integer>{
	
	boolean existsByNumSerie(String numSerie);
	
	@Query(nativeQuery = true, value = "SELECT id FROM `dron` WHERE `num_serie`= :numSerie")
	Integer getIdByNumSerie(@Param("numSerie") String numSerie);
}
