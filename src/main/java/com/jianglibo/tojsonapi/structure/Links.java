package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

public class Links implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	public void addStringLink(String linkName, String linkUrl) {
		this.map.put(linkName, linkUrl);
	}
	
	public void addObjectLink(String linkName, LinkObject linkObject) {
		this.map.put(linkName, linkObject);
	}
	
	public boolean notEmpty() {
		return !this.map.isEmpty();
	}

	@Override
	public Map<String, Object> asMap() {
		return this.map;
	}

}
