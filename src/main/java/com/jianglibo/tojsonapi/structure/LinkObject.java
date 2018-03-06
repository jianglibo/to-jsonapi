package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

public class LinkObject {
	
	private String href;
	
	private Map<String, Object> meta;
	
	public void addMeta(String key, Object value) {
		if (this.meta == null) {
			this.meta = new HashMap<>();
		}
		this.meta.put(key, value);
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
