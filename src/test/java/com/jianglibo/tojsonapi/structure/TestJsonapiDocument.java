package com.jianglibo.tojsonapi.structure;

import org.junit.Test;

import com.jianglibo.tojsonapi.model.MyUserWithAnnotation;
import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJsonapiDocument {
	
	@Test
	public void testADoc() {
		MyUserWithAnnotation user = UtilForTt.userWithAnnotation();
		ResourceObject ro = new ResourceObjectBuilder("/").build(user);
		JsonApiDocument jd = new JsonApiDocument(ro);
		UtilForTt.printJson(jd.asMap());
	}
}
