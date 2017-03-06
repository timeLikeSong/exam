package com.lx.exam.vo;

import java.util.Date;

import com.lx.exam.po.PoEnroll;
import com.lx.exam.util.ObjectUtil;

public class Enroll {
	private Long id;
	private Long paperId;
	private String paperName;
	private Integer status;
	private Date enrollTime;
	
	public Enroll(){}
	public Enroll(PoEnroll poEnroll){
		ObjectUtil.o2o(this, poEnroll);
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
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}
	
}
