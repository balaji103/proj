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
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.ar.model.UserModel;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.service.ARService;
import com.his.util.AppConstants;

/**
 * @author nit This is controller
 */
@Controller
public class ArController {

	@Autowired(required = true)
	public ARService service;
	
	private static Logger logger =LoggerFactory.getLogger(ArController.class);
	/**
	 * This method is used for return registration form with UserModel object
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/userReg", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		logger.info("getRegistrationForm() is loaded...");
		//create and return UserMoDel object
		UserModel userModel = new UserModel();
		model.addAttribute("formModel", userModel);
		//add user roles to userRole of form
		getUserRole(model);
		
		return "registration";
	}

	/**
	 * This method used for returning the userRole
	 * @param model
	 */
	private void getUserRole(Model model) {
		//add user roles to Model object
		List<String> userRole=new ArrayList<String>();
		userRole.add("Admin");
		userRole.add("Case Worker");
		model.addAttribute("userRole",userRole);
	}
	
	/**
	 * This method takes registration form , process the form field and
	 * save into the db
	 * @param userModel
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "/userReg", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("formModel") UserModel userModel, Model model) {
		logger.info("registerUser() is loaded...");
		// Use service
		int count = service.saveUser(userModel);
		if (count != 0) {
			model.addAttribute(AppConstants.SUCCESS, AppConstants.SUCCESS_MSG);
		} else {
			model.addAttribute(AppConstants.ERROR, AppConstants.ERROR_MSG);
		}
		//add user roles to userRole of form
				getUserRole(model);
		// LVN
		return "registration";
	}
	
	
	/**
	 * this method is for checking email Uniqueness
	 * @param email
	 * @return StringS
	 */
	  @RequestMapping(value="/userReg/checkEmail") 
	  public @ResponseBody String checkUserEmail(@RequestParam("email") String email) { 
		  logger.info("checkUserEmail() is loaded...");
		  System.out.println(email);
		  String ret = service.checkUserMail(email); 
		  System.out.println(ret);
		  logger.info("controll return to ajax function...");
		  return ret;
	  }//checkUserEmail()
	  
	  /**
	   * thismethod is for getting list of case worker using pagination
	   * @param cpn
	   * @param model
	   * @return String
	   */
	  @RequestMapping("/viewCaseWorkers")
	  public String getAllCaseWorker(@RequestParam("cpn") String cpn,Model model) {
		  List<UserModel> umList = new ArrayList();
		  int currentPageNum = 1;
		  //decide current page num
		  if(cpn!=null && !cpn.equals(" ")) {
			  currentPageNum = Integer.parseInt(cpn);
		  }
		  
		  //use service layer method for getting list of case worker
		  Page<ARUserMaster> page = service.findCaseWorker(currentPageNum);
		  for(ARUserMaster aum : page) {
			  UserModel um = new UserModel();
			  BeanUtils.copyProperties(aum,um);
			  umList.add(um);
		  }
		  
		  //get total pages
		  int totalPages = page.getTotalPages();
		  
		  //add all this content to model attribute
		  model.addAttribute("caseWorkers",umList);
		  model.addAttribute("totalPages",totalPages);
		  model.addAttribute("cpn",currentPageNum);
		  
		  //return lvn
		  return "viewCaseWorkers";
	  }//getAllCaseWorker()
	 
}// class
