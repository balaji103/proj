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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.his.ar.model.UserModel;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.service.ARService;
import com.his.util.AppConstants;
import com.his.util.PasswordUtil;

/**
 * @author nit This is controller
 */
@Controller
public class ArController {

	@Autowired(required = true)
	public ARService service;

	private static Logger logger = LoggerFactory.getLogger(ArController.class);

	/**
	 * this method return dashboard
	 * 
	 * @return String
	 */
	@RequestMapping("/dashboard")
	public String getDashboard() {
		return "dashboard";
	}

	/**
	 * This method is used for return registration form with UserModel object
	 * 
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/userReg", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		logger.info("getRegistrationForm() is loaded...");
		// create and return UserMoDel object
		UserModel userModel = new UserModel();
		model.addAttribute("formModel", userModel);
		// add user roles to userRole of form
		getUserRole(model);

		return "registration";
	}

	/**
	 * This method used for returning the userRole
	 * 
	 * @param model
	 */
	private void getUserRole(Model model) {
		// add user roles to Model object
		List<String> userRole = new ArrayList<String>();
		userRole.add("Admin");
		userRole.add("Case Worker");
		model.addAttribute("userRole", userRole);
	}

	/**
	 * This method takes registration form , process the form field and save into
	 * the db
	 * 
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
		// add user roles to userRole of form
		getUserRole(model);
		// LVN
		return "registration";
	}

	/**
	 * this method is for checking email Uniqueness
	 * 
	 * @param email
	 * @return StringS
	 */
	@RequestMapping(value = "/userReg/checkEmail")
	public @ResponseBody String checkUserEmail(@RequestParam("email") String email) {
		logger.info("checkUserEmail() is loaded...");
		System.out.println(email);
		UserModel model = service.checkUserMail(email);
		logger.info("controll return to ajax function...");
		//send response to ajax fucnction
		return (model!=null)?AppConstants.DUPLICATE:AppConstants.UNIQUE;
	}// checkUserEmail()

	/**
	 * thismethod is for getting list of case worker using pagination
	 * 
	 * @param cpn
	 * @param model
	 * @return String
	 */
	@RequestMapping("/viewCaseWorkers")
	public String getAllCaseWorker(@RequestParam(name = "cpn", defaultValue = "1") String cpn, Model model) {
		List<UserModel> umList = new ArrayList();
		int currentPageNum = 1;
		// decide current page num
		if (cpn != null && !cpn.equals(" ")) {
			currentPageNum = Integer.parseInt(cpn);
		}

		// use service layer method for getting list of case worker
		Page<ARUserMaster> page = service.findCaseWorker(currentPageNum);
		for (ARUserMaster aum : page) {
			UserModel um = new UserModel();
			BeanUtils.copyProperties(aum, um);
			umList.add(um);
		}

		// get total pages
		int totalPages = page.getTotalPages();

		// add all this content to model attribute
		model.addAttribute("caseWorkers", umList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("cpn", currentPageNum);

		// return lvn
		return "viewCaseWorkers";
	}// getAllCaseWorker()
	
	/**
	 * this method is used for display form along with CaseWorker values
	 * @param userId
	 * @param model
	 * @return String
	 */

	@RequestMapping("/editCaseWorker")
	public String showEditForm(@RequestParam("uid") String userId, Model model) {
		// invoke service class
		UserModel userModel = service.findByUserId(Integer.parseInt(userId));
		// decrypt pwd
		userModel.setUserPwd(PasswordUtil.decrypt(userModel.getUserPwd()));
		// add user roles to userRole of form
		getUserRole(model);
		// add model Attribute
		model.addAttribute("formModel", userModel);
		// return lvn to controller
		return "editCaseWorker";
	}//showEditForm(-,-)

	/**
	 * this method is used for updating caseWorker profile
	 * @param usermodel
	 * @param redirectAttributes
	 * @return String
	 */
	@RequestMapping(value = "/editCaseWorker", method = RequestMethod.POST)
	public String editCaseWorker(@ModelAttribute("formModel") UserModel usermodel,
			RedirectAttributes redirectAttributes) {
		// invoke service class
		boolean status = service.update(usermodel, true);
		// add model Attribute
		if (status) {
			redirectAttributes.addFlashAttribute(AppConstants.SUCCESS, AppConstants.UPDATE_SUCCESS_MSG);
		} else {
			redirectAttributes.addFlashAttribute(AppConstants.ERROR, AppConstants.UPDATE_FAIL_MSG);
		}
		
		//TODO:need to write send email logic 
		
		// return lvn to controller
		return "redirect:viewCaseWorkers";
	}//editCaseWorker(-,-)
	
	@RequestMapping("/activateCaseWorker")
	public String activeCaseWorker(@RequestParam("uid") String userId,RedirectAttributes redirectAttributes) {
		// invoke service class for getting cw profile
		UserModel userModel = service.findByUserId(Integer.parseInt(userId));
		//change activeSw to N
		userModel.setActiveSw(AppConstants.STR_Y);
		//save cw profile
		boolean status = service.update(userModel, AppConstants.FALSE);
		// add model Attribute
		if (status) {
			redirectAttributes.addFlashAttribute(AppConstants.SUCCESS, AppConstants.ACTIVATE_SUCCESS_MSG);
		} else {
			redirectAttributes.addFlashAttribute(AppConstants.ERROR, AppConstants.ACTIVATE_FAIL_MSG);
		}
		
		//TODO:need to write send email logic 
		
		// return lvn to controller
		return "redirect:viewCaseWorkers";
	}
	
	@RequestMapping("/deActivateCaseWorker")
	public String deActiveCaseWorker(@RequestParam("uid") String userId,RedirectAttributes redirectAttributes) {
		// invoke service class for getting cw profile
		UserModel userModel = service.findByUserId(Integer.parseInt(userId));
		//change activeSw to N
		userModel.setActiveSw(AppConstants.STR_N);
		//save cw profile
		boolean status = service.update(userModel, AppConstants.FALSE);
		// add model Attribute
		if (status) {
			redirectAttributes.addFlashAttribute(AppConstants.SUCCESS, AppConstants.DEACTIVATE_SUCCESS_MSG);
		} else {
			redirectAttributes.addFlashAttribute(AppConstants.ERROR, AppConstants.DEACTIVATE_FAIL_MSG);
		}
		
		//TODO:need to write send email logic 
		
		// return lvn to controller
		return "redirect:viewCaseWorkers";
	}

}// class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  