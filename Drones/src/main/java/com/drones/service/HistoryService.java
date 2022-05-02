package com.drones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drones.entity.History;

@Service
public interface HistoryService {

	public List<History> listHistory();
	
	public History saveHistory(History history);

}
