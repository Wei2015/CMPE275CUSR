package com.cmpe275.cusr.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmpe275.cusr.model.AllTrainContent;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.service.AdminService;


@Controller
@SessionAttributes(value={"trainContent"})
public class AdminController {
	@Autowired 
	AdminService adminService;
	
	@GetMapping("/admin")
	public String populateTrainInfo(Model model) {
		List<Train> trains = adminService.showTrainCapacity();
		if (trains.size()>0) {
			model.addAttribute("trainContent", new AllTrainContent(trains));
			model.addAttribute("message1", "You have already loaded train information");
		}else {
			model.addAttribute("trainContent", new AllTrainContent());
			model.addAttribute("message1", "Please populate train schedule table.");
		}
		return "admin";
		
	}
	
	@PostMapping(value ="/populateTable", params="trainScheduleTable")
	public String populateTrainSchedule(Model model) {
		adminService.populateTrainTable();
		model.addAttribute("message1", "Successfully loaded train schedule information.");
		model.addAttribute("message2", "Please populate train status table.");
		return "admin";
	}
	
	@PostMapping(value ="/populateTable", params="trainStatusTable")
	public String populateTrainStatus(Model model) {
		adminService.populateTrainStatus();
		model.addAttribute("message1", "Successfully loaded train schedule information.");
		model.addAttribute("message2", "Successfully loaded train status information for next 4 weeks.");
		return "admin";
	}
	
	@PostMapping(value ="/populateTable", params="trainCapacity")
	public String showTrainCapacity(@ModelAttribute("trainContent") AllTrainContent trainContent, Model model) {
		trainContent.setTrains(adminService.showTrainCapacity());
		return "admin";
	}
	
	
	
	@PostMapping(value ="/populateTable", params="updateCapacity")
	public String updateTrainCapacity(Model model, @RequestParam("capacity") String capacity,
									@ModelAttribute("trainContent") AllTrainContent trainContent) {
		adminService.updateTrainCapacity(Integer.valueOf(capacity));
		trainContent.setTrains(adminService.showTrainCapacity());
		return "admin";
	}
	

	@PostMapping(value ="/cancelTrain", params = "cancelTrain")
	public String cancelTrain(Model model, @RequestParam("trainBound") String trainBound, @RequestParam("cancelDate") String cancelDate) {
		adminService.trainCancel(trainBound, cancelDate);
		model.addAttribute("messageCancel", "Sucessfully cancelled train " + trainBound + " on " + cancelDate + ", and rebooked associated tickets.");
		return "admin";
	}
	
	@PostMapping(value ="/reset")
	public String resetSystem(Model model, @ModelAttribute("trainContent") AllTrainContent trainContent) {
		adminService.reset();
		trainContent.setTrains(adminService.showTrainCapacity());
		model.addAttribute("message", "Sucessfully reset the booking system");
		return "admin";
	}
	

}
