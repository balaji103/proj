package com.his.ar.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.his.ar.entity.ARUserMaster;


/**
 * @author nit
 *	The implementation class of this project will provide default 
 *  implementation give some predefined persistence logic to us. 
 */
@Repository("arUserMasterdao")
public interface ARUserMasterDAO extends JpaRepository<ARUserMaster, Serializable> {

	/**
	 * this method find case worker by email id
	 * @param emailId
	 * @return Integer
	 */
	  @Query("from ARUserMaster ar where ar.userEmail=:emailId")
	  public ARUserMaster findByUserEmail(String emailId);
	  
	  @Query("from ARUserMaster ar where ar.userEmail=:emailId and ar.userPwd=:userPwd")
	  public ARUserMaster findUserByEmailAndPwd(String emailId,String userPwd);
	 
}
