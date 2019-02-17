package com.his.ar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.his.ar.entity.UserEntity;
import com.his.ar.model.UserModel;
import com.his.util.AppConstants;

public interface ARService {
	
	public UserModel registerUser(UserModel userModel); 
	
	public Page<UserEntity> getUserList(String userStatus, int cpn);

}
