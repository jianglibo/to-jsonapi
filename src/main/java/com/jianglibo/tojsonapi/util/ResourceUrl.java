package com.jianglibo.tojsonapi.util;

public class ResourceUrl {
	
	private String type;
	
	private String id;
	
	public ResourceUrl(String type) {
		this.type = type;
	}
	
	public ResourceUrl(String type, Object id) {
		this.type = type;
		this.id = id.toString();
	}
	
	public String getUrl() {
		if (this.id == null) {
			return "/" + type;
		} else {
			return "/" + type + "/" + id;
		}
	}

}
