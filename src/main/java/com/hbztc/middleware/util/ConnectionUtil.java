package com.hbztc.middleware.util;

import java.util.Vector;

import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionUtil {
	private static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
	private static ThreadLocal<HttpClientHelper> current = new ThreadLocal<HttpClientHelper>();
	private static Vector<HttpClientHelper> stack = new Vector<HttpClientHelper>(); // 创建堆栈对象 
	// 读取超时  
    private final static int SOCKET_TIMEOUT = 10000;  
    // 连接超时  
    private final static int CONNECTION_TIMEOUT = 10000;  
    // 每个HOST的最大连接数量  
    private final static int MAX_CONN_PRE_HOST = 20;  
    // 连接池的最大连接数  
    private final static int MAX_CONN = 60;  
    // 连接池  
    private final static HttpConnectionManager httpConnectionManager;  
  
    static {  
        httpConnectionManager = new MultiThreadedHttpConnectionManager();  
        HttpConnectionManagerParams params = httpConnectionManager.getParams();  
        params.setConnectionTimeout(CONNECTION_TIMEOUT);  
        params.setSoTimeout(SOCKET_TIMEOUT);  
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);  
        params.setMaxTotalConnections(MAX_CONN);  
    }  
    
	public static HttpClientHelper getCurrent() {
			HttpClientHelper helper = (HttpClientHelper) stack.get(0);
			logger.info("btCode :  "+helper.getBt_Cookie());
			stack.remove(helper);
			current.set(helper);
			if(helper!=null){
				stack.add(helper);
			}
			logger.info("size :  "+stack.size());
			return current.get();
	}
	
	public static void initConnections(int counts){
		for(int i=0;i<counts;i++){
			stack.add(new HttpClientHelper(httpConnectionManager));
		}
	}
	
}
