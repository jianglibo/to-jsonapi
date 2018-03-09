package com.jianglibo.tojsonapi.util;

public class Pager {
	
	private long totalResourceCount;
	
	private int perpage;
	
	private long currentPage;

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

}
