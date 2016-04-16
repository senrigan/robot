package com.gdc.nms.robot.util.indexer;

public class AppJsonObject {
	private long id;
	private String alias;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		return  ""+alias ;
	}
	
	
}
