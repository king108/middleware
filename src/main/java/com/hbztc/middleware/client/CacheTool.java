package com.hbztc.middleware.client;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Logger;

/**
 * memcache缓存数据
 * @author zxc88p
 * 
 */
public class CacheTool {

	public static Logger logger = Logger.getLogger(CacheTool.class);
	public static MemcachedClientBuilder builder = null;
	public static MemcachedClient client = null;

	/**
	 * 只使用一个连接缓存MemcachedClient 连接数上限为2000
	 * @return
	 */
	public synchronized static MemcachedClient getClient() {
		if (builder == null) {
			//91服务器上memcached端口22344
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
			builder.setConnectionPoolSize(30);
		}
		
		if (client == null) {
			try {
				client = builder.build();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return client;
	}

	/**
	 * 设置缓存
	 * @param key
	 * @param time 单位为秒
	 * @param o
	 */
	public static void setCache(String key, int time, Object o) {
		try {
			if (o != null) {
				getClient().set(key, time, o);
			}
		} catch (Exception e) {
			logger.error("MemcachedClient operation fail.... key=" + key);
		}
	}

	/**
	 * 获取缓存
	 * @param key
	 * @param time
	 * @param o
	 */
	public static Object getCache(String key) {
		Object o = null;
		try {
			o = getClient().get(key);
			if (o == null) {
				o = getClient().get(key);
			}
		} catch (Exception e) {
			logger.error("MemcachedClient operation fail.... key=" + key);
		}
		return o;
	}

	/**
	 * 删除缓存数据
	 * @param key
	 */
	public static void delCache(String key) {
		try {
			getClient().delete(key);
		} catch (Exception e) {
			logger.error("MemcachedClient operation fail.... key=" + key);
		}
	}

	public static void main(String[] args) throws Exception {
		//获得当前系统的毫秒数
		long time = System.currentTimeMillis();  
        Date date = new Date(time);  
  
        //在当前系统的毫秒数上加上10个小时
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.HOUR, 10);  
  
        int i = (int) calendar.getTimeInMillis();
        
        System.out.println(i); 
        
		System.out.println(System.currentTimeMillis());
		UUID uuid = UUID.randomUUID();
		CacheTool.setCache("sid", (3600 * 10) , uuid);
		Object obj = CacheTool.getCache("sid");
		System.out.println(obj.toString());
	}
}