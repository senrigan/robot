package com.gdc.nms.robot;









import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.Language;



public class Main {
	
	 public static void main(String[] args) {
		 Language.load();
		 RobotManager robotManager= new RobotManager();
		 robotManager.start();
//		 System.out.println(ImageTest.class.getResource("./")+"gdc.ico");
//		 System.out.println(new ImageIcon(ImageTest.class.getResource("/pic/gdc.ico")).getImage());
	}
	 
}

