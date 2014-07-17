package com.hbztc.middleware.controller;
/**
 * 描述：下发短信
 * @author Elisa
 * @date 2014-6-18
 */
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbztc.middleware.client.CacheTool;
import com.hbztc.middleware.util.HttpClientHelper;

@Controller
@RequestMapping("/sendSmsInfo")
public class SendsmsinfoController extends AbstractController{
	private static Logger logger = LoggerFactory.getLogger(SendsmsinfoController.class);
	
	@RequestMapping("qrySendSmsInfo")
	@ResponseBody
	public Map<String, Object> qrysendsmsinfo(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		String imei = request.getParameter("imei");
		String moblie = request.getParameter("m");
		String version = request.getParameter("version");
		
		logger.info("imei:" + imei);
		logger.info("moblie:" + moblie);
		logger.info("version:" + version);
		//输出解密后的电话号码
		
		Map<String,Object> resutlMap = null;
		String reqXml = "";
		String returnXml ="";
		int memcachedTime = 300;
		
		String randomNum = genRandomNum(6);
		reqXml = this.createReqXml(moblie,randomNum);
		HttpClientHelper httpClientHelper = (HttpClientHelper)getObjPool().getObject();
		returnXml = httpClientHelper.post(reqXml);
		resutlMap = httpClientHelper.convertToJsonMap(returnXml, "/message/head/retinfo", "/message/head/retinfo");
		if(String.valueOf(resutlMap.get("code")).equals("200")){
			CacheTool.setCache(moblie+"-"+"randomNum", memcachedTime, randomNum);
			logger.info("randomNum:" + randomNum);
		}
		getObjPool().returnObject(httpClientHelper);
		
		return resutlMap;
	}

	private String createReqXml(String tel,String randomNum) {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n"
				+ "<message>\n"
				+ "  <head version=\"1.0\">\n"
				+ "   <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_mmobile_busi_sendsmsinfo</process_code>\n"
				+ "    <verify_code>#verify_code#</verify_code>\n"
				+ "    <req_time>20140618090912</req_time>\n"
				+ "    <req_seq>#req_seq#</req_seq>\n"
				+ "    <unicontact>#unicontact#</unicontact>\n"
				+ "    <testflag>#testflag#</testflag>\n"
				+ "    <route>\n"
				+ "      <route_type>1</route_type>\n"
				+ "      <route_value>" + tel + "</route_value>\n"
				+ "    </route>\n"
				+ "    <channelinfo>\n"
				+ "      <operatorid>#operatorid#</operatorid>\n"
				+ "      <channelid>#channelid#</channelid>\n"
				+ "      <unitid>#unitid#</unitid>\n"
				+ "    </channelinfo>\n"
				+ " </head>\n"
				+ "  <Body>\n"
				+ "    <cli_mmobile_busi_sendsmsinfo>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>" + tel + "</TELNUM>\n"
				+ "        <SMPARAM>1尊敬的湖北移动客户，您的本次登陆门户网站---湖北移动流量直通车的随机密码为："+randomNum+"，五分钟有效</SMPARAM>\n"
				+ "        <process_code>cli_mmobile_busi_sendsmsinfo</process_code>\n"
				+ "      </tagset>\n" + "    </cli_mmobile_busi_sendsmsinfo>\n"
				+ " </Body>\n" + "</message>";
		return reqXml;
	}

	public String getDemoXml() {
		return null;
	}
	
	public static String genRandomNum(int pwd_len){ 
		//35是因为数组是从0开始的，26个字母+10个数字 
		final int maxNum = 6; 
		int i; //生成的随机数 
		int count = 0; //生成的密码的长度 
		char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; 

		StringBuffer pwd = new StringBuffer(""); 
		Random r = new Random(); 
		while(count < pwd_len){ 
		//生成随机数，取绝对值，防止生成负数， 
			i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1 
			if (i >= 0 && i < str.length) { 
				pwd.append(str[i]); 
				count ++; 
			} 
		} 
		return pwd.toString(); 
	} 

	public static void main(String[] args) {
		System.out.println(genRandomNum(6));
	}
	@Override
	public String createReqXml() {
		return null;
	}
}
