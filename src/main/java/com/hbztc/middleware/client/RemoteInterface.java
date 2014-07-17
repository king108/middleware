package com.hbztc.middleware.client;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface extends Remote{

	public String getServerInfo(int index)throws RemoteException;
	
	public Employees getEmployeesById(String id)throws RemoteException;
}
