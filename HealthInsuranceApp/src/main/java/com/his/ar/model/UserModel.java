package com.his.ar.model;

import java.sql.Date;

import lombok.Data;

@Data
public class UserModel {
	
	private String regNo;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String emailId;
	private String phno;
	private String ssn1;
	private String ssn2;
	private String ssn3;
	private String dob;
	
	private String createdBy = "balaji";
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private String userStatus;

}
