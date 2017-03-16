package com.lx.exam.vo;

import com.lx.exam.po.PoScore;
import com.lx.exam.util.DateUtil;

public class Score {
	private Long id;
	private Long examId;
	private String examName;
	private Long userId;
	private String userName;
	private Long paperId;
	private String paperName;
	private String data;
	private Double score;
	private String startTime;
	private String submitTime;
	private Integer status;
	
	public Score(){}
	public Score(PoScore poScore){
		examId = poScore.getPoExam().getId();
		examName=poScore.getPoExam().getName();
		userId=poScore.getPoUser().getId();
		userName=poScore.getPoUser().getUsername();
		paperId=poScore.getPoPaper().getId();
		paperName=poScore.getPoPaper().getName();
		startTime=DateUtil.format(poScore.getStartTime());
		submitTime=DateUtil.format(poScore.getSubmitTime());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
