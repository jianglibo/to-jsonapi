package com.jianglibo.tojsonapi.structure;

import java.util.Date;

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
		ResourceObject ro = new ResourceObjectBuilder("/").build(user);
		
		String s = UtilForTt.toJson(ro.asMap());
		UtilForTt.printme(s);
		
		String expected = "{\r\n" + 
				"  \"id\":\"0\",\r\n" + 
				"  \"type\":\"myusernoannotations\",\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"birthDay\":null,\r\n" + 
				"    \"uniqueNumber\":0,\r\n" + 
				"    \"username\":null\r\n" + 
				"  },\r\n" + 
				"  \"links\":{\r\n" + 
				"    \"self\":\"/myusernoannotations/0\"\r\n" + 
				"  }\r\n" + 
				"}";
		JSONAssert.assertEquals(expected, s, true);
	}
	
	@Test
	public void testNoAnnotationWithId() throws JSONException {
		
		Date d = new Date(1520236392L * 1000);
		
		MyUserNoAnnotation user = new MyUserNoAnnotation();
		user.setId(556);
		user.setBirthDay(d);
		user.setUniqueNumber(33);
		user.setUsername("myname");
		
		ResourceObject ro = new ResourceObjectBuilder("/").build(user);
		String s = UtilForTt.toJson(ro.asMap());
		UtilForTt.printme(s);
		
		String expected = "{\r\n" + 
				"  \"id\":\"556\",\r\n" + 
				"  \"type\":\"myusernoannotations\",\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"    \"uniqueNumber\":33,\r\n" + 
				"    \"username\":\"myname\"\r\n" + 
				"  },\r\n" + 
				"  \"links\":{\r\n" + 
				"    \"self\":\"/myusernoannotations/556\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expected, s, true);
	}
	
	@Test
	public void testWithAnnotationEmptyEntity() throws JSONException {
		MyUserWithAnnotation user = new MyUserWithAnnotation();
		ResourceObject ro = new ResourceObjectBuilder("/").build(user);
		
		String s = UtilForTt.toJson(ro.asMap());
		UtilForTt.printme(s);
		
		String expected = "{\r\n" + 
				"  \"id\":\"0\",\r\n" + 
				"  \"type\":\"hello\",\r\n" + 
				"  \"attributes\":{\r\n" + 
				"    \"un\":null,\r\n" + 
				"    \"birthDay\":null,\r\n" + 
				"    \"uniqueNumber\":0\r\n" + 
				"  },\r\n" + 
				"  \"relationships\":{\r\n" + 
				"    \"roles\":{\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"/hello/0/relationships/roles\",\r\n" + 
				"        \"related\":\"/hello/0/roles\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  \"links\":{\r\n" + 
				"    \"self\":\"/hello/0\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expected, s, true);
	}
}
