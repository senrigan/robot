package com.gdc.nms.robot.gui.tree.test;

import java.util.Arrays;

import com.gdc.nms.robot.gui.util.process.jWMI;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String wmiValue = jWMI.getWMIValue("Select * from Win32_Process where name like '%java%' ","name,processid,commandline");
			String[] split = wmiValue.split("\n");
			System.out.println("split"+Arrays.toString(split));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
