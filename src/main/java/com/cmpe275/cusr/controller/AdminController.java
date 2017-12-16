package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpe275.cusr.service.AdminService;


@Controller
public class AdminController {
	@Autowired 
	AdminService adminService;
	
	@GetMapping("/admin")
	public String populateTrainInfo(Model model) {
		model.addAttribute("message", "Please populate train schedule table and status table.");
		return "admin";
		
	}
	
	@PostMapping(value ="/populateTable", params="trainScheduleTable")
	public String populateTrainSchedule(Model model) {
		adminService.populateTrainTable();
		model.addAttribute("message", "sucessfully loaded train schedule information.");
		return "admin";
	}
	
	@PostMapping(value ="/populateTable", params="trainStatusTable")
	public String populateTrainStatus(Model model) {
		adminService.populateTrainStatus();
		model.addAttribute("message", "sucessfully loaded train status information for next 4 weeks.");
		return "admin";
	}
	
	@PostMapping(value ="/populateTable", params="updateCapacity")
	public String updateTrainCapacity(Model model, @RequestParam("capacity") String capacity) {
		adminService.updateTrainCapacity(Integer.valueOf(capacity));
		model.addAttribute("message", "sucessfully updated train capacity as" + capacity);
		return "admin";
	}

}
