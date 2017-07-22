package com.li.common.dto;

public class PageInfo {
	private int count;
	private  int page;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PageInfo [count=" + count + ", page=" + page + "]";
	}

}
