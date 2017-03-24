package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoExam;
import com.lx.exam.util.DateUtil;

public class Exam {
	private Long id;
	private String name;
	private Integer step;
	private String faceGroup;
	private String canIn;
	private Long eventId;
	private String eventName;
	private Long paperId;
	private String paperName;
	private String startTime;
	private String endTime;
	private Integer duration;
	
	
	public Exam(){}
	public Exam(PoExam poExam){
		BeanUtils.copyProperties(poExam,this);
		eventId=poExam.getPoEvent().getId();
		eventName=poExam.getPoEvent().getName();
		paperId=poExam.getPoPaper().getId();
		paperName=poExam.getPoPaper().getName();
		startTime=DateUtil.format(poExam.getStartTime());
		endTime=DateUtil.format(poExam.getEndTime());
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
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public String getFaceGroup() {
		return faceGroup;
	}
	public void setFaceGroup(String faceGroup) {
		this.faceGroup = faceGroup;
	}
	public String getCanIn() {
		return canIn;
	}
	public void setCanIn(String canIn) {
		this.canIn = canIn;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
}
