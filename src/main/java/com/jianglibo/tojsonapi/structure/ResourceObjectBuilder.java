package com.jianglibo.tojsonapi.structure;

public class ResourceObjectBuilder {
	
	private String requestUrl;
	
	public ResourceObjectBuilder(String baseUrl) {
		this.requestUrl = baseUrl;
	}
	
	public ResourceObject build(Object resource) {
		ResourceObject ro = new ResourceObject(this.requestUrl, resource);
		ro.buildAttributes();
		return ro;
	}

}
