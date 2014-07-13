package com.hbztc.middleware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesHelper {
	
	private static Logger logger = LoggerFactory.getLogger(DesHelper.class);
	
	public static String decryptMoblie(String srcCode, String key) {
		String decCode = "";
		try {
			DES des = new DES(key);
			decCode = des.decrypt(srcCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return decCode;
	}
	
	public static String decryptDynamicPass(String srcCode, String key) {
		String decCode = "";
		try {
			DES des = new DES(key);
			decCode = des.decrypt(srcCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return decCode;
	}
	
	public static String convertImei(String srcCode) {
		srcCode = srcCode +"00000000";
		return srcCode.substring(0,8);
	}
	
	public static boolean checkAccountPassWord(String mobile,String password){
		String mobileNo = mobile.substring(0, 11);
		String code = mobile.substring(11, 17);
		String mobileNo2 = password.substring(0, 11);//手机号
		String code2 = password.substring(11, 17);//CODE
		if(mobileNo.equals(mobileNo2) && code.equals(code2)){
			return true;
		}
		return false;
	}
}
