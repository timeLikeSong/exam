package com.lx.exam.common.vo;

/**
 * 分页bean
 * 
 * @author redesthorse
 */
public class PageBean {
	private int total; // 总记录数
	private int rows; // 每页的条数
	private int page; // 当前页号

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
