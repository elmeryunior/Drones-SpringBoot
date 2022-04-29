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
		
		String [] modelo = {"Lightweight", "Middleweight", "Cruiserweight", "Heavyweight"};
		String [] estado = {"IDLE", "LOADING", "LOADED", "DELIVERING", "DELIVERED", "RETURNING"};
		
		boolean mod  = false,est = false;
		
		for(int i=0;i<modelo.length;i++) {
			if(modelo[i].equals(dron.getModelo())) {
				mod = true;
			}
		}
		
		for(int i=0;i<estado.length;i++) {
			if(estado[i].equals(dron.getEstado())) {
				est = true;
			}
		}
		
        if(StringUtils.isBlank(dron.getNumSerie()))
            return new ResponseEntity(new Mensaje("Serial number is required"), HttpStatus.BAD_REQUEST);
        if(dron.getModelo() == null || mod==false)
           return new ResponseEntity(new Mensaje("The types of models are:(Lightweight, Middleweight, Cruiserweight, Heavyweight)"), HttpStatus.BAD_REQUEST);
        if(dron.getLimitePeso()==null || dron.getLimitePeso()<0 || dron.getLimitePeso()>500)
            return new ResponseEntity(new Mensaje("The weight limit is 500 grams"), HttpStatus.BAD_REQUEST);
        if(dron.getCapacidadBateria()==null || dron.getCapacidadBateria()<0 || dron.getCapacidadBateria()>100)
            return new ResponseEntity(new Mensaje("Battery capacity should be between 0-100"), HttpStatus.BAD_REQUEST);
        if(dron.getEstado()==null || est==false)
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
        List<Medicamento> list = medicamentoService.listaMedicamentosByDron(numSerie);
        return new ResponseEntity(list, HttpStatus.OK);
    }
	
	@ApiOperation("List of drones enables")
    @GetMapping("/dronEnables")
    public ResponseEntity<List<Dron>> getDronDisponible(){
        List<Dron> list = dronService.listaDronEnables();
        return new ResponseEntity(list, HttpStatus.OK);
    }
	
	@ApiOperation("Drone battery level")
    @GetMapping("/dronBattery/{numSerie}")
    public ResponseEntity<?> getDronBateria(@PathVariable("numSerie") String numSerie){
		if(!dronService.existsByNumSerie(numSerie))
            return new ResponseEntity(new Mensaje("There is no drone with this serial number"), HttpStatus.NOT_FOUND);
		Integer cap_battery = dronService.getBattery(numSerie);
        return new ResponseEntity(new Mensaje("The battery level is " + cap_battery + " %"), HttpStatus.OK);
    }
	
	@ApiOperation("Load a drone with medicines")
    @PostMapping("/uploadDron")
    public ResponseEntity<?> UploadDron(@RequestBody Medicamento medicamento){
        if(StringUtils.isBlank(medicamento.getNombre()))
            return new ResponseEntity(new Mensaje("The name of the drug is required"), HttpStatus.BAD_REQUEST);
        if(medicamento.getPeso() == null || medicamento.getPeso()<0)
           return new ResponseEntity(new Mensaje("Medication weight must be greater than 0"), HttpStatus.BAD_REQUEST);
        if(medicamento.getCodigo() == null)
            return new ResponseEntity(new Mensaje("Medication code is required"), HttpStatus.BAD_REQUEST);
        if(medicamento.getImagen() == null)
            return new ResponseEntity(new Mensaje("Enter Image URL"), HttpStatus.BAD_REQUEST);
        if(medicamento.getNumSerieDron() == null)
            return new ResponseEntity(new Mensaje("Enter the ID of the Drone"), HttpStatus.BAD_REQUEST);
        Medicamento medicamento2 = new Medicamento(medicamento.getNombre(), medicamento.getPeso(), medicamento.getCodigo(), medicamento.getImagen(), medicamento.getNumSerieDron());
        medicamentoService.guardarMedicamento(medicamento2);
        return new ResponseEntity(new Mensaje("Dron uploaded"), HttpStatus.OK);
	}
}
