package com.his.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserRegistrationNoGenerator implements IdentifierGenerator {

	private static final String GET_USER_REGNO_FROM_SEQ = "SELECT USER_REGNO_SEQ.NEXTVAL FROM DUAL";
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		int value=0;
		String prefix="ARN";
		
		try {
			//get JDBC connection
			connection=session.connection();
			//create preparedStatement object
			preparedStatement=connection.prepareStatement(GET_USER_REGNO_FROM_SEQ);
			//execute sequence and get resultSet object
			resultSet=preparedStatement.executeQuery();
			//get Sequence value from resultSet
			if(resultSet.next()) {
				value=resultSet.getInt(1);
			}
			if(value<=9)
				return prefix+"000"+value;
			else if(value<=99)
				return prefix+"00"+value;
			else if(value<=999)
				return prefix+"0"+value;
			else
				return prefix+value;		
			
		} catch (Exception e) {
			e.printStackTrace();
			return "problem in user Reg no generator";
		}
	}

}
