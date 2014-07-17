package com.hbztc.middleware.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtils {
	/**
	 * Logger for this class
	 */
	
	private static Log loger = LogFactory.getLog(HttpClientUtils.class);
	
	
	private static HttpClient client ;
	
	public static HttpClient getInstance(){
		if(client == null){
			client = new HttpClient();
		}
		return client;
	}
	
	public static String post(String reqXml){
		PostMethod post = new PostMethod("http://171.0.0.18:6315/liuL/rs/hb/agent");// 请求地址
		try {
			RequestEntity entity = new StringRequestEntity(reqXml,"text/xml","GBK");
			post.setRequestEntity(entity);
		} catch (UnsupportedEncodingException e1) {
			loger.error(e1);
		}
		// 指定请求内容的类型
		post.setRequestHeader("Pragma:", "no-cache");
		post.setRequestHeader("Cache-Control", "no-cache");
		post.setRequestHeader("Cookie", "liulapp=ll123654");
		post.setRequestHeader("Content-Type", "text/xml; charset=GBK");

		HttpClient httpclient = getInstance();// 创建 HttpClient 的实例
		int result = 0;
		String responseBodyAsString = "";
		try {
			result = httpclient.executeMethod(post);
			responseBodyAsString = post.getResponseBodyAsString();
			loger.info("Response status code: " + result);// 返回200为成功
			loger.info("Response body: " + responseBodyAsString);// 返回的内容
		} catch (HttpException e) {
			loger.error(e);
		} catch (IOException e) {
			loger.error(e);
		}
		return responseBodyAsString;
	}

}
