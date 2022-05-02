package com.drones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drones.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer>{

}
