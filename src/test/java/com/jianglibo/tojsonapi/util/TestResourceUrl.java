package com.jianglibo.tojsonapi.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestResourceUrl {
	
	@Test
	public void build() {
		ResourceUrl ru = new ResourceUrl("hello");
		assertThat(ru.getUrl(), equalTo("/hello"));
		
		ru = new ResourceUrl("hello", 5);
		assertThat(ru.getUrl(), equalTo("/hello/5"));

	}
}
