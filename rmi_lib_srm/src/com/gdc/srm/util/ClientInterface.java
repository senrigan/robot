package com.gdc.srm.util;

public interface ClientInterface  extends java.rmi.Remote  {
	public int getRobotId() throws java.rmi.RemoteException;
	public String getStatus() throws java.rmi.RemoteException;
	public String getRobotVersion() throws java.rmi.RemoteException;
	public String getVersionDate() throws  java.rmi.RemoteException;
	public String getRobotName() throws java.rmi.RemoteException;
	public boolean killYourself() throws java.rmi.RemoteException;
	
}
