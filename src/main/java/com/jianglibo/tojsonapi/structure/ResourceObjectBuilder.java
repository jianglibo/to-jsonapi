package com.jianglibo.tojsonapi.structure;

public class ResourceObjectBuilder {
	
	private String requestUrl;
	
	public ResourceObjectBuilder(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	public ResourceObject build(Object resource) {
		ResourceObject ro = new ResourceObject(this.requestUrl, resource);
		ro.buildAttributes();
		return ro;
	}

}
