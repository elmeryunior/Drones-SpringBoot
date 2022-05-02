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

import com.drones.dto.Message;
import com.drones.entity.Dron;
import com.drones.entity.History;
import com.drones.entity.Medication;
import com.drones.service.DronService;
import com.drones.service.HistoryService;
import com.drones.service.MedicationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/drones")
public class DronesController {

	@Autowired
	private DronService dronService;
	@Autowired
	private MedicationService medicationService;
	@Autowired
	private HistoryService historyService;

	@ApiOperation("List of Dron")
	@GetMapping("/listDrones")
	public ResponseEntity<List<Dron>> list() {
		List<Dron> list = dronService.listDrones();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation("Create Dron")
	@PostMapping("/createDron")
	public ResponseEntity<?> create(@RequestBody Dron dron) {

		String[] model = { "Lightweight", "Middleweight", "Cruiserweight", "Heavyweight" };
		String[] state = { "IDLE", "LOADING", "LOADED", "DELIVERING", "DELIVERED", "RETURNING" };

		boolean mod = false, est = false;

		for (int i = 0; i < model.length; i++) {
			if (model[i].equals(dron.getModel())) {
				mod = true;
			}
		}

		for (int i = 0; i < state.length; i++) {
			if (state[i].equals(dron.getState())) {
				est = true;
			}
		}

		if (StringUtils.isBlank(dron.getSerial()))
			return new ResponseEntity(new Message("Serial number is required"), HttpStatus.BAD_REQUEST);
		if (dron.getModel() == null || mod == false)
			return new ResponseEntity(
					new Message("The types of models are:(Lightweight, Middleweight, Cruiserweight, Heavyweight)"),
					HttpStatus.BAD_REQUEST);
		if (dron.getWeight_limit() == null || dron.getWeight_limit() < 0 || dron.getWeight_limit() > 500)
			return new ResponseEntity(new Message("The weight limit is 500 grams"), HttpStatus.BAD_REQUEST);
		if (dron.getBattery_capacity() == null || dron.getBattery_capacity() < 0 || dron.getBattery_capacity() > 100)
			return new ResponseEntity(new Message("Battery capacity should be between 0-100"), HttpStatus.BAD_REQUEST);
		if (dron.getState() == null || est == false)
			return new ResponseEntity(
					new Message("States can be:(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING)"),
					HttpStatus.BAD_REQUEST);
		if (dron.getState().equals("LOADING") && dron.getBattery_capacity() < 25)
			return new ResponseEntity(
					new Message("The drone cannot be in LOADING status if the battery level is less than 25%"),
					HttpStatus.BAD_REQUEST);
		Dron dron2 = new Dron(dron.getSerial(), dron.getModel(), dron.getWeight_limit(), dron.getBattery_capacity(),
				dron.getState());
		dronService.saveDron(dron2);
		return new ResponseEntity(new Message("Dron created"), HttpStatus.OK);
	}

	@ApiOperation("List of drugs given a drone")
	@GetMapping("/medical-articles/{serialDron}")
	public ResponseEntity<List<Medication>> getByNumSerie(@PathVariable("serialDron") String serialDron) {
		if (!dronService.existsByNumSerie(serialDron))
			return new ResponseEntity(new Message("There is no drone with this serial number"), HttpStatus.NOT_FOUND);
		List<Medication> list = medicationService.listMedicationByDron(serialDron);
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation("List of drones enables")
	@GetMapping("/dronEnables")
	public ResponseEntity<List<Dron>> getDronEnable() {
		List<Dron> list = dronService.listDronEnables();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation("Drone battery level")
	@GetMapping("/dronBattery/{serialDron}")
	public ResponseEntity<?> getDronBateria(@PathVariable("serialDron") String serialDron) {
		if (!dronService.existsByNumSerie(serialDron))
			return new ResponseEntity(new Message("There is no drone with this serial number"), HttpStatus.NOT_FOUND);
		Integer cap_battery = dronService.getBattery(serialDron);
		return new ResponseEntity(new Message("The battery level is " + cap_battery + " %"), HttpStatus.OK);
	}

	@ApiOperation("Load a drone with medicines")
	@PostMapping("/loadDron")
	public ResponseEntity<?> UploadDron(@RequestBody Medication medication) {
		if (StringUtils.isBlank(medication.getName()))
			return new ResponseEntity(new Message("The name of the drug is required"), HttpStatus.BAD_REQUEST);
		if (medication.getWeight() == null || medication.getWeight() < 0)
			return new ResponseEntity(new Message("Medication weight must be greater than 0"), HttpStatus.BAD_REQUEST);
		if (medication.getCode() == null)
			return new ResponseEntity(new Message("Medication code is required"), HttpStatus.BAD_REQUEST);
		if (medication.getImage() == null)
			return new ResponseEntity(new Message("Enter Image URL"), HttpStatus.BAD_REQUEST);
		if (medication.getNumSerieDron() == null || dronService.getIdByNumSerie(medication.getNumSerieDron()) == null)
			return new ResponseEntity(new Message("Enter valid ID of the Drone"), HttpStatus.BAD_REQUEST);

		Dron dron = dronService.findById(dronService.getIdByNumSerie(medication.getNumSerieDron()));

		if ((dron.getWeight_limit() + medication.getWeight()) < 500) {
			dron.setWeight_limit((dron.getWeight_limit() + medication.getWeight()));
			dronService.saveDron(dron);
		} else {
			return new ResponseEntity(new Message("Drone with this medication exceeds its load capacity"),
					HttpStatus.BAD_REQUEST);
		}

		Medication medicamento2 = new Medication(medication.getName(), medication.getWeight(), medication.getCode(),
				medication.getImage(), medication.getNumSerieDron());
		medicationService.saveMedication(medicamento2);
		return new ResponseEntity(new Message("Dron uploaded"), HttpStatus.OK);
	}

	@ApiOperation("Register of History")
	@GetMapping("/reghistory")
	public ResponseEntity<List<History>> listHistory() {
		List<History> list = historyService.listHistory();
		return new ResponseEntity(list, HttpStatus.OK);
	}
}
