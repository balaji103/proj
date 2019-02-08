package com.his.admin.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.TituloEleitoral;

import lombok.Data;


/**
 * @author nit
 * Entity class case worker registration
 * 
 */
@Entity
@Table(name="ADMIN_CASEWORKER_MASTER")
@Data
public class CaseWorkerEntity {
	
	/**
	 * this field is used to specify pk in db table
	 */
	@Id
	@SequenceGenerator(name="ADMIN_CASEWORKER_MASTER_ID_SEQ", initialValue=1001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMIN_CASEWORKER_MASTER_ID_SEQ")
	@Column(name="USER_ID" , length=10)
	private int userId;
	
	/**
	 * this  field is specifies first name of case worker in db
	 */
	@Column(name="FIRST_NAME",length=30)
	private String firstName;
	
	/**
	 * this  field is specifies last name of case worker in db
	 */
	@Column(name="LAST_NAME",length=30)
	private String lastName;
	
	/**
	 * this field is specifies Email id of case worker in db
	 */
	@Column(name="EMAIL",length=30)
	private String userEmail;
	
	/**
	 * this field is specifies Password of case worker in db
	 */
	@Column(name="PASSWORD",length=100)
	private String userPwd;
	
	/**
	 * this field is specifies DOB of case worker in db
	 */
	@Column(name="DOB")
	private String userDob;
	
	/**
	 * this field is specifies phone num of case worker in db
	 */
	@Column(name="PHNO",length=20)
	private String userPhno;
	

	/**
	 * this field is specifies Role of case worker indb
	 */
	@Column(name="ROLE",length=20)
	private String userRole;
	
	/**
	 * this field is specify that case worker is active or not
	 */
	@Column(name="ACTIVE_SW",length=10)
	private String activeSw;
	
	/**
	 * this field is specify when case worker is registered
	 */
	@Column(name="CRATED_DATE")
	@CreationTimestamp
	private Timestamp createDate;
	
	/**
	 * this field is specify when case worker is updated last time
	 */
	@Column(name="UPDATED_DATE")
	@UpdateTimestamp
	private Timestamp updatedDate;
	
	/**
	 * this field is specify by whom case worker is registered
	 */
	@Column(name="CREATED_BY",length=30)
	private String createdBy;
	
	
	/**
	 * this field is specify by whom case worker is updated last time
	 */
	@Column(name="UPDATED_BY",length=30)
	private String updatedBy;

}//close class
