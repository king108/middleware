package com.hbztc.middleware.controller;

/**
 * 描述：余额查询
 * @author Elisa
 * @date 2014-6-18
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbztc.middleware.client.CacheTool;
import com.hbztc.middleware.util.HttpClientHelper;

@Controller
@RequestMapping("/blance")
public class BlanceController extends AbstractController{
	private static Logger logger = LoggerFactory.getLogger(BlanceController.class);

	@RequestMapping("feeBlance")
	@ResponseBody
	public Map<String, Object> feeblance(HttpServletRequest request, HttpServletResponse response) throws HttpException, IOException {
		Map<String,Object> resutlMap = null;
		String reqXml = "";
		String returnXml ="";
		
		String imei = request.getParameter("imei");
		String moblie = request.getParameter("m");
		String sid = request.getParameter("sid");
		String flag = request.getParameter("flag");
		String version = request.getParameter("version");
		
		String memcachedSid = String.valueOf(CacheTool.getCache("sid"));
		if (memcachedSid != null && memcachedSid.equals(sid)){
			logger.info("imei:" + imei);
			logger.info("moblie:" + moblie);
			logger.info("sid:" + sid);
			logger.info("flag:" + flag);
			logger.info("version:" + version);
			
			//未加密的电话号码请求
			//reqXml = this.createReqXml(moblie, flag);
			//加密的电话号码请求
			reqXml = this.createReqXml(moblie, flag);
			HttpClientHelper httpClientHelper = (HttpClientHelper)getObjPool().getObject();
			returnXml = httpClientHelper.post(reqXml);
			resutlMap = httpClientHelper.convertToJsonMap(returnXml, "/message/head/retinfo", "/message/Body/cli_mmobile_qry_feeblance/tagset");
			getObjPool().returnObject(httpClientHelper);
		}else{
			resutlMap = new HashMap<String, Object>();
			resutlMap.put("code", 500);
			resutlMap.put("info", "Processing the request failed");
		}
		return resutlMap;
	}
	
	// 客户端请求参数，组装为xml
	public String createReqXml(String tel, String flag) {
		Long date = System.currentTimeMillis();
		String reqXml = 
			"<?xml version=\"1.0\" encoding=\"GBK\" ?>\n"
			+"<message>\n"
			+"	<head version=\"1.0\">\n"
			+"		<menuid>#menuid#</menuid>\n"
			+"		<process_code>cli_mmobile_qry_feeblance</process_code>\n"
			+"		<verify_code>#verify_code#</verify_code>\n"
			+"		<req_time>" + date + "</req_time>\n"
			+"		<req_seq>#req_seq#</req_seq>\n"
			+"		<unicontact>#unicontact#</unicontact>\n"
			+"		<testflag>#testflag#</testflag>\n"
			+"		<route>\n"
			+"			<route_type>1</route_type>\n"
			+"			<route_value>" + tel + "</route_value>\n"
			+"		</route>\n"
			+"		<channelinfo>\n"
			+"			<operatorid>#operatorid#</operatorid>\n"
			+"			<channelid>#channelid#</channelid>\n"
			+"			<unitid>#unitid#</unitid>\n"
			+"		</channelinfo>\n"
			+"	</head>\n"
			+"	<Body>\n"
			+"		<cli_mmobile_qry_feeblance>\n"
			+"			<tagset>\n"
			+"				<TELNUM>" + tel + "</TELNUM>\n"
			+"				<FLAG>" + flag + "</FLAG>\n"
			+"				<process_code>cli_mmobile_qry_feeblance</process_code>\n"
			+"			</tagset>\n"
			+"		</cli_mmobile_qry_feeblance>\n"
			+"	</Body>\n"
			+"</message>\n";
			logger.info(reqXml);
		return reqXml;
	}

	public String getDemoXml() {
		return null;
	}

	@Override
	public String createReqXml() {
		return null;
	}
}
