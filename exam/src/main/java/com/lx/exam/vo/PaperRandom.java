package com.lx.exam.vo;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoPaperRandom;

public class PaperRandom {
	private Long id;
	private Long paperId;
	private String data;
	public PaperRandom(){}
	public PaperRandom(PoPaperRandom poPaperRandom){
		BeanUtils.copyProperties(poPaperRandom, this);
		paperId=poPaperRandom.getPoPaper().getId();
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
