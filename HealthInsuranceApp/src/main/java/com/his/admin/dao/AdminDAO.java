package com.his.admin.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.his.admin.entity.CaseWorkerEntity;


/**
 * @author nit
 *	The implementation class of this project will provide default 
 *  implementation give some predefined persistence logic to us. 
 */
@Repository("arUserMasterdao")
public interface AdminDAO extends JpaRepository<CaseWorkerEntity, Serializable> {

	/**
	 * this method find case worker by email id
	 * @param emailId
	 * @return Integer
	 */
	  @Query("from CaseWorkerEntity cwe where cwe.userEmail=:emailId")
	  public CaseWorkerEntity findByUserEmail(String emailId);
	  
	  @Query("from CaseWorkerEntity cwe where cwe.userEmail=:emailId and cwe.userPwd=:userPwd")
	  public CaseWorkerEntity findUserByEmailAndPwd(String emailId,String userPwd);
	 
}
