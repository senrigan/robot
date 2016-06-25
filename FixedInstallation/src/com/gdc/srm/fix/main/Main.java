package com.gdc.srm.fix.main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gdc.srm.fix.util.Installation;
import com.gdc.srm.fix.util.VBSUtils;
import com.gdc.srm.fix.util.os.windows.WindowsOS;


public class Main {
	public static void main(String[] args) {
		System.out.println("starting fixed registry for srm Installed");
		String specialFolder = VBSUtils.getSpecialFolder(VBSUtils.SF_DESKTOP);
		Path deskTopPath = Paths.get(specialFolder).resolve("SRM.lnk");
		Path srmInstall = Installation.getInstallaPath().resolve("SRM.EXE");
		WindowsOS windowsUtil=new WindowsOS();
		try {
			windowsUtil.createShortcut(srmInstall.toString(),
					Installation.getInstallaPath().toString()," ",
					Installation.getInstallaPath().resolve("images")
			        .resolve("gdc_logo.ico").toString(),deskTopPath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
