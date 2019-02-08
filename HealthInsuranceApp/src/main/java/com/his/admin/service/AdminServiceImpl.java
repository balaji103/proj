package com.his.admin.service;

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

import com.his.admin.dao.AdminDAO;
import com.his.admin.entity.CaseWorkerEntity;
import com.his.admin.model.CaseWorkerModel;
import com.his.util.AppConstants;
import com.his.util.EmailService;
import com.his.util.PasswordUtil;

/**
 * This is the service class it provides the b.methods
 * @author nit 
 */
@Service("arService")
public class AdminServiceImpl implements AdminService {

	@Autowired(required = true)
	private AdminDAO arUserMasterDao;
	
	@Autowired(required = true)
	private EmailService emailService;
	
	private Logger logger =LoggerFactory.getLogger(AdminServiceImpl.class);
	

	/**
	 * This method is used to insert the case worker data into database
	 * @author nit 
	 * 
	 */
	@Override
	public int saveUser(CaseWorkerModel model) {
		logger.info("ARServiceImpl::saveUser() is loaded...");
		// Create entity class object
		CaseWorkerEntity entity = new CaseWorkerEntity();

		// Copy incoming model data into entity class object
		BeanUtils.copyProperties(model, entity);
		
		entity.setActiveSw("Y");
		entity.setCreatedBy("admin");
		
		//Encrypt and save password to db table
		entity.setUserPwd(PasswordUtil.encrypt(entity.getUserPwd()));

		System.out.println(entity);
		// Save the entity class using repository
		CaseWorkerEntity savedEntity = arUserMasterDao.save(entity);
		
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
	private String getEmailFormatBody(CaseWorkerModel userModel) throws Exception {
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
	  public CaseWorkerModel checkUserMail(final String emailId) {
		  logger.info("ARServiceImpl::checkUserMail() is loaded...");
		  //call dao layer methods
		  CaseWorkerEntity entity = arUserMasterDao.findByUserEmail(emailId);
		  //convert entity object to model object
		  CaseWorkerModel model = new CaseWorkerModel();
		  if(entity!=null)
			  BeanUtils.copyProperties(entity, model);
		  //return to controller
		  return model;
	  }

	  /**
	   * this method get list of case worker using pagination
	   */
	@Override
	public Page findCaseWorker(final Integer cpn) {
		//create Pageable class object
		Pageable pageable = new PageRequest(cpn-1, AppConstants.PAGE_SIZE);
		Page<CaseWorkerEntity> page = arUserMasterDao.findAll(pageable);
		return page;
	}
	 
	/**
	 * this method used to give case worker by id
	 * @param userId
	 * @return model
	 */
	public CaseWorkerModel findByUserId(Integer userId) {
		//use dao layer method for get the case worker details
		CaseWorkerEntity entity = arUserMasterDao.findById(userId).get();
		
		//convert entity to model
		CaseWorkerModel model=new CaseWorkerModel();
		BeanUtils.copyProperties(entity, model);
		
		//return to controller
		return model;
	} //findByUserId(-)
	
	
	/**
	 * this method used to perform update of case worker
	 * @param userId
	 * @return model
	 */
	public boolean update(CaseWorkerModel model,boolean isEncryptPwd) {
		//convert entity to model
		CaseWorkerEntity entity=new CaseWorkerEntity();
		BeanUtils.copyProperties(model, entity);
		//encrypt pwd
		if(isEncryptPwd) {
		entity.setUserPwd(PasswordUtil.encrypt(entity.getUserPwd()));
		}
		//update the case worker details
		entity = arUserMasterDao.save(entity);

		//return to controller
		if(entity!=null)
			return AppConstants.TRUE;
		else
			return AppConstants.FALSE;
	}//update(-)
	
	/**
	 * 
	 * @param userModel
	 * @return
	 */
	public CaseWorkerModel loginUser(CaseWorkerModel userModel) {
		CaseWorkerModel model=null;
		//encrypt pwd 
		String userPwd = PasswordUtil.encrypt(userModel.getUserPwd());
		//invoke dao layer method
		CaseWorkerEntity entity = arUserMasterDao.findUserByEmailAndPwd(userModel.getUserEmail(), userPwd);
		//convert entity object to userModel object
		if(entity!=null) {
			model = new CaseWorkerModel();
			BeanUtils.copyProperties(entity, model);
		}
		//return to controller
		return model;
	}
	
}
