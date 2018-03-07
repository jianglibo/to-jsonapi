package com.jianglibo.tojsonapi.structure;

import java.util.HashMap;
import java.util.Map;

public class JsonapiError {
	
	private String id;
	private Map<String, Object> links;
	
	private String status;
	private String code;
	
	private String title;
	
	private String detail;
	
	private Map<String, Object> source;
	
	private Map<String, Object> meta;
	
	public void addLink(String linkName, String linkUrl) {
		if (this.links == null) {
			this.links = new HashMap<>();
		}
		this.links.put(linkName, linkUrl);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getLinks() {
		return links;
	}

	public void setLinks(Map<String, Object> links) {
		this.links = links;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, Object> getSource() {
		return source;
	}

	public void setSource(Map<String, Object> source) {
		this.source = source;
	}

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}

}
