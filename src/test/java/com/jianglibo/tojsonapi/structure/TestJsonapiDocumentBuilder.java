package com.jianglibo.tojsonapi.structure;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestJsonapiDocumentBuilder {
	
	private Pager pager = new OffsetlimitPager();
	
	@Test
	public void tbuildSingle() throws JSONException {
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder(pager);
		JsonApiDocument jad = jdb.buildSingleResource(UtilForTt.userWithAnnotation(3), "/");
		String s = UtilForTt.toJson(jad.asMap());

		JSONAssert.assertEquals("{\r\n" + 
				"  \"data\":{\r\n" + 
				"    \"id\":\"3\",\r\n" + 
				"    \"type\":\"hello\",\r\n" + 
				"    \"attributes\":{\r\n" + 
				"      \"un\":\"username\",\r\n" + 
				"      \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"      \"uniqueNumber\":1\r\n" + 
				"    },\r\n" + 
				"    \"relationships\":{\r\n" + 
				"      \"roles\":{\r\n" + 
				"        \"links\":{\r\n" + 
				"          \"self\":\"/hello/3/relationships/roles\",\r\n" + 
				"          \"related\":\"/hello/3/roles\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"links\":{\r\n" + 
				"      \"self\":\"/hello/3\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}", s, true);
	}

	@Test
	public void tbuildList() throws JSONException {
		List<Object> rss = Stream.of(1,2,3).map(id -> UtilForTt.userWithAnnotation(id)).collect(Collectors.toList());
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder(pager);
		JsonApiDocument jad = jdb.buildListResource(rss, 100, "/resources/?page[limit]=10");
		String s = UtilForTt.toJson(jad.asMap());
		JSONAssert.assertEquals("{\r\n" + 
				"  \"data\":[\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"1\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"/hello/1/relationships/roles\",\r\n" + 
				"            \"related\":\"/hello/1/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"/hello/1\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"2\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"/hello/2/relationships/roles\",\r\n" + 
				"            \"related\":\"/hello/2/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"/hello/2\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"3\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"/hello/3/relationships/roles\",\r\n" + 
				"            \"related\":\"/hello/3/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"/hello/3\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"meta\":{\r\n" + 
				"    \"totalResourceCount\":100\r\n" + 
				"  },\r\n" + 
				"  \"links\":{\r\n" + 
				"    \"first\":\"/resources/?page[limit]=10\",\r\n" + 
				"    \"last\":\"/resources/?page[offset]=90&page[limit]=10\",\r\n" + 
				"    \"prev\":null,\r\n" + 
				"    \"next\":\"/resources/?page[offset]=10&page[limit]=10\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"", s, true);
	}
	
	@Test
	public void tbuildListFullUrl() throws JSONException {
		List<Object> rss = Stream.of(1,2,3).map(id -> UtilForTt.userWithAnnotation(id)).collect(Collectors.toList());
		JsonapiDocumentBuilder jdb = new JsonapiDocumentBuilder(pager);
		JsonApiDocument jad = jdb.buildListResource(rss, 100, "http://www.abc.com/resources/?page[offset]=40&page[limit]=10");
		String s = UtilForTt.toJson(jad.asMap());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"data\":[\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"1\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"http://www.abc.com/hello/1/relationships/roles\",\r\n" + 
				"            \"related\":\"http://www.abc.com/hello/1/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"http://www.abc.com/hello/1\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"2\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"http://www.abc.com/hello/2/relationships/roles\",\r\n" + 
				"            \"related\":\"http://www.abc.com/hello/2/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"http://www.abc.com/hello/2\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\":\"3\",\r\n" + 
				"      \"type\":\"hello\",\r\n" + 
				"      \"attributes\":{\r\n" + 
				"        \"un\":\"username\",\r\n" + 
				"        \"birthDay\":\"20180305155312+0800\",\r\n" + 
				"        \"uniqueNumber\":1\r\n" + 
				"      },\r\n" + 
				"      \"relationships\":{\r\n" + 
				"        \"roles\":{\r\n" + 
				"          \"links\":{\r\n" + 
				"            \"self\":\"http://www.abc.com/hello/3/relationships/roles\",\r\n" + 
				"            \"related\":\"http://www.abc.com/hello/3/roles\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"links\":{\r\n" + 
				"        \"self\":\"http://www.abc.com/hello/3\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"meta\":{\r\n" + 
				"    \"totalResourceCount\":100\r\n" + 
				"  },\r\n" + 
				"  \"links\":{\r\n" + 
				"    \"first\":\"http://www.abc.com/resources/?page[limit]=10\",\r\n" + 
				"    \"last\":\"http://www.abc.com/resources/?page[offset]=90&page[limit]=10\",\r\n" + 
				"    \"prev\":\"http://www.abc.com/resources/?page[offset]=30&page[limit]=10\",\r\n" + 
				"    \"next\":\"http://www.abc.com/resources/?page[offset]=50&page[limit]=10\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"", s, true);
	}

}
