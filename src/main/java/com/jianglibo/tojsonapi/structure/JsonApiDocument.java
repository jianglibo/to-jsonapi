package com.jianglibo.tojsonapi.structure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonApiDocument implements CanAsMap {
	
	private Map<String, Object> map = new LinkedHashMap<>();
	
	private ResourceObject ro;
	
	private List<ResourceObject> ros;
	
	private List<JsonapiError> errors;
	
	private Map<String, Object> meta;

	private Links links = new Links();
	
	private List<ResourceObject> included;
	
	
	public JsonApiDocument(ResourceObject ro) {
		this.ro = ro;
	}
	
	public JsonApiDocument(List<ResourceObject> ros) {
		this.ros = ros;
	}
	
	public void addSelfLink(String url) {
		this.links.addStringLink("self", url);
	}
	
	public void addRelatedLink(String url) {
		this.links.addStringLink("related", url);
	}
	
	public void addMeta(String key, Object value) {
		if (this.meta == null) {
			this.meta = new LinkedHashMap<>();
		}
		this.meta.put(key, value);
	}
	 
	@Override
	public Map<String, Object> asMap() {
		if (this.errors == null) {
			if (this.ros != null) {
				map.put("data", ros.stream().map(jo -> jo.asMap()).collect(Collectors.toList()));
			} else if (ro != null) {
				map.put("data", ro.asMap());
			} else {
				map.put("data", new ArrayList<>());
			}
			map.remove("errors");
		} else {
			map.put("errors", this.errors);
			map.remove("data");
		}
		if (this.meta != null) {
			map.put("meta", this.meta);
		}
		if (this.links != null && this.links.notEmpty()) {
			map.put("links", links.asMap());
		}
		if (this.included != null) {
			map.put("included", this.included);
		}
		return map;
	}
	
}
