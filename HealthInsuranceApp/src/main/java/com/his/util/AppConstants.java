package com.his.util;

/**
 * this class provides constants
 * @author nit
 *
 */
public class AppConstants {
	/**
	 * this field specify registration Success key
	 */
	public static final String SUCCESS = "SUCCESS";
	
	/**
	 * this field specify registration success msg
	 */
	public static final String SUCCESS_MSG ="Registration Successfully..";
	
	/**
	 * this field specify registration error key
	 */
	public static final String ERROR = "ERROR";
	
	/**
	 * this field specify registration error msg
	 */
	public static final String ERROR_MSG = "Not Registered..";
	
	/**
	 * this field specify record is duplicate
	 */
	public static final String DUPLICATE = "DUPLICATE";
	

	/**
	 * this field specify record is unique
	 */
	public static final String UNIQUE = "VALID";
	

	/**
	 * this field specify email id from which we will sending 
	 * registration email to case worker
	 */
	public static final String EMAIL_FROM = "bmudholkar3@gmail.com";
	
	/**
	 * this field specify subject for email which we will sending 
	 * registered case worker
	 */
	public static final String EMAIL_SUBJECT = "HIS Application Registration succcessfull";
	
	public static final Integer PAGE_SIZE = 3;
	
	public static final String STR_Y = "Y";
	
	public static final String STR_N = "N";
	
	public static final String FORGOTPWD_SUCCESS_MSG = "Pwd is send to registered emailId, Click here for <a href='login'>Login</a>";
	public static final String DEACTIVATE_SUCCESS_MSG = "Case Worker Deactivated !!! ";
	
	public static final String FORGOTPWD_FAIL_MSG = "invalid emailid , plz enter valid emailid";
	public static final String DEACTIVATE_FAIL_MSG = "Case Worker Deactivation fail !!! ";
	
	//public static final String ACTIVATE_SUCCESS = "ACTIVE_SUCCESS";
	public static final String ACTIVATE_SUCCESS_MSG = "Case Worker Activated !!! ";
	
	//public static final String ACTIVATE_FAIL = "ACTIVE_FAIL";
	public static final String ACTIVATE_FAIL_MSG = "Case Worker Activation fail !!! ";
	
	//public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	public static final String UPDATE_SUCCESS_MSG = "Case Worker Updated successfully !!! ";
	
	//public static final String UPDATE_FAIL = "UPDATE_FAIL";
	public static final String UPDATE_FAIL_MSG = "Case Worker Updation fail !!! ";

	public static final boolean TRUE = true;
	public static final boolean FALSE = false;


}
