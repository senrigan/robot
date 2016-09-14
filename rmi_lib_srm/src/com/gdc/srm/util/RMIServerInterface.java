package com.gdc.srm.util;

import java.rmi.Remote;

public interface RMIServerInterface extends java.rmi.Remote {
	
	
	public boolean  registerRobot(Remote remote) throws java.rmi.RemoteException;
	
}
