package com.gdc.nms.robot.util;

import java.util.Locale;
import java.util.ResourceBundle;


public class Language {
	private static ResourceBundle BUNDLE;
	
	public static void load(){
		BUNDLE=ResourceBundle.getBundle("languaje.language");
		
	}
	
	public static void load(Locale locale){
		BUNDLE=ResourceBundle.getBundle("languaje.language",locale);
	}
	
	public static String get(String key){
		return BUNDLE.getString(key);
	}
	
	
	public static void main(String[] args) {
		Language.load();
		String string = Language.get("license.panel.title");
		System.out.println("*"+string);
	}
	
	
}
