package com.hbztc.middleware.controller;
/**
 * 描述：套餐内使用量查询
 * @author Mark
 * @date 2014-6-30
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
@RequestMapping("/privused")
public class PrivusedController{
	private static Logger logger = LoggerFactory
			.getLogger(PrivusedController.class);

	@RequestMapping("qryPrivused")
	@ResponseBody
	public String qryprivused(HttpServletRequest request,
			HttpServletResponse response) throws HttpException, IOException {
		return 
		"{\n" +
		"    \"code\": \"200\",\n" + 
		"    \"info\": \"success\",\n" + 
		"    \"list\": [\n" + 
		"        {\n" + 
		"            \"Col1\": \"GPRSNOBUSY\",\n" + 
		"            \"Col2\": \"G31202\",\n" + 
		"            \"Col3\": \"10元闲时流量套餐\",\n" + 
		"\t   \"Col4\": \"GPRS闲时流量\",\n" + 
		"            \"Col5\": \"909\",\n" + 
		"            \"Col6\": \"0\",\n" + 
		"            \"Col7\": \"M\"\n" + 
		"        },\n" + 
		"        {\n" + 
		"            \"Col1\": \"GPRSNOBUSY\",\n" + 
		"            \"Col2\": \"G31321\",\n" + 
		"            \"Col3\": \"免费30G省内4G流量充值包12个月\",\n" + 
		"       \"Col4\": \"省内流量\",\n" + 
		"            \"Col5\": \"30720\",\n" + 
		"            \"Col6\": \"0\",\n" + 
		"            \"Col7\": \"M\"\n" + 
		"        }\n" + 
		"    ]\n" + 
		"}";
		

	}

	public String createXml(String tel, String billcycle) {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head><menuid></menuid><process_code>cli_mmobile_qry_privused</process_code><verify_code></verify_code><req_time>20131130111826</req_time><req_seq></req_seq><unicontact></unicontact><testflag></testflag><route><route_type>1</route_type><route_value>15002735378</route_value></route><channelinfo><operatorid></operatorid><channelid>bsacWap</channelid><unitid>bsacWap</unitid></channelinfo></head><Body><cli_mmobile_qry_privused>"
				+ "<tagset><TELNUM>"+tel+"</TELNUM>"
				+ "<BILLCYCLE>"+billcycle+"</BILLCYCLE><process_code>cli_mmobile_qry_privused</process_code></tagset></cli_mmobile_qry_privused></Body></message>";
		return xml;
	}

	public String getDemoXml() {

		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_qry_privused</process_code><verify_code/><resp_time>20140604111602</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></head><Body><cli_mmobile_qry_privused><tagset><errno>0</errno></tagset><crset><usedetail><Col1>VOICE</Col1><Col2>B221547</Col2><Col3>LTE语音168元套餐</Col3><Col4>国内语音主叫</Col4><Col5>1000</Col5><Col6>259</Col6><Col7>分钟</Col7></usedetail><usedetail><Col1>EDUWLAN</Col1><Col2>EduWlan20</Col2><Col3>高校WLAN20元包</Col3><Col4>校园WLAN赠送时长</Col4><Col5>6000</Col5><Col6>3487</Col6><Col7>分钟</Col7></usedetail></crset></cli_mmobile_qry_privused></Body></message>";
		return demoXml;
	}
}
