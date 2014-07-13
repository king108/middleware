package com.hbztc.middleware.client;

public class CacheAgreement {
	public final static String cache_user="Myuser"; //用户信息缓存
	public final static String cache_userdata="Mydata"; //用户信息缓存
	public final static int cache_userdatatime=7200; //用户信息缓存时间

	public final static String cache_organizationdata="Myorganizationdata"; //组织结构缓存
	public final static int cache_organizationdatatime=7200; //组织结构缓存时间
	
	public final static String cache_organizationuserdata="Myorganizationuserdata"; //组织结构对应用户缓存
	public final static int cache_organizationuserdatatime=7200; //组织结构对应用户信息缓存时间
	
	public final static String cache_firstlogin="firstlogin"; //用户信息缓存
	public final static int cache_firstlogintime=7200; //用户信息缓存时间
	
	public final static String cache_webloginqrcode="weblogin"; //web登录界面二维码
	public final static int cache_webloginqrcodetime=1800; //web登录界面二维码保存30分钟
}