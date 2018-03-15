package com.jianglibo.tojsonapi.structure;

import java.util.Map;
import java.util.regex.Matcher;

public class OffsetlimitPager extends Pager {
	
	private final String offsetName;
	private final String limitName;
	
	public OffsetlimitPager() {
		this.offsetName = "offset";
		this.limitName = "limit";
	}

	public OffsetlimitPager(String offsetName, String limitName) {
		this.offsetName = offsetName;
		this.limitName = limitName;
	}
	
	
	@Override
	public Map<String, String> calLinks(long totalResourceCount, String requestUrl) {
		Matcher m = pagePattern.matcher(requestUrl);
		String offsetValue = null, limitValue = null, currentOffsetString = null;
		while(m.find()) {
			String ps = m.group(1);
			if (ps.equals(this.offsetName)) {
				offsetValue = m.group(2);
				currentOffsetString = m.group();
			} else if (ps.equals(this.limitName)) {
				limitValue = m.group(2);
			}
		}
		String first = null, last = null, prev = null, next = null;
		if (limitValue == null) {
			return assembleLinks(first, last, prev, next);
		}
		int limit = Integer.valueOf(limitValue);
		long currentOffset = 0;
		if (offsetValue != null) {
			currentOffset = Integer.valueOf(offsetValue);
		}
		
		long nextOffset = currentOffset + limit;
		long prevOffset = currentOffset - limit;
		long lastOffset = (totalResourceCount / limit ) * limit;
		
		first = removeOffsetParameter(requestUrl, currentOffsetString);
		
		if (nextOffset >= totalResourceCount) { // only one page.
			next = null;
		} else {
			next = replaceOrInsertOffset(requestUrl, currentOffsetString, nextOffset);
		}
		
		if (prevOffset < 0) {
			prev = null;
		} else if(prevOffset == 0) {
			prev = removeOffsetParameter(requestUrl, currentOffsetString);
		} else {
			prev = replaceOrInsertOffset(requestUrl, currentOffsetString, prevOffset);
		}
		
		if (lastOffset == totalResourceCount) {
			lastOffset = totalResourceCount - limit;
			if (lastOffset < 0) {
				lastOffset = 0;
			}
		}
		
		if (lastOffset == 0) {
			last = removeOffsetParameter(requestUrl, currentOffsetString);
		} else {
			last = replaceOrInsertOffset(requestUrl, currentOffsetString, lastOffset);
		}
		
		return assembleLinks(first, last, prev, next);
	}


	private String replaceOrInsertOffset(String currentUrl, String stringTobeReplaced,
			long newValue) {
		String next;
		if (stringTobeReplaced != null) {
			next = currentUrl.replace(stringTobeReplaced, "page[" + this.offsetName + "]=" + newValue);
		} else {
			next = currentUrl.replace("page[", "page[" + this.offsetName + "]=" + newValue + "&page[");
		}
		return next;
	}

}
