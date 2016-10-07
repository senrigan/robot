package com.gdc.srm.robot.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;


public class NecromancerRobot {
	private HashMap<String,String> deadList;
	private ExecutorService executor;
	
	public void searchDeadRobot(){
		deadList=new HashMap<String,String>();
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			if(!appInformation.isServicesRunning()){
				System.out.println("the "+appInformation.getAppName()+"is no running"+new Date().toString());
				if(!deadList.containsKey(appInformation.getAppName())){
					deadList.put(appInformation.getAppName(),appInformation.getBotFile().toString());
					NecromancerWorker woker= new NecromancerWorker(appInformation.getBotFile().toPath(),this);
					executor.execute(woker);
				}else{
					System.out.println("the "+appInformation.getAppName()+"al ready contain for startup"+new Date().toString());
				}
				
			}else{
				System.out.println("the "+appInformation.getAppName()+"is running"+new Date().toString());

			}
		}
	}
	
	
	
	public void initScan(){
		ThreadFactory cutomThreadFactory=new ThreadFactoryBuilder().setNamePrefix("NecromancerPool-Thread").
				setDaemon(false).setPriority(Thread.MAX_PRIORITY).build();
		executor = Executors.newCachedThreadPool(cutomThreadFactory);
		Thread thread=new Thread( new Runnable() {
			public void run() {
				while(true){
					System.out.println("pooling for dead robots"+new Date().toString());
					searchDeadRobot();
					try {
						Thread.sleep(TimeUnit.MINUTES.toMillis(1));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
	}
	
	
	public static void main(String[] args) {
		NecromancerRobot necro=new NecromancerRobot();
		necro.initScan();
	}
	
	
	public void removeFromDeadList(String nameServices){
		deadList.remove(nameServices);
	}
}
