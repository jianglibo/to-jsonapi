package com.jianglibo.tojsonapi.structure;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

//import com.jianglibo.tojsonapi.structure.PagerBuilder.PaginationType;
import com.jianglibo.tojsonapi.util.UtilForTt;

public class TestOffsetlimitPager {
	
	private Pager olp = new OffsetlimitPager();
	
	@Test
	public void testNoPageParameter() throws JSONException {
		Pager olp =  new OffsetlimitPager();
		
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
		String s = UtilForTt.toJson(olp.calLinks(65, "http://www.abc.com?page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=60&page[limit]=10\",\r\n" + 
				"  \"prev\":null,\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=10&page[limit]=10\"\r\n" + 
				"}", s, true);
		
	}
	
	@Test
	public void testManyPageMiddle() throws JSONException {
		
		String s = UtilForTt.toJson(olp.calLinks(65, "http://www.abc.com?page[offset]=10&page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?page[offset]=60&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?page[offset]=20&page[limit]=10\"\r\n" + 
				"}", s, true);
		
	}
	
	@Test
	public void testManyPageMiddleExtraParams() throws JSONException {
		
		String s = UtilForTt.toJson(olp.calLinks(65, "http://www.abc.com?x=y&page[offset]=30&page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?x=y&page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?x=y&page[offset]=60&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?x=y&page[offset]=20&page[limit]=10\",\r\n" + 
				"  \"next\":\"http://www.abc.com?x=y&page[offset]=40&page[limit]=10\"\r\n" + 
				"}", s, true);
		
	}
	
	@Test
	public void testManyPageLastExtraParams() throws JSONException {
		
		String s = UtilForTt.toJson(olp.calLinks(65, "http://www.abc.com?x=y&page[offset]=60&page[limit]=10"));
		UtilForTt.printme(s);
		
		JSONAssert.assertEquals("{\r\n" + 
				"  \"first\":\"http://www.abc.com?x=y&page[limit]=10\",\r\n" + 
				"  \"last\":\"http://www.abc.com?x=y&page[offset]=60&page[limit]=10\",\r\n" + 
				"  \"prev\":\"http://www.abc.com?x=y&page[offset]=50&page[limit]=10\",\r\n" + 
				"  \"next\":null\r\n" + 
				"}", s, true);
		
	}

}
