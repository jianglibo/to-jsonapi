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
	
	public String calUrl() {
		if (this.id == null) {
			return "/" + type;
		} else {
			return "/" + type + "/" + id;
		}
	}
	
	public String calUrl(String baseUrlWithSlashEnd) {
		if (this.id == null) {
			return baseUrlWithSlashEnd + type;
		} else {
			return baseUrlWithSlashEnd + type + "/" + id;
		}
	}

}
