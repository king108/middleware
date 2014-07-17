package com.hbztc.middleware.controller;
/**
 * 描述：公共业务抽象类
 * @author Elisa
 * @date 2014-6-16
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.hbztc.middleware.client.ObjectPool;
import com.hbztc.middleware.util.AssertValue;

public abstract class AbstractController {
	private static final Logger logger = Logger.getLogger(AbstractController.class);
	private static ObjectPool objPool = new ObjectPool();
	static{
		objPool.createPool();
	}
	
	public static ObjectPool getObjPool() {
		return objPool;
	}

	public String getParam(HttpServletRequest request, String key, String msg) {
		String tel = request.getParameter(key);
		return tel == null ? msg : tel.trim();
	}

	/**
	 * 获取手机号码
	 * 
	 * @param request
	 * @return
	 */
	protected String getMobile(HttpServletRequest request) {
		return this.getParam(request, "m", "手机号码必须不为空");
	}

	/**
	 * 获取手机服务密码 imei号
	 * 
	 * @param request
	 * @return
	 */
	protected String getPwd(HttpServletRequest request) {
		return this.getParam(request, "imei", "手机服务密码必须不为空");
	}

	/**
	 * 获取version
	 * 
	 * @param request
	 * @return
	 */
	protected String getVersion(HttpServletRequest request) {
		return this.getParam(request, "version", "版本号必须不为空");
	}

	/**
	 * 获取sid
	 * 
	 * @param request
	 * @return
	 */
	protected String getSid(HttpServletRequest request) {
		return this.getParam(request, "sid", "UUID必须不为空");
	}

	// 解析湖北移动响应的xml
	protected Map<String, Object> parseXml(String xml, String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "200");
		Document document = null;
		try {
			document = DocumentHelper.parseText(xml);

			Node root = document.selectSingleNode(path);
			String rootName = root.getName();
			logger.info("\nrootName:" + rootName + "\nroot:" + root.asXML());
			List list = root.selectNodes("*");

			if (rootName.equals("crset")) {

				for (Object o : list) {
					Element e_row = (Element) o;
					logger.info("\nelement name："+e_row.getName());
					List rows = new LinkedList();//row集合
					
					for(Iterator i_col = e_row.elementIterator();i_col.hasNext();){//遍历多个row集合元素
						Element e_col =(Element) i_col.next();
						String value = e_col.getText();
						String name = e_col.getName();
						if (AssertValue.isNotNullAndNotEmpty(value)
								&& AssertValue.isNotNullAndNotEmpty(name)) {
							Map<String,String> colMap = new HashMap<String,String>();
							colMap.put(name, value);
							rows.add(colMap);
						}
		                map.put("detatil:", rows);
					}
				}
				
			} else {

				for (Object o : list) {
					Element e = (Element) o;
					String value = e.getText();
					String name = e.getName();
					if (AssertValue.isNotNullAndNotEmpty(value)
							&& AssertValue.isNotNullAndNotEmpty(name)) {
						map.put(name, value);
					}

				}
			}
		} catch (DocumentException e) {
			map.put("code", 500);
			map.put("info", "系统内部出现异常");
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 模拟湖北接口响应的xml
	 * 
	 * @return 响应xml
	 */
	public abstract String createReqXml();

}
