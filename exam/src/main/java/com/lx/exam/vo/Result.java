package com.lx.exam.vo;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoResult;

public class Result {
	private Long id;
	private Long examId;
	private String examName;
	private Long userId;
	private String userName;
	private Boolean isPass;
	private Double personScore;
	private Double teamAverageScore;
	public Result(){}
	public Result(PoResult poResult){
		BeanUtils.copyProperties(poResult,this);
		examId=poResult.getPoExam().getId();
		examName=poResult.getPoExam().getName();
		userId=poResult.getPoUser().getId();
		userName=poResult.getPoUser().getUsername();
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
	public Boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	public Double getPersonScore() {
		return personScore;
	}
	public void setPersonScore(Double personScore) {
		this.personScore = personScore;
	}
	public Double getTeamAverageScore() {
		return teamAverageScore;
	}
	public void setTeamAverageScore(Double teamAverageScore) {
		this.teamAverageScore = teamAverageScore;
	}
	
	
}
