package com.hbztc.middleware.controller;

/**
 * 描述：余额查询
 * @author Elisa
 * @date 2014-6-18
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blance")
public class BlanceController {
	private static Logger logger = LoggerFactory
			.getLogger(BlanceController.class);

	/**
	 * 获取flag 
	 * 0：BOSS返回金额，BOSS不发送短信 
	 * 1：BOSS返回金额，且BOSS直接发送短信
	 * 2：BOSS返回短信内容，BOSS不发生短信
	 * @param request
	 * @return
	 */
	private String getFlag(HttpServletRequest request) {

		return request.getParameter("flag");
	}

	@RequestMapping("feeBlance")
	@ResponseBody
	public String feeblance(HttpServletRequest request,
			HttpServletResponse response) throws HttpException, IOException {
		
		return 
				"{\n" +
				"\"code\": \"200\",\n" + 
				"\"info\": \"success\",\n" + 
				"\"left\": \"2390\",\n" + 
				"\"overdraft\": \"10000\",\n" + 
				"\"balance\": \"1223\",\n" + 
				"\"totalowe\": \"0\",\n" + 
				"\"spacefee\": \"200\",\n" + 
				"\"nowfee\": \"0\"\n" + 
				"}";
	}

	public String createXml(String tel, String flag) {
		Long date = System.currentTimeMillis();
		String reqxml = "<?xml version=\"1.0\" encoding=\"GBK\"?><message><head version=\"1.0\"><menuid>#menuid#</menuid><process_code>cli_mmobile_qry_feeblance</process_code><verify_code>#verify_code#</verify_code><req_time>20140618091008</req_time><req_seq>#req_seq#</req_seq><unicontact>#unicontact#</unicontact><testflag>#testflag#</testflag><route><route_type>1</route_type><route_value>13871668140</route_value></route><channelinfo><operatorid>#operatorid#</operatorid><channelid>#channelid#</channelid><unitid>#unitid#</unitid></channelinfo></head><Body><cli_mmobile_qry_feeblance><tagset><TELNUM>13871668140</TELNUM><FLAG>0</FLAG><process_code>cli_mmobile_qry_feeblance</process_code></tagset></cli_mmobile_qry_feeblance></Body></message>";
		return reqxml;
	}

	public String getDemoXml() {
		// 余额xml响应
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_qry_feeblance</process_code><verify_code/><resp_time>20140604111016</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processingtherequestsucceeded!]]></retmsg></retinfo></head><Body><cli_mmobile_qry_feeblance><tagset><RETFLAG>0</RETFLAG><LEFT>21876</LEFT><OVERDRAFT>10000</OVERDRAFT><BALANCE>11876</BALANCE><TOTALOWE>0</TOTALOWE><SPACEFEE>200</SPACEFEE><NOWFEE>0</NOWFEE></tagset></cli_mmobile_qry_feeblance></Body></message>";
		return demoXml;
	}
}
