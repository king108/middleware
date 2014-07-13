package com.hbztc.middleware.util;

import java.io.IOException;

import org.apache.commons.httpclient.methods.GetMethod;

public interface HttpUtilGetCallBack {
	public Object success(GetMethod get) throws IOException;
}
