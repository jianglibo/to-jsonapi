package com.jianglibo.tojsonapi.structure;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestPager {
	
	@Test
	public void testZeroPage() throws JSONException {
		Pager pager = new Pager(0L, 0, 0L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
	}
	
	
	@Test
	public void testWrongCurrentPage() throws JSONException {
		Pager pager = new Pager(0L, 0, 2L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testExactlyOnePage() throws JSONException {
		Pager pager = new Pager(10L, 10, 1L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testMoreThanOnePage() throws JSONException {
		Pager pager = new Pager(15L, 10, 1L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[offset]=10&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"page[offset]=10&page[limit]=10\"\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testExactlyTwoPage() throws JSONException {
		Pager pager = new Pager(20L, 10, 1L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[offset]=10&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"page[offset]=10&page[limit]=10\"\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testMoreThanTwoPage() throws JSONException {
		Pager pager = new Pager(25L, 10, 2L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[offset]=20&page[limit]=10\",\r\n" + 
				"  \"prev\":\"page[limit]=10\",\r\n" + 
				"  \"next\":\"page[offset]=20&page[limit]=10\"\r\n" + 
				"}\r\n" + 
				"", s, true);
	}
	
	@Test
	public void testManyPage() throws JSONException {
		Pager pager = new Pager(115L, 10, 5L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks());
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"page[offset]=110&page[limit]=10\",\r\n" + 
				"  \"prev\":\"page[offset]=40&page[limit]=10\",\r\n" + 
				"  \"next\":\"page[offset]=50&page[limit]=10\"\r\n" + 
				"}" + 
				"", s, true);
	}
	
	@Test
	public void testWithPrefix() throws JSONException {
		Pager pager = new Pager(115L, 10, 5L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks("http://www.abc.com"));
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=110&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?page[offset]=40&page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=50&page[limit]=10\"\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testWithPrefix1() throws JSONException {
		Pager pager = new Pager(115L, 10, 5L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks("http://www.abc.com?"));
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=110&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?page[offset]=40&page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=50&page[limit]=10\"\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testWithPrefix2() throws JSONException {
		Pager pager = new Pager(115L, 10, 5L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks("http://www.abc.com?abc"));
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?abc&page[offset]=110&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?abc&page[offset]=40&page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?abc&page[offset]=50&page[limit]=10\"\r\n" + 
				"}", s, true);
	}
	
	@Test
	public void testWithPrefix3() throws JSONException {
		Pager pager = new Pager(115L, 10, 5L);
		String s = UtilForTt.toJson(pager.getLimitOffsetLinks("http://www.abc.com?abc&"));
		UtilForTt.printme(s);
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?abc&page[offset]=110&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?abc&page[offset]=40&page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?abc&page[offset]=50&page[limit]=10\"\r\n" + 
				"}", s, true);
	}

}
