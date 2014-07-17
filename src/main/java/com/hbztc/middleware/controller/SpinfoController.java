package com.hbztc.middleware.controller;
/**
 * 描述：增值业务统一查询
 * @author Mark
 * @date 2014-6-30
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
@RequestMapping("/spinfo")
public class SpinfoController extends AbstractController{
	private static Logger logger = LoggerFactory.getLogger(SpinfoController.class);

	@RequestMapping("qrySpinfo")
	@ResponseBody
	public Map<String, Object> qryspinfo(HttpServletRequest request, HttpServletResponse response) throws HttpException, IOException {
		Map<String,Object> resutlMap = null;
		String reqXml = "";
		String returnXml ="";
		
		String imei = request.getParameter("imei");
		String moblie = request.getParameter("m");
		String sid = request.getParameter("sid");
		String version = request.getParameter("version");
		
		logger.info("imei:" + imei);
		logger.info("moblie:" + moblie);
		logger.info("sid:" + sid);
		logger.info("version:" + version);
		
		String memcachedSid = String.valueOf(CacheTool.getCache("sid"));
		if (memcachedSid != null && memcachedSid.equals(sid)){
			//未加密的电话号码请求
			//reqXml = this.createReqXml(moblie, flag);
			//加密的电话号码请求
			reqXml = this.createReqXml(moblie);
			HttpClientHelper httpClientHelper = (HttpClientHelper)getObjPool().getObject();
			returnXml = httpClientHelper.post(reqXml);
			resutlMap = httpClientHelper.convertToJsonMap(returnXml, "/message/head/retinfo", "/message/Body/cli_mmobile_qry_spinfo/crset");
			getObjPool().returnObject(httpClientHelper);
		}else{
			resutlMap = new HashMap<String, Object>();
			resutlMap.put("code", 500);
			resutlMap.put("info", "Processing the request failed");
		}
		return resutlMap;
	}

	private String createReqXml(String tel) {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n"
				+ "<message>\n"
				+ "  <head version=\"1.0\">\n"
				+ "    <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_mmobile_qry_spinfo</process_code>\n"
				+ "    <verify_code>#verify_code#</verify_code>\n"
				+ "    <req_time>20140618091008</req_time>\n"
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
				+ "  </head>\n"
				+ "  <Body>\n"
				+ "    <cli_mmobile_qry_spinfo>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>" + tel + "</TELNUM>\n"
				+ "        <queryspid></queryspid>\n"
				+ "        <process_code>cli_mmobile_qry_spinfo</process_code>\n"
				+ "        <menuid>cancelSpInfo</menuid>\n"
				+ "        <queryhismodle></queryhismodle>\n"
				+ "        <sn></sn>\n" + "        <regtype></regtype>\n"
				+ "      </tagset>\n" + "    </cli_mmobile_qry_spinfo>\n"
				+ "  </Body>\n" + "</message>";
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
