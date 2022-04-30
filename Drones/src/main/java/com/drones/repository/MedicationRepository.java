package com.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.drones.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer>{

	@Query(nativeQuery = true, value = "SELECT * FROM `medication` WHERE `num_serie_dron`= :serial")
	List<Medication> listMedicationByDron(@Param("serial") String serial);
}
