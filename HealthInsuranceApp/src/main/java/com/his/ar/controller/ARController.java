package com.his.ar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.his.ar.model.UserModel;
import com.his.ar.service.ARService;
import com.his.util.AppConstants;

@Controller
public class ARController {

	@Autowired
	private ARService arService;
	
	@RequestMapping("/userRegistration")
	public String getUserRegForm(Model model) {
		//create userModel object and put into model scope
		UserModel userModel = new UserModel();
		model.addAttribute("userModel", userModel);
		return "userRegistration";
	}
	
	@RequestMapping(value="/userRegistration",method=RequestMethod.POST)
	public String userRegistrationProcess(@ModelAttribute("userModel") UserModel userModel, Model model) {
		//invoke service layer method 
		userModel=arService.registerUser(userModel);
		//place msg to model scope
		if(userModel.getRegNo()!=null)
			model.addAttribute(AppConstants.SUCCESS,"User registered successful...plz note your Reg no. : "+userModel.getRegNo());
		else
			model.addAttribute(AppConstants.ERROR,"User registration Fail...invalid dob/ssn no.");
		//return lvn
		return "userRegistration";
	}
}
