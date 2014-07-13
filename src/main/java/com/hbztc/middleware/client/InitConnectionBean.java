package com.hbztc.middleware.client;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.hbztc.middleware.util.ConnectionUtil;

public class InitConnectionBean implements InitializingBean, ServletContextAware{

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		ConnectionUtil.initConnections(20);
	}

}
