package com.hbztc.middleware.client;


import java.io.Serializable;

public class Employees implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8202439972511838105L;

	private String id;
	private String name;
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}

