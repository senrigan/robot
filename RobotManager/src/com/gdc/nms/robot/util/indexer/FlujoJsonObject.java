package com.gdc.nms.robot.util.indexer;

public class FlujoJsonObject {
	private String alias;
	private long id;
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FlujoJsonObject [alias=" + alias + ", id=" + id + "]";
	}
	
	
	
}
