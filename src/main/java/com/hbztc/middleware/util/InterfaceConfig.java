package com.hbztc.middleware.util;

/**
 * interface.properties
 * 
 * @author 陈照华
 */

public class InterfaceConfig {
	// 流量查询url
	private String trafficQueryUrl;
	// 推荐内容查询url
	private String recommendQueryUrl;
	// 关闭流量提醒url
	private String closeToolbarUrl;
	public String getTrafficQueryUrl() {
		return trafficQueryUrl;
	}
	public void setTrafficQueryUrl(String trafficQueryUrl) {
		this.trafficQueryUrl = trafficQueryUrl;
	}
	public String getRecommendQueryUrl() {
		return recommendQueryUrl;
	}
	public void setRecommendQueryUrl(String recommendQueryUrl) {
		this.recommendQueryUrl = recommendQueryUrl;
	}
	public String getCloseToolbarUrl() {
		return closeToolbarUrl;
	}
	public void setCloseToolbarUrl(String closeToolbarUrl) {
		this.closeToolbarUrl = closeToolbarUrl;
	}

}
