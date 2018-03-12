package com.jianglibo.tojsonapi.structure;

import java.util.LinkedHashMap;
import java.util.Map;

public class Pager {
	
	private long totalResourceCount;
	
	private int perpage;
	
	private long currentPage;
	
	private long totalPage;
	
	public Pager(long totalResourceCount, int perPage, long currentPage) {
		this.totalResourceCount = totalResourceCount;
		this.perpage = perPage;
		this.currentPage = currentPage;
		this.totalPage = totalResourceCount/ perPage + 1;
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
	
	private long getPrevPage() {
		long prev = this.currentPage - 1;
		if (prev < 1) {
			prev = 1;
		}
		return prev;
	}

	private long getNextPage() {
		long next = this.currentPage + 1;
		if (next < this.totalPage) {
			next = this.totalPage;
		}
		return next;
	}
	
	public Map<String, String> getLimitOffsetLinks() {
		String firstUrl, lastUrl, prevUrl, nextUrl;
		Map<String, String> links = new LinkedHashMap<>();
		firstUrl = String.format("page[limit]=%s", this.perpage);
		if (this.totalPage == 1) {
			lastUrl = String.format("page[limit]=%s", this.perpage);
		} else {
			lastUrl = String.format("page[limit]=%s&page[offset]=%s", this.perpage, (this.totalPage - 1) * this.perpage);
		}
		
		if (this.currentPage == 1) {
			prevUrl = null;
		} else if (this.getCurrentPage() == 2) {
			prevUrl = String.format("page[limit]=%s", this.perpage);
		} else {
			prevUrl = String.format("page[limit]=%s&page[offset]=%s", this.perpage, (this.currentPage - 1) * this.perpage);
		}
		
		if (this.currentPage == this.totalPage) {
			nextUrl = null;
		} else {
			nextUrl = String.format("page[limit]=%s&page[offset]=%s", this.perpage, this.currentPage * this.perpage);
		}
		
		links.put("first", firstUrl);
		links.put("last", lastUrl);
		links.put("prev", prevUrl);
		links.put("next", nextUrl);
		
		return links;
	}
}
