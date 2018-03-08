package com.jianglibo.tojsonapi.structure;

import com.jianglibo.tojsonapi.util.AnnotationUtil;
import com.jianglibo.tojsonapi.util.ResourceUrl;

public class ResourceObjectBuilder {
	
	private String baseUrl;
	
	public ResourceObjectBuilder(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public ResourceObject build(Object resource) {
		ResourceObject ro = new ResourceObject(resource);
		String selfLink = new ResourceUrl(AnnotationUtil.getType(resource.getClass()), ro.getId()).calUrl(baseUrl); 
		ro.addSelfLink(selfLink);
		return ro;
	}

}
