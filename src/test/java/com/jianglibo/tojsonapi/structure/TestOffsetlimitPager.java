package com.jianglibo.tojsonapi.structure;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jianglibo.tojsonapi.structure.PagerBuilder.PaginationType;
import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestOffsetlimitPager {
	
	@Test
	public void testNoPageParameter() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(0, "http://www.abc.com"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":null,\r\n" + 
				"  \"last\":null,\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
		
	}
	
	
	@Test
	public void testZeroResourceCount() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(0, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
		
	}
	
	@Test
	public void testExactOnePage() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(10, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
		
	}
	
	@Test
	public void testMoreThanOnePage() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(12, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=10&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=10&page[limit]=10\"\r\n" + 
				"}\r\n" + 
				"", s, true);
		
	}
	
	@Test
	public void testExactTwoPage() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(20, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=10&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=10&page[limit]=10\"\r\n" + 
				"}\r\n" + 
				"", s, true);
		
	}
	
	
	@Test
	public void testManyPage() throws JSONException {
		OffsetlimitPager olp = (OffsetlimitPager) new PagerBuilder(PaginationType.OFFSET_LIMIT, "limit", "offset").newInstance();
		
		String s = UtilForTt.toJson(olp.calLinks(65, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=60&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=10&page[limit]=10\"\r\n" + 
				"}", s, true);
		
	}

}
