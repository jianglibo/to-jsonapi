package com.jianglibo.tojsonapi.structure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jianglibo.tojsonapi.model.BaseModel;
import com.jianglibo.tojsonapi.model.MyUserNoAnnotation;

public class TestMyUserNoAnnotation {
	
	@Test
	public void testClassEqual() {
		assertTrue("class can test euqal by ==.", BaseModel.class.getSuperclass() == Object.class);
	}
	
	@Test
	public void testOne() {
		MyUserNoAnnotation user = new MyUserNoAnnotation();
		ResourceObject ro = new ResourceObjectBuilder("").build(user);
		assertThat("unassigned id should be a string 0", ro.getId(), equalTo("0"));
		assertFalse("attributes shouldn't contains id field.", ro.getAttributes().containsKey("id"));
		assertFalse(ro.getAttributes().containsKey("type"));
		assertThat(ro.getType(), equalTo("myusernoannotations"));
		
		assertThat("should contain 3 fields.", ro.getAttributes().keySet().size(), equalTo(3));
		
		assertNull("unsigned value should be null.", ro.getAttributes().get("username"));
		assertNull("unsigned value should be null.", ro.getAttributes().get("birthDay"));
		assertThat("unsigned long value should be 0.", ro.getAttributes().get("uniqueNumber"), equalTo(0L));
		
		assertTrue("the key exists despite value is null.", ro.getAttributes().containsKey("username"));
		assertTrue("the key exists despite value is null.", ro.getAttributes().containsKey("birthDay"));

	}

}
