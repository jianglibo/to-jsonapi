package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonApiDocument<T> implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	private T pojo;
	
	private List<T> pojos;
	
	private boolean isList = false;
	
	public JsonApiDocument(T pojo) {
		this.pojo = pojo;
	}
	
	public JsonApiDocument(List<T> pojos) {
		this.pojos = pojos;
		this.isList = true;
	}
	
	
	
	@Override
	public Map<String, Object> asMap() {
		if (isList) {
			map.put("data", pojos.stream().map(pj -> new ResourceObject<>(pj)).collect(Collectors.toList()));
		} else {
			map.put("data", new ResourceObject<T>(pojo));
		}
		return map;
	}
	
	

}
