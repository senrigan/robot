package com.gdc.srm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import com.gdc.srm.util.ClientInterface;
import com.gdc.srm.util.Constants;
import com.gdc.srm.util.RMIServerInterface;




public class RMIServer extends java.rmi.server.UnicastRemoteObject  implements RMIServerInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RMIServer instance;
	private HashMap<String,ClientInterface> registerRobots;
	

	private RMIServer() throws RemoteException {
		super();
		registerRobots=new HashMap<String,ClientInterface>();
	}
	
	
	public static RMIServer getInstance(){
		if(instance==null){
			try {
				instance=new RMIServer();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	
	
	public void  startServerRMI(){
		 try
		    {
		      RMIServer rmiServer = new RMIServer();
		      java.rmi.registry.LocateRegistry.createRegistry(Constants.PORT);
		      java.rmi.Naming.rebind(Constants.SERVERNAME, rmiServer);
		      System.out.println("Server Ready");
		    }catch (Exception e){
		    	e.printStackTrace();
		    }
	}
	
	
	

	
	
	public static void main(String[] args)
	  {
//		try {
////			Runtime.getRuntime().exec("rmiregistry 1193");
//			LocateRegistry.createRegistry(1193);
//
//			System.setProperty("java.rmi.server.hostname", java.net.InetAddress.getLocalHost().getHostAddress());
//			ClaseRemota rem=new ClaseRemota();
////			rmiTest stub=(rmiTest) UnicastRemoteObject.exp;
//			rmiTest stub= (rmiTest)UnicastRemoteObject.exportObject(rem,1193);
//			
//			Registry registry = LocateRegistry.getRegistry();
//			registry.bind("PruebaRMI", stub);
//			System.out.println("Server ready");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	   
	  }

	/**
	 * @return true if remote is valid ClientInterface and not register before other case return false
	 */
	public boolean registerRobot(Remote remote) {
		try{
			ClientInterface robotClient=(ClientInterface)remote;
			return registerNewRobotRMIClient(robotClient);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;

	}
	
	/**
	 * register the rmi interface of a new client
	 * @param robotClient
	 * @return true if the clientinterface not exist yet other case return false
	 * @throws RemoteException 
	 */
	public boolean registerNewRobotRMIClient(ClientInterface robotClient) throws RemoteException{
		String robotName = robotClient.getRobotName();
		if(registerRobots.containsKey(robotName)){
			registerRobots.put(robotName, robotClient);
			return true;
		}
		return false;
	}

}
