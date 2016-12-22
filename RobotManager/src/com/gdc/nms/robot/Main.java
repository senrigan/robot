package com.gdc.nms.robot;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.Language;

public class Main {

	public static void main(String[] args) {

		Language.load();
		RobotManager robotManager = new RobotManager();
		robotManager.start();
	}
}
