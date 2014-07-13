package com.hbztc.middleware.util;

import java.io.IOException;

import org.apache.commons.httpclient.methods.PostMethod;

public interface HttpUtilPostCallBack {
	public Object success(PostMethod post) throws IOException;
}
