package com.his.admin.model;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @author nit This is a UserModel (Entity / Value Object) Used to transfer the
 *         data
 */
@Data
public class CaseWorkerModel {

	/**
	 * this field is specifies identity value for
	 * case worker
	 */
	private int userId;
	
	/**
	 * this  field is specifies first name of case worker
	 */
	private String firstName;
	
	/**
	 * this field is specifies last name of case worker
	 */
	private String lastName;
	
	/**
	 * this field is specifies Email id of case worker
	 */
	private String userEmail;
	
	/**
	 * this field is specifies Password of case worker
	 */
	private String userPwd;
	
	/**
	 * this field is specifies DOB of case worker
	 */
	private String userDob;
	
	/**
	 * this field is specifies phone num of case worker
	 */
	private String userPhno;
	
	/**
	 * this field is specifies Role of case worker
	 */
	private String userRole;
	
	/**
	 * this field is specify that case worker is active or not
	 */
	private String activeSw;
	
	/**
	 * this field is specify when case worker is registered
	 */
	private Timestamp createDate;
	
	/**
	 * this field is specify when case worker is updated last time
	 */
	private Timestamp updatedDate;
	
	/**
	 * this field is specify by whom case worker is registered
	 */
	private String createdBy;
	
	/**
	 * this field is specify by whom case worker is updated last time
	 */
	private String updatedBy;
	
}//class
