package com.jianglibo.tojsonapi.util;

import java.io.StringWriter;
import java.util.Date;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

import com.jianglibo.tojsonapi.model.MyUserWithAnnotation;
import com.jianglibo.tojsonapi.structure.ResourceObject;

public class UtilForTt {
	
	private static Date d = new Date(1520236392L * 1000);
	
	public static final Mapper mapper = new MapperBuilder().setSkipNull(false).setPretty(true).build();
	
	public static void printme(Object o) {
		System.out.println(o);
	}
	
	public static MyUserWithAnnotation userWithAnnotation() {
		MyUserWithAnnotation user = new MyUserWithAnnotation();
		user.setBirthDay(d);
		user.setId(1L);
		user.setUniqueNumber(1L);
		user.setUsername("username");
		return user;
	}
	
	public static MyUserWithAnnotation userWithAnnotation(long id) {
		MyUserWithAnnotation user = new MyUserWithAnnotation();
		user.setBirthDay(d);
		user.setId(id);
		user.setUniqueNumber(1L);
		user.setUsername("username");
		return user;
	}
	
	public static ResourceObject oneRo() {
		return new ResourceObject(userWithAnnotation());
	}
	
	public static void printJson(Object o) {
		StringWriter sw = new StringWriter();
		mapper.writeObject(o, sw);
		UtilForTt.printme(sw);

	}

}
