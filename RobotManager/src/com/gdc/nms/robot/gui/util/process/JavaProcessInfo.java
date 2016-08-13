package com.gdc.nms.robot.gui.util.process;

import java.util.Date;

public class JavaProcessInfo {
	private String name;
	private long processid;
	private String commandLine;
	private Date date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProcessid() {
		return processid;
	}
	public void setProcessid(long processid) {
		this.processid = processid;
	}
	public String getCommandLine() {
		return commandLine;
	}
	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}
	
	public void setCreationDate(Date date){
		this.date=date;
	}
	
	
	public Date getCreationDate(){
		return date;
	}
	@Override
	public String toString() {
		return "JavaProcessInfo [name=" + name + ", processid=" + processid + ", commandLine=" + commandLine + "]";
	}
	
	

}
