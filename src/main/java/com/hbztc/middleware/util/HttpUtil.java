package com.hbztc.middleware.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	public static Map<String, Object> defaultConfigs() {
		Map<String, Object> configs = new HashMap<String, Object>();

		// http.protocol.content-charset
		configs.put(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		// http.socket.timeout
		configs.put(HttpMethodParams.SO_TIMEOUT, 5000);

		return configs;
	}

	public static Object postCallHttpRequest(String url, NameValuePair[] data,
			HttpUtilPostCallBack callBack) throws HttpException, IOException {
		return HttpUtil.postCallHttpRequest(url, data,
				HttpUtil.defaultConfigs(), callBack);
	}

	public static Object getCallHttpRequest(String url,
			HttpUtilGetCallBack callBack) throws HttpException, IOException {
		return HttpUtil.getCallHttpRequest(url, HttpUtil.defaultConfigs(),
				callBack);

	}

	public static Object getCallHttpRequest(String url,
			Map<String, Object> configs, HttpUtilGetCallBack callBack)
			throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		GetMethod getMethod = new GetMethod(url);

		try {
			httpClient
					.getHttpConnectionManager()
					.getParams()
					.setConnectionTimeout(
							(Integer) configs.get(HttpMethodParams.SO_TIMEOUT));

			getMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET,
					configs.get(HttpMethodParams.HTTP_CONTENT_CHARSET));

			httpClient.executeMethod(getMethod);

			if (AssertValue.isNotNull(callBack)) {
				return callBack.success(getMethod);
			} else {
				return null;
			}
		} finally {
			if (AssertValue.isNotNull(getMethod)) {
				getMethod.releaseConnection();
			}
		}

	}

	public static Object postCallHttpRequest(String url, NameValuePair[] data,
			Map<String, Object> configs, HttpUtilPostCallBack callBack)
			throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		PostMethod post = new PostMethod(url);

		try {
			post.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET,
					configs.get(HttpMethodParams.HTTP_CONTENT_CHARSET));

			httpClient
					.getHttpConnectionManager()
					.getParams()
					.setConnectionTimeout(
							(Integer) configs.get(HttpMethodParams.SO_TIMEOUT));
			post.setRequestBody(data);
			httpClient.executeMethod(post);

			return callBack.success(post);

		} finally {
			if (AssertValue.isNotNull(post)) {
				post.releaseConnection();
			}
		}

	}
}
