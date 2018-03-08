package com.jianglibo.tojsonapi.structure;

import java.util.List;

import com.jianglibo.tojsonapi.util.AnnotationUtil;
import com.jianglibo.tojsonapi.util.ResourceUrl;

public class JsonapiDocumentBuilder {
	
	private String baseUrl = "/";
	

	public JsonapiDocumentBuilder(String baseUrl) {
		super();
		this.baseUrl = baseUrl;
		if (!this.baseUrl.endsWith("/")) {
			this.baseUrl = this.baseUrl + "/";
		}
	}
	
	public JsonApiDocument buildSingleResource(Object resource) {
		ResourceObject ro = new ResourceObjectBuilder(baseUrl).build(resource);
		JsonApiDocument jad = new JsonApiDocument(ro);
//		ResourceUrl rurl = new ResourceUrl(AnnotationUtil.getType(resource.getClass()), ro.getId());
//		jad.addSelfLink(rurl.calUrl(baseUrl));
		return jad;
	}
	
	public JsonApiDocument buildListResource(Object resource, long totalResourceCount, int perpage) {
		return null;
	}
	
	public JsonApiDocument buildListResource(List<Object> resources, long totalResourceCount, int perpage) {
		return null;
	}

}
