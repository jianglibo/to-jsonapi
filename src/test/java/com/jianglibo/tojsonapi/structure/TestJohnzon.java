package com.jianglibo.tojsonapi.structure;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jianglibo.tojsonapi.model.MyUserNoAnnotation;
import com.jianglibo.tojsonapi.model.MyUserWithAnnotation;
import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJohnzon {
	
	@Test
	public void testNoAnnotationEmptyEntity() throws JSONException {
		MyUserNoAnnotation user = new MyUserNoAnnotation();
		ResourceObject ro = new ResourceObject(user);
		
		final Mapper mapper = new MapperBuilder().setSkipNull(false).setPretty(true).build();
		StringWriter sw = new StringWriter();
		Map<String, Object> map = ro.asMap();
		mapper.writeObject(map, sw);
		UtilForTt.printme(sw);
		
		String expected = "{\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"birthDay\":null,\r\n" + 
				"    \"uniqueNumber\":0,\r\n" + 
				"    \"username\":null\r\n" + 
				"  },\r\n" + 
				"  \"id\":null,\r\n" + 
				"  \"type\":\"myusernoannotations\"\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expected, sw.toString(), true);
	}
	
	@Test
	public void testNoAnnotationWithId() throws JSONException {
		
		Date d = new Date(1520236392L * 1000);
		
		MyUserNoAnnotation user = new MyUserNoAnnotation();
		user.setId(556);
		user.setBirthDay(d);
		user.setUniqueNumber(33);
		user.setUsername("myname");
		
		ResourceObject ro = new ResourceObject(user);
		
		final Mapper mapper = new MapperBuilder().setSkipNull(false).setPretty(true).build();
		StringWriter sw = new StringWriter();
		Map<String, Object> map = ro.asMap();
		mapper.writeObject(map, sw);
		UtilForTt.printme(sw);
		
		String expected = "{\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"    \"uniqueNumber\":33,\r\n" + 
				"    \"username\":\"myname\"\r\n" + 
				"  },\r\n" + 
				"  \"id\":null,\r\n" + 
				"  \"type\":\"myusernoannotations\"\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expected, sw.toString(), true);
	}
	
	@Test
	public void testWithAnnotationEmptyEntity() throws JSONException {
		MyUserWithAnnotation user = new MyUserWithAnnotation();
		ResourceObject ro = new ResourceObject(user);
		
		final Mapper mapper = new MapperBuilder().setSkipNull(false).setPretty(true).build();
		StringWriter sw = new StringWriter();
		Map<String, Object> map = ro.asMap();
		mapper.writeObject(map, sw);
		UtilForTt.printme(sw);
		
		String expected = "{\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"birthDay\":null,\r\n" + 
				"    \"un\":null,\r\n" + 
				"    \"uniqueNumber\":0\r\n" + 
				"  },\r\n" + 
				"  \"id\":null,\r\n" + 
				"  \"type\":\"hello\"\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expected, sw.toString(), true);
	}


}
