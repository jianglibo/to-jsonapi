package com.jianglibo.tojsonapi.structure;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJsonapiDocumentBuilder {
	
	@Test
	public void tbuildSingle() {
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder("/");
		JsonApiDocument jad = jdb.buildSingleResource(UtilForTt.userWithAnnotation(3));
		UtilForTt.printJson(jad.asMap());
	}

	@Test
	public void tbuildList() {
		List<Object> rss = Stream.of(1,2,3).map(id -> UtilForTt.userWithAnnotation(id)).collect(Collectors.toList());
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder("/");
		JsonApiDocument jad = jdb.buildListResource(rss, 100, 12);
		UtilForTt.printJson(jad.asMap());
	}

}
