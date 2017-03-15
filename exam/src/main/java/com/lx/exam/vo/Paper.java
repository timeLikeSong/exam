package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoPaper;
import com.lx.exam.util.DateUtil;

public class Paper {
	private Long id;
	private String name;
	private String description;
	private Long posterId;
	private String posterName;
	private Long modifyorId;
	private String modifyorName;
	private Integer viewOrder;
	private Boolean isRandom;
	private Boolean isAutoCheck;
	private Boolean isShowAnswer;
	private String data;
	private Double totalScore;
	private String createDate;
	private String modifyDate;
	public Paper(){}
	public Paper(PoPaper poPaper){
		BeanUtils.copyProperties(poPaper, this);
		posterId=poPaper.getPoster().getId();
		posterName=poPaper.getPoster().getUsername();
		modifyorId=poPaper.getModifyor().getId();
		modifyorName=poPaper.getModifyor().getUsername();
		createDate = DateUtil.format(poPaper.getCreateDate());
		modifyDate=DateUtil.format(poPaper.getModifyDate());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPosterId() {
		return posterId;
	}
	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public Long getModifyorId() {
		return modifyorId;
	}
	public void setModifyorId(Long modifyorId) {
		this.modifyorId = modifyorId;
	}
	public String getModifyorName() {
		return modifyorName;
	}
	public void setModifyorName(String modifyorName) {
		this.modifyorName = modifyorName;
	}
	public Integer getViewOrder() {
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}
	public Boolean getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(Boolean isRandom) {
		this.isRandom = isRandom;
	}
	public Boolean getIsAutoCheck() {
		return isAutoCheck;
	}
	public void setIsAutoCheck(Boolean isAutoCheck) {
		this.isAutoCheck = isAutoCheck;
	}
	public Boolean getIsShowAnswer() {
		return isShowAnswer;
	}
	public void setIsShowAnswer(Boolean isShowAnswer) {
		this.isShowAnswer = isShowAnswer;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
