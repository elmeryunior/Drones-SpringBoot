package com.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drones.entity.History;
import com.drones.repository.HistoryRepository;

@Service
public class HistoryServiceImplementation implements HistoryService{
	
	@Autowired
	private HistoryRepository repository;

	@Override
	public List<History> listHistory() {
		return repository.findAll();
	}

	@Override
	public History saveHistory(History history) {
		return repository.save(history);
	}

}
