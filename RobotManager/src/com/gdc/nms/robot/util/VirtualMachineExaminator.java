package com.gdc.nms.robot.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.gdc.nms.robot.gui.tree.Element;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;



//import sun.tools.jps.Jps;

public class VirtualMachineExaminator {
	/**
	 * get a list of java process with contains a property name = "robotid" 
	 * if the jvm not have any of this process then return a empaty list
	 * @return
	 */
//	public static ArrayList<RobotInformation> getRunningRobot(){
//		List<VirtualMachineDescriptor> list = VirtualMachine.list();
////		   String [] param= {"-v"};
////			ArrayList<JavaProcessInfo> runningApps = JpsManager.getRunningApps(param);
////			System.out.println("running apps jps"+runningApps);
//		System.out.println("list"+list);
//		ArrayList<RobotInformation> robots=new ArrayList<RobotInformation>();
//		System.out.println("procesando virtualmachine");
////		ExecutorService executor=Executors.newFixedThreadPool(10);
//		
// 		for(VirtualMachineDescriptor descriptor:list){
// 			System.out.println("descriptor id "+descriptor.id());
//// 			executor.execute(new Runnable() {
////				
////				@Override
////				public void run() {
//					try {
//						VirtualMachine vm=VirtualMachine.attach(descriptor.id());
//						System.out.println(""+descriptor.id());
//						System.out.println("display name"+descriptor.displayName());
//						System.out.println(descriptor.getClass().getName());
//						String robotId = vm.getSystemProperties().getProperty("robotId");
//						System.out.println("robot  id"+robotId);
//						if(robotId!=null){
//							RobotInformation robotInfo=new RobotInformation();
//							robotInfo.setIdProcess(Long.parseLong(descriptor.id()));
//							robotInfo.setRobotId(Long.parseLong(robotId));
//							robotInfo.setAppName(vm.getSystemProperties().getProperty("appName"));
//							Properties robotProperties = vm.getSystemProperties();
//							Set<Object> keySet = robotProperties.keySet();
//							HashMap<String,String> properties=new HashMap<String,String>();
//							for (Object object : keySet) {
//								String propertyName=(String)object;
//								if(propertyName.contains("robot_")){
//									propertyName=propertyName.replaceAll("robot_","");
//									propertyName=propertyName.replaceAll("_", " ");
//									String propertyValue = (String)robotProperties.get(object);
//									properties.put(propertyName, propertyValue);
//								}
//							}
//							robotInfo.setPropierties(properties);
//							robots.add(robotInfo);
//							
//						}
//						System.out.println("termindo de analizadr"+descriptor.id());
//						
//					} catch ( Exception e) {
//						e.printStackTrace();
//					}
////				}
////			});
// catch (Throwable e) {
//						e.printStackTrace();
//					}
//			
//		}
//// 		executor.shutdown();
//// 		while(!executor.isTerminated()){
//// 			
//// 		}
// 		System.out.println("termino virtal machone");
// 		return robots;
//	}
//	
	
	public static  ArrayList<RobotInformation> getRunningRobot(){
		ArrayList<RobotInformation> robots=new ArrayList<RobotInformation>();
        try{
        	
        	MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
            Set<Object> vmlist = new HashSet<Object>(local.activeVms());
            for (Object id : vmlist) {
                // 1234 - Specifies the Java Virtual Machine identified by lvmid 1234 on an unnamed host. 
                //This string is transformed into the absolute form //1234, which must be resolved against 
                //a HostIdentifier. \*/
              MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + id));
                ///\* take care of class file and jar file both \*/
//             String processname = MonitoredVmUtil.mainClass(vm, true);
//             String jvmArgs = MonitoredVmUtil.jvmArgs(vm);
                String commandLine = MonitoredVmUtil.commandLine(vm);
//                String mainArgs = MonitoredVmUtil.mainArgs(vm);
                
                if(commandLine.contains("bot-1.0.jar")){
             	   String idString = Integer.toString((Integer)id);
             	   VirtualMachine vmT=VirtualMachine.attach(idString);
         			String robotId = vmT.getSystemProperties().getProperty("robotId");
         			System.out.println(robotId);
					if(robotId!=null){
						RobotInformation robotInfo=new RobotInformation();
						robotInfo.setIdProcess((Integer)id);
						robotInfo.setRobotId(Long.parseLong(robotId));
						robotInfo.setAppName(vmT.getSystemProperties().getProperty("appName"));
						Properties robotProperties = vmT.getSystemProperties();
						Set<Object> keySet = robotProperties.keySet();
						HashMap<String,String> properties=new HashMap<String,String>();
						for (Object object : keySet) {
							String propertyName=(String)object;
							if(propertyName.contains("robot_")){
								propertyName=propertyName.replaceAll("robot_","");
								propertyName=propertyName.replaceAll("_", " ");
								String propertyValue = (String)robotProperties.get(object);
								properties.put(propertyName, propertyValue);
							}
						}
						robotInfo.setPropierties(properties);
						robots.add(robotInfo);
						
					}
                }
              }
        }catch(Exception ex){
        	
        }
        
        return robots;
        
	}
	
	
	private static void getApplicationActiveJPS(){
		String args[]={"-v"};
		ArrayList<JavaProcessInfo> runningApps = JpsManager.getRunningApps(args);
    	for (JavaProcessInfo javaProcessInfo : runningApps) {
    		String data = javaProcessInfo.getData();
    		System.out.println("data"+data);
    		if(data.contains("Robot")){
    			String[] split = data.split("=");
    			String idenfy=split[1];
    			if(idenfy.contains("Robot")){
    			
    			}
    			
    		}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<RobotInformation> runningRobot = VirtualMachineExaminator.getRunningRobot();
		System.out.println(runningRobot);
//		System.out.println(runningRobot);
//		VirtualMachineExaminator.getApplicationActiveJPS();
	}
	
}
