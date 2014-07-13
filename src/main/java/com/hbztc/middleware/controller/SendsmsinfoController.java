package com.hbztc.middleware.controller;
/**
 * 描述：下发短信
 * @author Elisa
 * @date 2014-6-18
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sendSmsInfo")
public class SendsmsinfoController{
	private static Logger logger = LoggerFactory
			.getLogger(SendsmsinfoController.class);
	
	

	@RequestMapping("qrySendSmsInfo")
	@ResponseBody
	public String qrysendsmsinfo(HttpServletRequest request,
			HttpServletResponse response) throws  IOException {
		return 
				"{\n" +
				"\"code\": \"200\",\n" + 
				"\"info\": \"success\"\n" + 
				"}";
	}

	private String createXml(String tel, String password) {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head><menuid/><process_code>cli_mmobile_busi_sendsmsinfo</process_code><verify_code/><req_time>20140604113543</req_time><req_seq/><unicontact/><testflag/><route><route_type>1</route_type><route_value>15002735378</route_value></route><channelinfo><operatorid/><channelid>bsacWap</channelid><unitid>bsacWap</unitid></channelinfo></head><Body><cli_mmobile_busi_sendsmsinfo><tagset>"
				+ "<SMPARAM>您的网上营业厅随机密码是："+password+"，请输入系统。该密码仅限本次使用,有效时间为10分钟。</SMPARAM>"
				+ "<TELNUM>"+tel+"</TELNUM><process_code>cli_mmobile_busi_sendsmsinfo</process_code></tagset></cli_mmobile_busi_sendsmsinfo></Body></message>";
		return xml;
	}

	public String getDemoXml() {
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_busi_sendsmsinfo</process_code><verify_code/><resp_time>20140604114429</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[ Processing the request succeeded!]]></retmsg></retinfo></head></message>";
		return demoXml;
	}
}
