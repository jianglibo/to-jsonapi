package com.jianglibo.tojsonapi.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonApiDocument<T> implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	private T pojo;
	
	private List<T> pojos;
	
	private List<JsonapiError> errors;
	
	private Map<String, Object> meta;
	
	private Links links = new Links();
	
	private List<ResourceObject<?>> included;
	
	public JsonApiDocument(T pojo) {
		this.pojo = pojo;
	}
	
	public JsonApiDocument(List<T> pojos) {
		this.pojos = pojos;
	}
	
	public void addSelfLink(String url) {
		this.links.addStringLink("self", url);
	}
	
	public void addRelatedLink(String url) {
		this.links.addStringLink("related", url);
	}
	
	@Override
	public Map<String, Object> asMap() {
		if (this.errors == null) {
			if (this.pojos != null) {
				map.put("data", pojos.stream().map(pj -> new ResourceObject<>(pj)).map(jo -> jo.asMap()).collect(Collectors.toList()));
			} else if (pojo != null) {
				map.put("data", new ResourceObject<T>(pojo).asMap());
			} else {
				map.put("data", new ArrayList<>());
			}
			map.remove("errors");
		} else {
			map.put("errors", this.errors);
			map.remove("data");
		}
		map.put("meta", this.meta);
		if (this.links != null) {
			map.put("links", links.asMap());
		}
		if (this.included != null) {
			map.put("included", this.included);
		}
		return map;
	}
}
