package com.jianglibo.tojsonapi.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestResourceUrl {
	
	@Test
	public void build() {
		ResourceUrl ru = new ResourceUrl("hello");
		assertThat(ru.calUrl(), equalTo("/hello"));
		
		ru = new ResourceUrl("hello", 5);
		assertThat(ru.calUrl(), equalTo("/hello/5"));

	}
}
