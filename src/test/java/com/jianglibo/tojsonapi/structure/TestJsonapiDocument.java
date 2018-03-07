package com.jianglibo.tojsonapi.structure;

import org.junit.Test;

import com.jianglibo.tojsonapi.model.MyUserWithAnnotation;
import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJsonapiDocument {
	
	@Test
	public void testADoc() {
		MyUserWithAnnotation user = UtilForTt.userWithAnnotation();
		JsonApiDocument<MyUserWithAnnotation> jd = new JsonApiDocument<MyUserWithAnnotation>(user);
		UtilForTt.printJson(jd.asMap());
	}

}
