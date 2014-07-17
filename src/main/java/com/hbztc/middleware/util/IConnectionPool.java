package com.hbztc.middleware.util;

import java.sql.SQLException;

public interface IConnectionPool {
	 // 获得连接  
    public HttpClientHelper  getConnection();  
    // 获得当前连接  
    public HttpClientHelper getCurrentConnecton();  
    // 回收连接  
    public void releaseConn(HttpClientHelper conn) throws SQLException;  
    // 销毁清空  
    public void destroy();  
    // 连接池是活动状态  
    public boolean isActive();  
    // 定时器，检查连接池  
    public void cheackPool();  
    
    
}
