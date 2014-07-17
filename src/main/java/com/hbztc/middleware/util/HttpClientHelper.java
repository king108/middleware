package com.hbztc.middleware.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientHelper {
	private String bt_Cookie = "";
	private static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
	private static final String HTTPS_120_202_17_126_12520_LIU_L_RS_HB_AGENT = "https://120.202.17.126:12520/liuL/rs/hb/agent";
	private static final String _200 = "200";
	private static final String CODE = "code";
	private HttpConnectionManager httpconnectionmanager;
	
	public HttpClientHelper(HttpConnectionManager httpconnectionmanager) {
		this.httpconnectionmanager = httpconnectionmanager;
	}

	public String getBt_Cookie() {
		return bt_Cookie;
	}

	public void setBt_Cookie(String bt_Cookie) {
		this.bt_Cookie = bt_Cookie;
	}

	public String post(String reqXml, boolean isNeedValidAccount) {
		PostMethod post = new PostMethod(
				HTTPS_120_202_17_126_12520_LIU_L_RS_HB_AGENT);// 请求地址
		try {
			RequestEntity entity = new StringRequestEntity(reqXml, "text/xml",
					"GBK");
			post.setRequestEntity(entity);
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage());
		}
		post.setRequestHeader("Pragma:", "no-cache");
		post.setRequestHeader("Cache-Control", "no-cache");
		String cookie = "";
		if (isNeedValidAccount) {
			cookie = cookie + "liulapp=ll123654;";
		}
		if (StringUtils.isNotBlank(bt_Cookie)) {
			cookie = cookie + bt_Cookie;
		}
		post.setRequestHeader("Cookie", cookie);
		post.setRequestHeader("Content-Type", "text/xml; charset=GBK");
		// ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		// Protocol.registerProtocol("https", new Protocol("https", fcty,
		// 12520));
		HttpClient httpclient = new HttpClient(httpconnectionmanager);// 创建 HttpClient 的实例
		int result;
		BufferedReader in = null;
		String resultString = "";
		try {
			result = httpclient.executeMethod(post);
			Cookie[] cookies = httpclient.getState().getCookies();
			if (cookies != null && cookies.length > 0) {
				String tmpBtCookie = "";
				for (int i = 0; i < cookies.length; i++) {
					if ("BTdH8ocdSK".equals(cookies[i].getName())) {
						tmpBtCookie = cookies[i].getName() + "="
								+ cookies[i].getValue() + ";";
						if (!bt_Cookie.equals(tmpBtCookie)) {
							logger.info("  replace:  " + bt_Cookie + "  new: "
									+ tmpBtCookie);
							bt_Cookie = tmpBtCookie;
						}
					}
				}
				logger.info("Set-Cookie:" + bt_Cookie);
				// cookies[0].setValue(cook);
				// HttpState state = new HttpState();
				// state.addCookie(cookies[0]);
				// httpclient.setState(state);
			}
			in = new BufferedReader(new InputStreamReader(
					post.getResponseBodyAsStream(), "GBK"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			resultString = buffer.toString();
			logger.info("Response status code: " + result);// 返回200为成功
			logger.info("Response body: ");
			logger.info(resultString);// 返回的内容
		} catch (HttpException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			post.releaseConnection();
		}
		return resultString;
	}

	public String post(String reqXml) {
		String responseXML = "";
		responseXML = post(reqXml, false);
		if ("60001".equals(responseXML)) {
			responseXML = post(reqXml, true);
		}
		return responseXML;
	}

	public Map<String, Object> convertToJsonMap(String returnXml,
			String retInfoPath, String resultPath) {
		Map<String, Object> resutlMap;
		XMLUtil xmlUtil = new XMLUtil();
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		resutlMap = xmlUtil.parseXmlResult(returnXml, retInfoPath);
		String returnCode = String.valueOf(resutlMap.get(CODE));
		if (returnCode.equals(_200)) {
			xmlUtil.parseXmlData(resutlMap, returnXml, resultPath);
		}
		logger.info(mapper.toJson(resutlMap));
		return resutlMap;
	}
	
	public Map<String, Object> convertToJsonMap(String returnXml,
			String retInfoPath, String resultPath, String resultPath2) {
		Map<String, Object> resutlMap;
		XMLUtil xmlUtil = new XMLUtil();
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		resutlMap = xmlUtil.parseXmlResult(returnXml, retInfoPath);
		String returnCode = String.valueOf(resutlMap.get(CODE));
		if (returnCode.equals(_200)) {
			xmlUtil.parseXmlData(resutlMap, returnXml, resultPath, resultPath2);
		}
		logger.info(mapper.toJson(resutlMap));
		return resutlMap;
	}
}
