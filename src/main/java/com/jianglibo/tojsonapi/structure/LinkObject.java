package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

public class LinkObject implements CanAsMap {
	
	private Map<String, Object> map = new HashMap<>();
	
	private String href;
	
	private Meta meta;

	@Override
	public Map<String, Object> asMap() {
		return map;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
		this.map.put("href", href);
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
		this.asMap().put("meta", meta);
	}

}
