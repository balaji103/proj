package com.his.ar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.ar.bindings.IndvDetailResponse;
import com.his.ar.dao.ARUserRegistrationDAO;
import com.his.ar.entity.UserEntity;
import com.his.ar.model.UserModel;
import com.his.ar.ws.consumer.SsnValidatorService;
import com.his.util.AppConstants;

@Service("arService")
public class ARServiceImpl implements ARService {
	
	@Autowired
	private SsnValidatorService ssnValidatorService;

	@Autowired
	private ARUserRegistrationDAO arUserRegistrationDao;
	
	@Override
	public UserModel registerUser(UserModel model) {
		UserModel userModel=null;
		//combine ssn1,ssn2 and ssn3 in one ssn and store into db
		String ssn = model.getSsn1()+model.getSsn2()+model.getSsn3();
		
		//hit federal gov project and validate ssn no is valid or not
	//IndvDetailResponse response = ssnValidatorService.validateUserBySsnAndDob(model.getDob(),Long.parseLong(ssn));
	//if(response != null) {
		//convert model to entity class obj
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(model, entity);
		//combine ssn1,ssn2 and ssn3 in one ssn and store into db
		entity.setSsn(ssn);
		entity.setUserStatus(AppConstants.STR_N);
		//invoke DAO layer method
		entity=arUserRegistrationDao.save(entity);
		//convert entity to model class object
		if(entity!=null) {
			userModel = new UserModel();
			BeanUtils.copyProperties(entity,userModel);
		}
	//}
		//usermodel return to controller
		return userModel;
	}
	
	/**
	 * 
	 * @return
	 */
	public Page<UserEntity> getUserList(String userStatus, int cpn) {
		//create pageable object
		Pageable pageable = new PageRequest(cpn-1, AppConstants.PAGE_SIZE);
		Page page = arUserRegistrationDao.findAllByUserStatus(userStatus, pageable);
		return page;
	}

}
