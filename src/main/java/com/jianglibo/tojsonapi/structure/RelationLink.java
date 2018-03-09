package com.jianglibo.tojsonapi.structure;

import java.util.LinkedHashMap;
import java.util.Map;

public class RelationLink {
	
	private Map<String, Object> links = new LinkedHashMap<>();
	
	public void addSelfLink(String url) {
		this.links.put("self", url);
	}
	
	public void addRelatedLink(String url) {
		this.links.put("related", url);
	}

	public Map<String, Object> getLinks() {
		return links;
	}

	public void setLinks(Map<String, Object> links) {
		this.links = links;
	}
	
	

}
