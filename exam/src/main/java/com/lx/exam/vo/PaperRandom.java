package com.lx.exam.vo;

import com.lx.exam.po.PoPaperRandom;
import com.lx.exam.util.ObjectUtil;

public class PaperRandom {
	private Long id;
	private Long paperId;
	private Long userId;
	private String data;
	public PaperRandom(){}
	public PaperRandom(PoPaperRandom poPaperRandom){
		ObjectUtil.o2o(this, poPaperRandom);
		paperId=poPaperRandom.getPoPaper().getId();
		userId=poPaperRandom.getPoUser().getId();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
