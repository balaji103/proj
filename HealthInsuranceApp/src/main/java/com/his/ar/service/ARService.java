package com.his.ar.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.his.ar.model.UserModel;

/**
 * this interface is provide b.methods
 * @author balaji
 *
 */
public interface ARService {

	/**
	 * this method is used for register case worker in db
	 * @param model
	 * @return int
	 */
	public int saveUser(UserModel model);
	
	/**
	 * this methods is checked email is unique or not
	 * @param emailId
	 * @return String
	 */
	public String checkUserMail(String emailId);
	
	/**
	 * this method is used give the case worker list using pagination
	 * @param cpn
	 * @return Pageable
	 */
	public Page findCaseWorker(Integer cpn);
	
	 /**
	  * this method is used for deactivate the case worker
	  * @param userId
	  * @return model
	  */
	public UserModel findByUserId(Integer userId);
	
	/**
	  * this method is used for update the case worker records
	  * @param userId
	  * @return model
	  */
	public boolean update(UserModel model,boolean isEncryptPwd);
	
	
	
}
