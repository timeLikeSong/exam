package com.lx.exam.common.vo;

/**
 * 分页bean
 * 
 * @author redesthorse
 */
public class PageBean {
	private boolean paging;
	private int total; // 总记录数
	private int length=10; // 每页的条数
	private int page=1; // 当前页号
	private int start=0;//开始的索引

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

}
