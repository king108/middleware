package com.hbztc.middleware.controller;
/**
 * 描述：GPRS优惠套餐流量查询
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
@RequestMapping("/gprsFlux")
public class GprsfluxController{
	private static Logger logger = LoggerFactory
			.getLogger(GprsfluxController.class);

	@RequestMapping("qryGprsFlux")
	@ResponseBody
	public String qrygprsflux(HttpServletRequest request,
			HttpServletResponse response) throws HttpException, IOException {
		return 
		"{\n" +
		"    \"code\": \"200\",\n" + 
		"    \"info\": \"success\",\n" + 
		"    \"packtype\":\"3\",\n" + 
		" \"list\": [\n" + 
		"        {\n" + 
		"            \"Col1\": \"30\",\n" + 
		"            \"Col2\": \"0\",\n" + 
		"            \"Col3\": \"30\"\n" + 
		"        },\n" + 
		"        {\n" + 
		"            \"Col1\": \"10\",\n" + 
		"            \"Col2\": \"0\",\n" + 
		"            \"Col3\": \"10\"\n" + 
		"        },\n" + 
		"        {\n" + 
		"            \"Col1\": \"30\",\n" + 
		"            \"Col2\": \"0\",\n" + 
		"            \"Col3\": \"30\"\n" + 
		"        }\n" + 
		"    ]\n" + 
		"\n" + 
		"}";

	}

	private String createXml(String tel) {
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head><menuid></menuid><process_code>cli_mmobile_qry_gprsflux</process_code><verify_code></verify_code><req_time>20131130111826</req_time><req_seq></req_seq><unicontact></unicontact><testflag></testflag><route><route_type>1</route_type><route_value>13476228880</route_value></route><channelinfo><operatorid></operatorid><channelid>bsacWap</channelid><unitid>bsacWap</unitid></channelinfo></head><Body><cli_mmobile_qry_gprsflux>"
				+ "<tagset><TELNUM>"+tel+"</TELNUM><process_code>cli_mmobile_qry_gprsflux</process_code></tagset></cli_mmobile_qry_gprsflux></Body></message>";
		return null;
	}

	public String getDemoXml() {
		
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_qry_gprsflux</process_code><verify_code/><resp_time>20140604111345</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><!["
                          +"CDATA[ Processingtherequestsucceeded! ]]></retmsg></retinfo></head><Body><cli_mmobile_qry_gprsflux><crset><row><Col1>0</Col1><Col2>0</Col2><Col3>0</Col3></row></crset></cli_mmobile_qry_gprsflux></Body></message>";
		return demoXml;
	}
}
