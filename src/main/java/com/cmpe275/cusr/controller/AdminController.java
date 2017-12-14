package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cmpe275.cusr.service.AdminService;


@Controller
public class AdminController {
	@Autowired 
	AdminService adminService;
	
	@GetMapping("/admin")
	public String populateTrainInfo(Model model) {
		adminService.populateTrainTable();
		model.addAttribute("message", "sucessfully loaded train information.");
		return "admin";
		
	}

}
