package com.jianglibo.tojsonapi.model;

import java.util.Date;

public class MyUser extends BaseModel {
	
	private String username;
	
	private Date birthDay;

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
	
}
