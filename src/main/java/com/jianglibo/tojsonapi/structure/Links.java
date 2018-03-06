package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

public class Links implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	public void addStringLink(String linkName, String linkUrl) {
		this.asMap().put(linkName, linkUrl);
	}
	
	public void addObjectLink(String linkName, LinkObject linkObject) {
		this.asMap().put(linkName, linkObject);
	}

	@Override
	public Map<String, Object> asMap() {
		return map;
	}

}
