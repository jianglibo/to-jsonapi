package com.jianglibo.tojsonapi.model;

import java.util.Date;

import com.jianglibo.tojsonapi.reflect.JsonapiField;
import com.jianglibo.tojsonapi.reflect.JsonapiFieldIgnore;
import com.jianglibo.tojsonapi.reflect.JsonapiResource;

@JsonapiResource(type="hello")
public class MyUserWithAnnotation extends BaseModel {
	
	@JsonapiField(name = "un")
	private String username;
	
	private Date birthDay;
	
	@JsonapiFieldIgnore
	private String ignored;
	
	private long uniqueNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public long getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(long uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}
	
	public String getIgnored() {
		return ignored;
	}

	public void setIgnored(String ignored) {
		this.ignored = ignored;
	}

	
}
