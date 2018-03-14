package com.jianglibo.tojsonapi.structure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestPager {
	
	@Test
	public void tPagePattern() {
		Matcher m = Pager.pagePattern.matcher("page[abc]=55");
		assertTrue(m.matches());
		m = Pager.pagePattern.matcher("page[abc]]=55");
		assertFalse(m.matches());
	}
	
	@Test
	public void tPagePatterns() {
		Matcher m = Pager.pagePattern.matcher("page[abc]=55&page[yy]=60");
		int i = 0;
		String s = "";
		while(m.find()) {
			i++;
			s+= m.group(1);
		}
		assertThat("should find 2 matches", i, equalTo(2));
		assertThat(s, equalTo("abcyy"));
	}
	
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
