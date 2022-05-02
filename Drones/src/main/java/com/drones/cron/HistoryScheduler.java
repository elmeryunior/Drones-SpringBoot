package com.drones.cron;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.drones.entity.Dron;
import com.drones.entity.History;
import com.drones.service.DronService;
import com.drones.service.HistoryService;

@Component
public class HistoryScheduler {
	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private DronService dronService;

	@Scheduled(cron = "0/30 * * * * *")
	public void schedulerTask() {
		
		List<Dron> drons = dronService.listDrones();

		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if (!drons.isEmpty()) {

			for (int i = 0; i < drons.size(); i++) {

				History history = new History();

				history.setSerial(drons.get(i).getSerial());
				history.setBattery_nivel(drons.get(i).getBattery_capacity());
				history.setFecha(timeStamp);

				historyService.saveHistory(history);
			}
		} else {
			System.out.println("Array empty");
		}
	}
}
