package com.lx.exam.vo;

import com.lx.exam.po.PoPaper;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class Paper {
	private Long id;
	private String name;
	private Integer status;
	private String startTime;
	private String endTime;
	private String enrollStart;
	private String enrollEnd;
	private Integer duration;
	private String description;
	private String showTime;
	private Long posterId;
	private String posterName;
	private Long modifyorId;
	private String modifyorName;
	private Integer questionOrder;
	private Integer isRandom;
	private Integer isAutoCheck;
	private Integer isShowAnswer;
	public Paper(){}
	public Paper(PoPaper poPaper){
		ObjectUtil.o2o(this, poPaper);
		posterId=poPaper.getPoster().getId();
		posterName=poPaper.getPoster().getRealname();
		modifyorId=poPaper.getModifyor().getId();
		modifyorName=poPaper.getModifyor().getRealname();
		startTime=DateUtil.format(poPaper.getStartTime(),"yyyy-MM-dd HH:mm:ss");
		endTime=DateUtil.format(poPaper.getEndTime(),"yyyy-MM-dd HH:mm:ss");
		enrollStart=DateUtil.format(poPaper.getEnrollStart(),"yyyy-MM-dd HH:mm:ss");
		enrollEnd=DateUtil.format(poPaper.getEnrollEnd(),"yyyy-MM-dd HH:mm:ss");
		showTime=DateUtil.format(poPaper.getShowTime(),"yyyy-MM-dd HH:mm:ss");
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getEnrollStart() {
		return enrollStart;
	}
	public void setEnrollStart(String enrollStart) {
		this.enrollStart = enrollStart;
	}
	public String getEnrollEnd() {
		return enrollEnd;
	}
	public void setEnrollEnd(String enrollEnd) {
		this.enrollEnd = enrollEnd;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
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
	public Integer getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
	public Integer getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(Integer isRandom) {
		this.isRandom = isRandom;
	}
	public Integer getIsAutoCheck() {
		return isAutoCheck;
	}
	public void setIsAutoCheck(Integer isAutoCheck) {
		this.isAutoCheck = isAutoCheck;
	}
	public Integer getIsShowAnswer() {
		return isShowAnswer;
	}
	public void setIsShowAnswer(Integer isShowAnswer) {
		this.isShowAnswer = isShowAnswer;
	}
	
}
