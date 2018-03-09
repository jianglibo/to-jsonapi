package com.jianglibo.tojsonapi.structure;

import java.util.List;
import java.util.stream.Collectors;


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
		return jad;
	}
	
	public JsonApiDocument buildListResource(List<Object> resources, long totalResourceCount, int perpage) {
		List<ResourceObject> rss = resources.stream().map(pojo -> new ResourceObjectBuilder(baseUrl).build(pojo)).collect(Collectors.toList());
		JsonApiDocument jad = new JsonApiDocument(rss);
		jad.addMeta("totalResourceCount", totalResourceCount);
		return jad;
	}

}
