package com.jianglibo.tojsonapi.model;

import java.util.Date;

public class MyUserNoAnnotation extends BaseModel {
	
	private String username;
	
	private Date birthDay;
	
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
	
}
