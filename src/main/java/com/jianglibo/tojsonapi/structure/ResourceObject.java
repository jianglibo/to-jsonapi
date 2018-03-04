package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

import com.jianglibo.tojsonapi.reflect.JsonapiResource;

public class ResourceObject<T> implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();

	private String id;
	private String type;
	
	private T resourceObject;
	
	public ResourceObject(T resourceObject) {
		this.resourceObject = resourceObject;
		this.buildAttributes();
	}

	private void buildAttributes() {
		
		
	}

	@Override
	public Map<String, Object> asMap() {
		map.put("id", id);
		map.put("type", type);
//		map.putAll(attributes.asMap());
		return map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
