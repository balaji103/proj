package com.his;

import org.junit.BeforeClass;
import org.junit.Test;

import com.his.util.PasswordUtil;

public class PasswordTest {
	
	//private static PasswordUtil pwdUtil;
	
	@BeforeClass
	public static void beforeClass() {
		//pwdUtil=new PasswordUtil();
	}

	@Test
	public void testPwdEncryption() {
		String encPwd=PasswordUtil.encrypt("gopi@123");
		System.out.println(encPwd);
		//fail("Not yet implemented");
	}

}
