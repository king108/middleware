package com.hbztc.middleware.controller;
/**
 * 描述：登录
 * @author Elisa
 * @date 2014-6-18
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbztc.middleware.client.CacheTool;
import com.hbztc.middleware.util.DesHelper;
import com.hbztc.middleware.util.HttpClientHelper;


@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController{

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//http://121.199.3.5:8888/ middleware/login/userLogin
	//?imei=356440043539939
	//&m=6d54df046e719cc4c38ac6729c0f765a5b393f7407c9ec6d
	//&p=6d54df046e719cc4c38ac6729c0f765aadcdc89f1670c453
	//&version=1.2.1.1.1
	//&code=123456
	
	@RequestMapping("userLogin")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws HttpException, IOException {
		String reqXml = "";
		String returnXml ="";
		int memcachedTime = 36000;
		Map<String,Object> resultMap = null;
		
		/*客户端请求参数*/
		String imei = request.getParameter("imei");
		if (imei.length() < 8){
			for (int i = imei.length(); i <= 8; i++){
				imei += "0";
			}
		}else{
			imei.substring(0, 8);
		}
		String moblie = request.getParameter("m");
		String pass = request.getParameter("p");
		String version = request.getParameter("version");
		String code = request.getParameter("code");
		String way = request.getParameter("way");
		
		/*解密手机号和密码*/
		String realMoblieNo = DesHelper.decryptMoblie(moblie, DesHelper.convertImei(imei));
		String realPass = DesHelper.decryptMoblie(pass, DesHelper.convertImei(imei));
		UUID uuid = UUID.randomUUID();
		String phoneNum = realMoblieNo.substring(0, 11);
		String newPass = realPass.substring(17, 23);
		logger.info("realMoblieNo:" + realMoblieNo);
		logger.info("realPass:" + realPass);
		logger.info("way:" + way);
		logger.info("code:" + code);
		logger.info("version:" + version);
		logger.info("sid:" + uuid);
		
		if(way.equals("1")){//服务密码
			if(!DesHelper.checkAccountPassWord(realMoblieNo, realPass)){
				logger.info("手机号码与动态密码不匹配");
			}
			
			logger.info("============服务密码登录============");
			logger.info("phoneNumber:" + realMoblieNo.substring(0, 11));
			logger.info("phonePassword:" + realPass.substring(17, 23));
			reqXml = createReqXml(phoneNum ,newPass);
			logger.info("reqXml:" + reqXml);
			HttpClientHelper httpClientHelper = (HttpClientHelper)getObjPool().getObject();
			returnXml = httpClientHelper.post(reqXml);
			resultMap = httpClientHelper.convertToJsonMap(returnXml, "/message/head/retinfo", "/message/Body/cli_channels_qry_login/tagset");
			
	        //设置sid到memcached缓存
	        CacheTool.setCache("sid", memcachedTime , uuid);
	        logger.info("CacheTool SID：" + String.valueOf(CacheTool.getCache("sid")));
	        
			//添加sid到返回的JSON中
			resultMap.put("sid", uuid);
			getObjPool().returnObject(httpClientHelper);
		}else{//动态随机密码
			resultMap = new HashMap<String, Object>();
			String dynamicPass = String.valueOf(CacheTool.getCache(phoneNum+"-"+"randomNum"));
			logger.info("dynamicPass:" + dynamicPass);
			if (StringUtils.isNotBlank(dynamicPass)){
				if (newPass.equals(dynamicPass)){
					resultMap.put("rettype", 0);
					resultMap.put("retcode", 100);
					resultMap.put("retmsg", "Processing the request succeeded");
					resultMap.put("code", 200);
					resultMap.put("info", "success");
					resultMap.put("upgrade", 0);
					resultMap.put("sid", uuid);
					resultMap.put("SUBNAME", "");
					resultMap.put("LOGINTYPE", "");
					//设置sid到memcached缓存
			        CacheTool.setCache("sid", memcachedTime, uuid);
			        logger.info("CacheTool SID：" + CacheTool.getCache("sid").toString());
				}else{
					resultMap.put("code", 500);
					resultMap.put("info", "failed");
				}
			}else{
				resultMap.put("code", 500);
				resultMap.put("info", "failed");
			}
		}
		return resultMap;
	}

	

	// 客户端请求参数，组装为xml
	public String createReqXml(String telnum, String password) {
		//测试：湖北移动杨工的手机号码：15002735378
		//测试：湖北移动杨工的手机服务密码：860708
		String reqXml = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
		"<message>\n" + 
		"  <head>\n" + 
		"   <menuid>#menuid#</menuid>\n" + 
		"    <process_code>cli_channels_qry_login</process_code>\n" + 
		"    <verify_code>#verify_code#</verify_code>\n" + 
		"    <req_time>20140618090912</req_time>\n" + 
		"    <req_seq>#req_seq#</req_seq>\n" + 
		"    <unicontact>#unicontact#</unicontact>\n" + 
		"    <testflag>#testflag#</testflag>\n" + 
		"    <route>\n" + 
		"      <route_type>1</route_type>\n" + 
		"      <route_value>"+telnum+"</route_value>\n" + 
		"    </route>\n" + 
		"    <channelinfo>\n" + 
		"      <operatorid>#operatorid#</operatorid>\n" + 
		"      <channelid>#channelid#</channelid>\n" + 
		"      <unitid>#unitid#</unitid>\n" + 
		"    </channelinfo>\n" + 
		"  </head>\n" + 
		"  <Body>\n" + 
		"    <cli_channels_qry_login>\n" + 
		"      <tagset>\n" + 
		"        <TELNUM>"+telnum+"</TELNUM>\n" + 
		"        <PASSWORD>"+password+"</PASSWORD>\n" + 
		"        <ISCHECKPASS>1</ISCHECKPASS>\n" + 
		"        <process_code>cli_channels_qry_login</process_code>\n" + 
		"      </tagset>\n" + 
		"    </cli_channels_qry_login>\n" + 
		"  </Body>\n" + 
		"</message>";
		return reqXml;
	}

	
	public String demoJson() {
		return "{"+
				"\"code\": \"200\",\n" + 
				"\"info\": \"success\",\n" + 
				"\"upgrade\":\"0\",\n" + 
				"\"sid\":\"会话id\",\n" + 
				"\"SUBNAME\":\"\",\n" + 
				"\"LOGINTYPE\":\"\"\n" + 
				"}";
	}
	
	
	
	public static void main(String[] args) {
		  UUID uuid = UUID.randomUUID();
		  System.out.println(uuid);
	}



	@Override
	public String createReqXml() {
		return null;
	}
}
