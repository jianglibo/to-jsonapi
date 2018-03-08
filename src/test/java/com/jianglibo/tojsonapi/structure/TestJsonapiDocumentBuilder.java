package com.jianglibo.tojsonapi.structure;

import org.junit.Test;

import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJsonapiDocumentBuilder {
	
	@Test
	public void tbuild() {
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder("/");
		
		JsonApiDocument jad = jdb.buildSingleResource(UtilForTt.userWithAnnotation(3));
		
		UtilForTt.printJson(jad.asMap());
	}

}
