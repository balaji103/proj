package com.his.ar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserRegistrationDAO;
import com.his.ar.entity.UserEntity;
import com.his.ar.model.UserModel;

@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired
	private ARUserRegistrationDAO arUserRegistrationDao;
	@Override
	public UserModel registerUser(UserModel model) {
		//combine ssn1,ssn2 and ssn3 in one ssn and store into db
		
		//convert model to entity class obj
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(model, entity);
		//combine ssn1,ssn2 and ssn3 in one ssn and store into db
		entity.setSsn(model.getSsn1()+model.getSsn2()+model.getSsn3());
		//invoke DAO layer method
		entity=arUserRegistrationDao.save(entity);
		//convert entity to model class object
		BeanUtils.copyProperties(entity,model);
		//usermodel return to controller
		return model;
	}

}
