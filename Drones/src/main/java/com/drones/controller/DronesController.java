package com.drones.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drones.dto.Mensaje;
import com.drones.entity.Dron;
import com.drones.entity.Medicamento;
import com.drones.service.DronService;
import com.drones.service.MedicamentoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drones")
public class DronesController {

	@Autowired
	private DronService dronService;
	@Autowired
	private MedicamentoService medicamentoService;
	
	@ApiOperation("List of Dron")
    @GetMapping("/listDrones")
    public ResponseEntity<List<Dron>> list(){
        List<Dron> list = dronService.listaDrones();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
	@ApiOperation("Create Dron")
    @PostMapping("/createDron")
    public ResponseEntity<?> create(@RequestBody Dron dron){
        if(StringUtils.isBlank(dron.getNumSerie()))
            return new ResponseEntity(new Mensaje("Serial number is required"), HttpStatus.BAD_REQUEST);
        if(dron.getModelo()==null || !dron.getModelo().equals("Lightweight") || !dron.getModelo().equals("Middleweight") || !dron.getModelo().equals("Cruiserweight") || !dron.getModelo().equals("Heavyweight"))
            return new ResponseEntity(new Mensaje("The types of models are:(Lightweight, Middleweight, Cruiserweight, Heavyweight)"), HttpStatus.BAD_REQUEST);
        if(dron.getLimitePeso()==null || dron.getLimitePeso()<0 || dron.getLimitePeso()>500)
            return new ResponseEntity(new Mensaje("The weight limit is 500 grams"), HttpStatus.BAD_REQUEST);
        if(dron.getCapacidadBateria()==null || dron.getCapacidadBateria()<0 || dron.getCapacidadBateria()>100)
            return new ResponseEntity(new Mensaje("Battery capacity should be between 0-100"), HttpStatus.BAD_REQUEST);
        if(dron.getEstado()==null || !dron.getEstado().equals("IDLE") || !dron.getEstado().equals("LOADING") || !dron.getEstado().equals("LOADED") || !dron.getEstado().equals("DELIVERING") || !dron.getEstado().equals("DELIVERED") || !dron.getEstado().equals("RETURNING"))
            return new ResponseEntity(new Mensaje("States can be:(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING)"), HttpStatus.BAD_REQUEST);
        if(dron.getEstado().equals("LOADING") && dron.getCapacidadBateria() < 25)
            return new ResponseEntity(new Mensaje("The drone cannot be in LOADING status if the battery level is less than 25%"), HttpStatus.BAD_REQUEST);
        Dron dron2 = new Dron(dron.getNumSerie(), dron.getModelo(),dron.getLimitePeso(),dron.getCapacidadBateria(),dron.getEstado());
        dronService.guardarDron(dron2);
        return new ResponseEntity(new Mensaje("Dron created"), HttpStatus.OK);
    }
    
	@ApiOperation("List of drugs given a drone")
    @GetMapping("/medical-articles/{numSerie}")
    public ResponseEntity<List<Medicamento>> getByNumSerie(@PathVariable("numSerie") String numSerie){
        if(!dronService.existsByNumSerie(numSerie))
            return new ResponseEntity(new Mensaje("There is no drone with this serial number"), HttpStatus.NOT_FOUND);
        List<Medicamento> list = medicamentoService.listaMedicamentosByDron(dronService.getIdByNumSerie(numSerie));
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
