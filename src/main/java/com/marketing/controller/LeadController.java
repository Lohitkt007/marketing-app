package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.entities.Lead;
import com.marketing.services.LeadService;
import com.marketing.utilities.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LeadService leadService;

	@RequestMapping("/veiwCreateLeadPage")
	public String viewCreateLeadpage() {
		return "create_lead";
	}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead")Lead lead, ModelMap model) {
		leadService.saveLead(lead);
		emailService.sendEmail(lead.getEmail(), "Welcome", "hello welcome to our page");
		model.addAttribute("msg","lead is saved");
		return "create_lead";
	}
	
	@RequestMapping("/listall")
	public String getAllLeads(ModelMap model) {
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads);
		return "lead_search_result";
	}
	
	@RequestMapping("/delete")
	public String deleteLeadById(@RequestParam("id")long id, ModelMap model) {
		leadService.deleteOneLead(id);
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads);
		return "lead_search_result";
		
	}
	
	@RequestMapping("/update")
	public String uodateOneLead(@RequestParam("id") long id, ModelMap model) {
		Lead lead = leadService.findOneLead(id);
		model.addAttribute("lead",lead );
		return "update_lead";
		
	}
	
	@RequestMapping("/updateLead")
	public String updateLead(@ModelAttribute("lead") Lead lead, ModelMap model) {
		leadService.saveLead(lead);
		model.addAttribute("msg", "Lead with id: "+lead.getId() + "is updated");
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads);
		return "lead_search_result";
		
	}
}
