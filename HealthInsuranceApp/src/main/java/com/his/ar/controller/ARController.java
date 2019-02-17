package com.his.ar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.his.ar.entity.UserEntity;
import com.his.ar.model.UserModel;
import com.his.ar.service.ARService;
import com.his.util.AppConstants;

@Controller
public class ARController {
	
	private static final Logger log = LoggerFactory.getLogger(ARController.class);

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
	
	@RequestMapping(value= {"/activeUsers","/viewUsers"})
	public String getActiveUserList(@RequestParam("cpn") String cpn, @RequestParam("userStatus") String userStatus, Model model) {
	
		List<UserModel> userList = new ArrayList();
		
		//default page num
		int currentPageNum = 1;
		
		if(cpn != null && cpn.equals("")) {
			currentPageNum = Integer.parseInt("cpn");
		}
		log.info("invoke service layer getUserList(-,-).....");	
		//invoke service layer method
		Page<UserEntity> page = arService.getUserList(userStatus, currentPageNum);
		log.info("result comes from getUserList(-,-)....."+page);	
		if(page!=null && !page.equals("")) {
			for(UserEntity ue : page) {
				UserModel um = new UserModel();
				BeanUtils.copyProperties(ue,um);
				userList.add(um);
			}
			//get pageSize from page object
			int totalPages = page.getTotalPages();
			//add data to model attribute
			model.addAttribute("userModelList", userList);
			model.addAttribute("cpn", currentPageNum);
			model.addAttribute("totalPages", totalPages);
		}else {
			model.addAttribute("empty", "Users are not available.....");
		}
		//return lvn
		return "viewActiveUsers";
	}
	 
	@RequestMapping("/inActiveUsers")
	public String getInActiveUserList(@RequestParam("cpn") String cpn, @RequestParam("userStatus") String userStatus, Model model) {


		List<UserModel> userList = new ArrayList();
		
		//default page num
		int currentPageNum = 1;
		
		if(cpn != null && cpn.equals("")) {
			currentPageNum = Integer.parseInt("cpn");
		}
		log.info("invoke service layer getUserList(-,-).....");	
		//invoke service layer method
		Page<UserEntity> page = arService.getUserList(userStatus, currentPageNum);
		log.info("result comes from getUserList(-,-)....."+page);	
		if(page!=null && !page.equals("")) {
			for(UserEntity ue : page) {
				UserModel um = new UserModel();
				BeanUtils.copyProperties(ue,um);
				userList.add(um);
			}
			//get pageSize from page object
			int totalPages = page.getTotalPages();
			//add data to model attribute
			model.addAttribute("userModelList", userList);
			model.addAttribute("cpn", currentPageNum);
			model.addAttribute("totalPages", totalPages);
		}else {
			model.addAttribute("empty", "Users are not available.....");
		}
		//return lvn
		return "viewInactiveUsers";
	}
}
