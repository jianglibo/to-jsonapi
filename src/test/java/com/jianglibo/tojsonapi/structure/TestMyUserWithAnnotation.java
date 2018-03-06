package com.jianglibo.tojsonapi.structure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jianglibo.tojsonapi.model.BaseModel;
import com.jianglibo.tojsonapi.model.MyUserWithAnnotation;

public class TestMyUserWithAnnotation {
	
	@Test
	public void testClassEqual() {
		assertTrue("class can test euqal by ==.", BaseModel.class.getSuperclass() == Object.class);
	}
	
	@Test
	public void testOne() {
		MyUserWithAnnotation user = new MyUserWithAnnotation();
		ResourceObject<MyUserWithAnnotation> ro = new ResourceObject<MyUserWithAnnotation>(user);
		assertNull(ro.getId());
		assertFalse("attributes shouldn't contains id field.", ro.getAttributes().containsKey("id"));
		assertFalse(ro.getAttributes().containsKey("type"));
		assertThat(ro.getType(), equalTo("hello"));
		
		assertThat("should contain 3 fields.", ro.getAttributes().keySet().size(), equalTo(3));
		
		assertNull("unsigned value should be null.", ro.getAttributes().get("un"));
		assertNull("unsigned value should be null.", ro.getAttributes().get("birthDay"));
		assertThat("unsigned long value should be 0.", ro.getAttributes().get("uniqueNumber"), equalTo(0L));
		
		assertTrue("the key exists despite value is null.", ro.getAttributes().containsKey("un"));
		assertTrue("the key exists despite value is null.", ro.getAttributes().containsKey("birthDay"));
		assertTrue("the key exists despite value is null.", ro.getAttributes().containsKey("uniqueNumber"));
		
		assertFalse("jsonapiIgnored field shouldn't exists.", ro.getAttributes().containsKey("ignored"));

	}

}
