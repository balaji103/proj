package com.his.ar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="AR_USER_MASTER")
public class UserEntity {
	@Id
	@GenericGenerator(name = "USER_REG_NO", strategy = "com.his.generator.UserRegistrationNoGenerator")
    @GeneratedValue(generator = "USER_REG_NO")
	@Column(name="REG_NO", length=20)
	private String regNo;
	
	@Column(name="FIRST_NAME", length=20)
	private String firstName;
	
	@Column(name="MIDDLE_NAME", length=20)
	private String middleName;
	
	@Column(name="LAST_NAME", length=20)
	private String lastName;
	
	@Column(name="GENDER", length=10)
	private String gender;
	
	@Column(name="EMAIL_ID", length=30)
	private String emailId;
	
	@Column(name="PHONE_NO", length=20)
	private String phno;
	
	@Column(name="SSN_NO", length=12)
	private String ssn;
	
	@Column(name="DOB", length=15)
	private String dob;

}
