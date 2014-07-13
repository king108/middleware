package com.hbztc.middleware.controller;
/**
 * 描述：积分查询
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
@RequestMapping("/subsScore")
public class SubsScoreController{
	private static Logger logger = LoggerFactory
			.getLogger(SubsScoreController.class);

	@RequestMapping("qrySubsScore")
	@ResponseBody
	public String qryuserinfo(HttpServletRequest request,
			HttpServletResponse response) throws HttpException, IOException {
		return 
		"{\n" +
		"    \"code\": \"200\",\n" + 
		"    \"info\": \"success\",\n" + 
		"    \"SBRAND\": \"G\",\n" + 
		"    \"LLEFTSCORE\": \"0\",\n" + 
		"    \"CURCONVERTSCORESTR\": \"0\",\n" + 
		"    \"CURINCSCORESTR\": \"0\",\n" + 
		"    \"CURLASTSCORESTR\": \"0\"\n" + 
		"}";


	}

	public String createXml(String tel) {
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head><menuid/><process_code>cli_mmobile_qry_userinfo</process_code><verify_code/><req_time>20140604111607</req_time><req_seq/><unicontact/><testflag/><route><route_type>1</route_type><route_value>15002735378</route_value></route><channelinfo><operatorid/><channelid>bsacWap</channelid><unitid>bsacWap</unitid></channelinfo></head><Body><cli_mmobile_qry_userinfo><tagset><TELNUM>15002735378</TELNUM><process_code>cli_mmobile_qry_userinfo</process_code></tagset></cli_mmobile_qry_userinfo></Body></message>";
	}

	public String getDemoXml() {
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_qry_userinfo</process_code><verify_code/><resp_time>20140604111607</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></head><Body><cli_mmobile_qry_userinfo><tagset><ACCNBR>15002735378</ACCNBR><REGIONNAME>武汉</REGIONNAME><NETNAME>GSM网</NETNAME><STOPNAME>正常</STOPNAME><VIPNAME>集团成员</VIPNAME><SEXNAME>男</SEXNAME><CERTID>420102198607082093</CERTID><CREATDATE>2010-08-17</CREATDATE><REGNAME>江汉北路合作营业厅</REGNAME><SUBNAME>杨健</SUBNAME><THISPRODDATE>20140301120000</THISPRODDATE></tagset></cli_mmobile_qry_userinfo></Body></message>";
		return demoXml;
	}
}
