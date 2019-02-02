package com.his.ar.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserMasterDAO;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserModel;
import com.his.util.AppConstants;
import com.his.util.EmailService;
import com.his.util.PasswordUtil;

/**
 * This is the service class it provides the b.methods
 * @author nit 
 */
@Service("arService")
public class ARServiceImpl implements ARService {

	@Autowired(required = true)
	private ARUserMasterDAO arUserMasterDao;
	
	@Autowired(required = true)
	private EmailService emailService;
	
	private Logger logger =LoggerFactory.getLogger(ARServiceImpl.class);
	

	/**
	 * This method is used to insert the case worker data into database
	 * @author nit 
	 * 
	 */
	@Override
	public int saveUser(UserModel model) {
		logger.info("ARServiceImpl::saveUser() is loaded...");
		// Create entity class object
		ARUserMaster entity = new ARUserMaster();

		// Copy incoming model data into entity class object
		BeanUtils.copyProperties(model, entity);
		
		entity.setActiveSw("Y");
		entity.setCreatedBy("admin");
		
		//set created_date
		entity.setCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		//Encrypt and save password to db table
		entity.setUserPwd(PasswordUtil.encrypt(entity.getUserPwd()));

		System.out.println(entity);
		// Save the entity class using repository
		ARUserMaster savedEntity = arUserMasterDao.save(entity);
		
		//send registration mail to user
		if(savedEntity!=null) {
			String body=null;
			try {
				body=getEmailFormatBody(model);
				logger.info("call sendEmail() of EmailService class...");
				emailService.sendEmail(model.getUserEmail(),AppConstants.EMAIL_FROM,AppConstants.EMAIL_SUBJECT , body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Save JPA generated genrator value of userId to model object
		return savedEntity.getUserId();
	}
	
	/**
	 * this method is used for format 
	 * the email body (Replace the all 
	 * placeholders by real values.)
	 * @param um
	 * @return String
	 */
	private String getEmailFormatBody(UserModel userModel) throws Exception {
		logger.info("ARServiceImpl::getEmailFormatBody() is loaded...");
		String fileName = "Registration_Email_Template.txt";
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		StringBuilder mailBody = new StringBuilder("");
		
		while (line != null) {
			//processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", userModel.getFirstName() + " " + userModel.getLastName());
			}
			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL", "<a href='http://localhost:9090/HIS/loginForm'>RI HIP</a>");
			}
			if (line.contains("APP_USER_EMAIL")) {
				line = line.replace("APP_USER_EMAIL", userModel.getUserEmail());
			}
			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", userModel.getUserPwd());
			}
			// appending processed line to StringBulder object
			mailBody.append(line);
			//read next line
			line = bufferedReader.readLine();
		}//while
		
		//close br and fr
		bufferedReader.close();
		fileReader.close();
		//return mail body
		logger.info("return Formated Email Body for pass to sendMail() ...");
		return mailBody.toString();
	}//getEmailFormatBody

	/**
	 * this method is used to check email uniqueness in db
	 */
	  @Override
	  public String checkUserMail(final String emailId) {
		  logger.info("ARServiceImpl::checkUserMail() is loaded...");
		  //call dao layer methods
		  Integer cnt = arUserMasterDao.findByUserEmail(emailId);
		  //System.out.println(cnt);
		  return(cnt!=0)?AppConstants.DUPLICATE : AppConstants.UNIQUE;
	  
			  
	  }

	  /**
	   * this method get list of case worker using pagination
	   */
	@Override
	public Page findCaseWorker(final Integer cpn) {
		//create Pageable class object
		Pageable pageable = new PageRequest(cpn-1, AppConstants.PAGE_SIZE);
		Page<ARUserMaster> page = arUserMasterDao.findAll(pageable);
		return page;
	}
	 

}
