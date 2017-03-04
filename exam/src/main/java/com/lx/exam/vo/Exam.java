package com.lx.exam.vo;

import java.util.Date;

import com.lx.exam.po.PoExam;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class Exam {
	private Long id;
	private Long paperId;
	private String paperName;
	private Long userId;
	private String userName;
	private String startTime;
	private String endTime;
	private String ip;
	private Integer score;
	private Integer status;
	private String data;
	
	public Exam(){}
	public Exam(PoExam poExam){
		ObjectUtil.o2o(this, poExam);
		paperId=poExam.getPoPaper().getId();
		paperName=poExam.getPoPaper().getName();
		userId=poExam.getPoUser().getId();
		userName=poExam.getPoUser().getRealname();
		startTime=DateUtil.format(poExam.getStartTime(),"yyyy-MM-dd HH:mm:ss");
		endTime=DateUtil.format(poExam.getEndTime(),"yyyy-MM-dd HH:mm:ss");
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
