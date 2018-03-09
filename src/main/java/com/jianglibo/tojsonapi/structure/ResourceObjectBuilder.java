package com.jianglibo.tojsonapi.structure;

public class ResourceObjectBuilder {
	
	private String baseUrl;
	
	public ResourceObjectBuilder(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public ResourceObject build(Object resource) {
		ResourceObject ro = new ResourceObject(this.baseUrl, resource);
		ro.buildAttributes();
		return ro;
	}

}
