package com.jianglibo.tojsonapi.structure;

public class PagerBuilder {
	
	public static enum PaginationType {
		OFFSET_LIMIT, PAGE_NUMBER, CURSOR
	}
	
	private PaginationType pt;
	
	private String pagesizeName;
	
	public PagerBuilder(PaginationType pt, String pagesizeName, String...othernames) {
		this.pt = pt;
		this.pagesizeName = pagesizeName;
	}
	
	
	public Pager getNewPager(String currenturl) {
		
	}
	
}
