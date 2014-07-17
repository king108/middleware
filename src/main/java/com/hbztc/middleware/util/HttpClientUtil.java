package com.hbztc.middleware.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {
	/**
	 * Logger for this class
	 */
	
	private static Log loger = LogFactory.getLog(HttpClientUtil.class);
	
	
	private static HttpClient client ;
	
	public static HttpClient getInstance(){
		client=new HttpClient();
		return client;
	}
	
	/**
	 * Getֵ
	 * @param request
	 */
	public static String getResponseByGet(String url, NameValuePair[] params) {
		GetMethod method = new GetMethod(url);
		if(params != null) 
			method.setQueryString(params);
		String rel = "";
		if(client==null)
		{
			client=new HttpClient();
		}
		try{
			client.executeMethod(method);
			BufferedInputStream is = new BufferedInputStream(method.getResponseBodyAsStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
			StringBuffer result = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");
			}
			rel = result.toString();
			br.close();
			is.close();
			br=null;
			is=null;
			method.abort();
			method.releaseConnection();
			method = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rel;
	}
	
	public static String getResponseByPost(String url, NameValuePair[] params) {
		PostMethod method = new PostMethod(url);
		if(params != null) 
			method.setQueryString(params);
		String rel = "";
		if(client==null)
		{
			client=new HttpClient();
		}
		try{
			client.executeMethod(method);
			BufferedInputStream is = new BufferedInputStream(method.getResponseBodyAsStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
			StringBuffer result = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");
			}
			rel = result.toString();
			br.close();
			is.close();
			br=null;
			is=null;
			method.abort();
			method.releaseConnection();
			method = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rel;
	}
	
	public static String getResponseByUTF8Post(String url, NameValuePair[] params) {
		PostMethod method = new PostMethod(url);
		 
		if(params != null) 
			method.setRequestBody(params);
		String rel = "";
		if(client==null)
		{
			client=new HttpClient();
		}
		try{
			client.executeMethod(method);
			BufferedInputStream is = new BufferedInputStream(method.getResponseBodyAsStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuffer result = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
				result.append("\n");
			}
			rel = result.toString();
			br.close();
			is.close();
			br=null;
			is=null;
			method.abort();
			method.releaseConnection();
			method = null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rel;
	}

}
