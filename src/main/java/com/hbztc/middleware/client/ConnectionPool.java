package com.hbztc.middleware.client;

import java.util.List;
import java.util.Vector;

import org.apache.commons.httpclient.HttpConnectionManager;

import com.hbztc.middleware.util.HttpClientHelper;
import com.hbztc.middleware.util.IConnectionPool;

public class ConnectionPool implements IConnectionPool {
	private static HttpConnectionManager httpConnectionManager;

	private boolean isActive = false; // 连接池活动状态
	private int contActive = 0;// 记录创建的总的连接数

	// 空闲连接
	private List<HttpClientHelper> freeConnection = new Vector<HttpClientHelper>();
	// 活动连接
	private List<HttpClientHelper> activeConnection = new Vector<HttpClientHelper>();
	// 将线程和连接绑定，保证事务能统一执行
	private static ThreadLocal<HttpClientHelper> threadLocal = new ThreadLocal<HttpClientHelper>();

	public ConnectionPool(HttpConnectionManager httpConnectionManager) {
		super();
		init();
		cheackPool();
		this.httpConnectionManager = httpConnectionManager;
	}

	// 初始化
	public void init() {
		for (int i = 0; i < 10; i++) {
			HttpClientHelper conn = new HttpClientHelper(httpConnectionManager);
			// 初始化最小连接数
			if (conn != null) {
				freeConnection.add(conn);
				contActive++;
			}
		}
		isActive = true;
	}

	// 获得当前连接
	public HttpClientHelper getCurrentConnecton() {
		// 默认线程里面取
		HttpClientHelper conn = threadLocal.get();
		if (!isValid(conn)) {
			conn = getConnection();
		}
		return conn;
	}

	// 获得连接
	public synchronized HttpClientHelper getConnection() {
		HttpClientHelper conn = null;
		try {
			// 判断是否超过最大连接数限制
			if (contActive < 20) {
				if (freeConnection.size() > 0) {
					conn = freeConnection.get(0);
					if (conn != null) {
						threadLocal.set(conn);
					}
					freeConnection.remove(0);
				} else {
					conn = new HttpClientHelper(httpConnectionManager);
				}

			} else {
				// 继续获得连接,直到从新获得连接
				wait(6000);
				conn = getConnection();
			}
			if (isValid(conn)) {
				activeConnection.add(conn);
				contActive++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 获得新连接
	@SuppressWarnings("unused")
	private synchronized HttpClientHelper newConnection() {
		HttpClientHelper conn = new HttpClientHelper(httpConnectionManager);
		return conn;
	}

	// 释放连接
	public synchronized void releaseConn(HttpClientHelper conn){
		if (isValid(conn)
				&& !(freeConnection.size() > 20)) {
			freeConnection.add(conn);
			activeConnection.remove(conn);
			contActive--;
			threadLocal.remove();
			// 唤醒所有正待等待的线程，去抢连接
			notifyAll();
		}
	}

	// 判断连接是否可用
	private boolean isValid(HttpClientHelper conn) {
//		try {
//			if (conn == null || conn.isClosed()) {
//				return false;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return true;
	}

	// 销毁连接池
	public synchronized void destroy() {
		for (HttpClientHelper conn : freeConnection) {
//			try {
//				if (isValid(conn)) {
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		for (HttpClientHelper conn : activeConnection) {
//			try {
//				if (isValid(conn)) {
//					
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		isActive = false;
		contActive = 0;
	}

	// 连接池状态
	@Override
	public boolean isActive() {
		return isActive;
	}

	// 定时检查连接池情况
	@Override
	public void cheackPool() {
		
	}

	
}
