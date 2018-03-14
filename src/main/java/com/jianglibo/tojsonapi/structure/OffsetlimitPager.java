package com.jianglibo.tojsonapi.structure;

import java.util.Map;
import java.util.regex.Matcher;

public class OffsetlimitPager extends Pager {
	
	private String offsetName;
	private String limitName;

	public OffsetlimitPager(String offsetName, String limitName) {
		this.offsetName = offsetName;
		this.limitName = limitName;
	}
	
	
	public Map<String, String> calLinks(long totalResourceCount, String currentUrl) {
		Matcher m = pagePattern.matcher(currentUrl);
		String offsetValue = null, limitValue = null;
		while(m.find()) {
			String ps = m.group(1);
			if (ps.equals(this.offsetName)) {
				offsetValue = m.group(2);
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
		
		String offsetUrlPtn = "page[" + this.offsetName + "]=\\d+";
		
		first = currentUrl.replaceFirst(offsetUrlPtn, "");
		if (nextOffset >= totalResourceCount) { // only one page.
			next = null;
		} else {
			if (offsetValue != null) {
				next = currentUrl.replaceFirst(offsetUrlPtn, "page[" + this.offsetName + "]=" + nextOffset);
			} else {
				next = currentUrl.replace("page[", "page[" + this.offsetName + "]=" + nextOffset + "&page[");
			}
		}
		
		if (prevOffset < 0) {
			prev = null;
		} else if(prevOffset == 0) {
			prev = currentUrl.replaceFirst(offsetUrlPtn, "");
		} else {
			prev = currentUrl.replaceFirst(offsetUrlPtn, "page[" + this.offsetName + "]=" + prevOffset);
		}
		
		if (lastOffset == totalResourceCount) {
			lastOffset = totalResourceCount - limit;
			if (lastOffset < 0) {
				lastOffset = 0;
			}
		}
		
		if (lastOffset == 0) {
			last = currentUrl.replaceFirst(offsetUrlPtn, "");
		} else {
			if (offsetValue != null) {
				last = currentUrl.replaceFirst(offsetUrlPtn, "page[" + this.offsetName + "]=" + lastOffset);
			} else {
				last = currentUrl.replace("page[", "page[" + this.offsetName + "]=" + lastOffset + "&page[");
			}
		}
		
		return assembleLinks(first, last, prev, next);
	}
	

}
