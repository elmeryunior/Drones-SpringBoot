package com.drones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.drones.entity.Dron;

@Repository
public interface DronRepository extends JpaRepository<Dron, Integer>{
	
	boolean existsBySerial(String serial);
	
	@Query(nativeQuery = true, value = "SELECT id FROM `dron` WHERE `serial`= :serial")
	Integer getIdBySerial(@Param("serial") String serial);
	
	@Query(nativeQuery = true, value = "SELECT * FROM `dron` WHERE `weight_limit`< 500")
	List<Dron> listDronEnables();
	
	@Query(nativeQuery = true, value = "SELECT battery_capacity FROM `dron` WHERE `serial`= :serial")
	Integer getBattery(@Param("serial") String serial);
	
	Optional<Dron> findById(Integer id);
}
