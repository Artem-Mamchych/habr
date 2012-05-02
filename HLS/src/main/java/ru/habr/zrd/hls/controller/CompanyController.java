package ru.habr.zrd.hls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.habr.zrd.hls.service.CompanyService;

@Controller
@RequestMapping("rest/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/{respType:customers|suppliers}", method = RequestMethod.GET)
	public void getXML(@PathVariable String respType, Model model) {
		String fetchProfile = ("customers".equals(respType)) ? "companyWithCustomers" : "companyWithSuppliers";
		model.addAttribute(companyService.getCompany(fetchProfile));
	}
}