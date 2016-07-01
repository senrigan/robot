package com.gdc.nms.robot.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class LogLayout extends Layout{
	StringBuffer sbuf = new StringBuffer(128);
	@Override
	public void activateOptions() {
		
	}

	@Override
	public String format(LoggingEvent event) {
		Locale locale=new Locale("mx","MX");
		TimeZone tz= TimeZone.getTimeZone("America/Mexico_City");
		Calendar instance = Calendar.getInstance(tz,locale);
		sbuf.setLength(0);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd H:m:s");
		
		sbuf.append(sim.format(instance.getTime()));
		sbuf.append(" ");
	    sbuf.append(event.getLevel().toString());
	    sbuf.append(" "+event.getLocationInformation().fullInfo+" ");
	    sbuf.append(" - ");
	    sbuf.append(event.getRenderedMessage());
	    sbuf.append(LINE_SEP);
	    return sbuf.toString();
	}

	@Override
	public boolean ignoresThrowable() {
		// TODO Auto-generated method stub
		return true;
	}

	
//	public static void main(String[] args) {
//		Locale locale=new Locale("es","MX");
//		TimeZone tz= TimeZone.getTimeZone("America/Mexico_City");
//		DateFormat df2 = DateFormat.getDateInstance(DateFormat.LONG, new Locale("es","MX"));
//		System.out.println(tz.getDisplayName());
//		Calendar instance = Calendar.getInstance(tz,locale);
//		System.out.println(instance.getTime().toString());
//		String format = df2.format(instance.getTime());
//		System.out.println(format);
//		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd H:m:s");
//		String format2 = sim.format(instance.getTime());
//		System.out.println(format2);
//	}
}
