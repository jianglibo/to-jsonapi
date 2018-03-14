package com.jianglibo.tojsonapi.structure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pager {
	
	protected static Pattern pagePattern = Pattern.compile("page\\[([^]]+)\\]=(\\d+)");
	
	private long totalResourceCount;
	
	private int perpage;
	
	private long currentPage;
	
	private long totalPage;
	
	private String currentUrl;
	
	public Pager() {}
	
	public Pager(long totalResourceCount, String currentUrl) {
		this.totalResourceCount = totalResourceCount;
		this.currentUrl = currentUrl;
		initByUrl();
	}
	
	private void initByUrl() {
		Matcher m = pagePattern.matcher(this.currentUrl);
		String offset = "";
		while(m.find()) {
			String ps = m.group(1);
			switch (ps) {
			case "limit":
				this.perpage = Integer.valueOf(ps); 
				break;
			case "offset":
				offset = ps;
			default:
				break;
			}
		}
	}

	public Pager(long totalResourceCount, int perPage, long currentPage) {
		this.totalResourceCount = totalResourceCount;
		this.perpage = perPage;
		this.currentPage = currentPage;
		initAndFix();
	}

	private void initAndFix() {
		if (this.perpage == 0) {
			this.perpage = 10;
		}
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
		this.totalPage = this.totalResourceCount / this.perpage;
		if (this.totalResourceCount % this.perpage != 0) {
			this.totalPage = this.totalPage + 1;
		}
		if (this.totalPage == 0L) {
			this.totalPage = 1;
		}
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
	}

	public long getTotalResourceCount() {
		return totalResourceCount;
	}

	public void setTotalResourceCount(long totalResourceCount) {
		this.totalResourceCount = totalResourceCount;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	
	public Map<String, String> getLimitOffsetLinks() {
		return getLimitOffsetLinks("");
	}
	
	public Map<String, String> getLimitOffsetLinks(String urlPrefix) {
		String firstUrl, lastUrl, prevUrl, nextUrl, alteredPrefix;
		if (urlPrefix != null && urlPrefix.length() > 0) {
			if (urlPrefix.indexOf('?') == -1) {
				alteredPrefix = urlPrefix + "?";
			} else {
				if (!urlPrefix.endsWith("?")) {
					if (!urlPrefix.endsWith("&")) {
						alteredPrefix = urlPrefix + "&";
					} else {
						alteredPrefix = urlPrefix;
					}
				} else {
					alteredPrefix = urlPrefix;
				}
			}
		} else {
			alteredPrefix = "";
		}
		firstUrl = String.format("page[limit]=%s", this.perpage);
		if (this.totalPage == 1) {
			lastUrl = String.format("%spage[limit]=%s",alteredPrefix, this.perpage);
		} else {
			lastUrl = String.format("%spage[offset]=%s&page[limit]=%s", alteredPrefix, (this.totalPage - 1) * this.perpage, this.perpage);
		}
		
		if (this.currentPage == 1) {
			prevUrl = null;
		} else if (this.getCurrentPage() == 2) {
			prevUrl = String.format("%spage[limit]=%s", alteredPrefix, this.perpage);
		} else {
			prevUrl = String.format("%spage[offset]=%s&page[limit]=%s", alteredPrefix, (this.currentPage - 1) * this.perpage, this.perpage);
		}
		
		if (this.currentPage == this.totalPage) {
			nextUrl = null;
		} else {
			nextUrl = String.format("%spage[offset]=%s&page[limit]=%s", alteredPrefix, this.currentPage * this.perpage, this.perpage);
		}
		
		return assembleLinks(firstUrl, lastUrl, prevUrl, nextUrl);
		
	}
	
	protected Map<String, String> assembleLinks(String first, String last, String prev, String next) {
		Map<String, String> links = new LinkedHashMap<>();
		
		links.put("first", first);
		links.put("last", last);
		links.put("prev", prev);
		links.put("next", next);
		
		return links;
	}
}
