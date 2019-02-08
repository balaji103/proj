package com.his.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.his.admin.model.CaseWorkerModel;

/**
 * this interface is provide b.methods
 * @author balaji
 *
 */
public interface AdminService {

	/**
	 * this method is used for register case worker in db
	 * @param model
	 * @return int
	 */
	public int saveUser(CaseWorkerModel model);
	
	/**
	 * this methods is checked email is unique or not
	 * @param emailId
	 * @return String
	 */
	public CaseWorkerModel checkUserMail(String emailId);
	
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
	public CaseWorkerModel findByUserId(Integer userId);
	
	/**
	  * this method is used for update the case worker records
	  * @param userId
	  * @return model
	  */
	public boolean update(CaseWorkerModel model,boolean isEncryptPwd);
	
	public CaseWorkerModel loginUser(CaseWorkerModel userModel);
	
}
