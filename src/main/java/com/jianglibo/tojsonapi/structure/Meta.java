package com.jianglibo.tojsonapi.structure;

import java.util.Map;

public class Meta implements CanAsMap {
	
	private Map<String, Object> map;

	@Override
	public Map<String, Object> asMap() {
		return map;
	}

}
