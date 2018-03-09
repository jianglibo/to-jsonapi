package com.jianglibo.tojsonapi.model;

import com.jianglibo.tojsonapi.reflect.JsonapiResource;

@JsonapiResource(type = "roles")
public class MyRole extends BaseModel {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
