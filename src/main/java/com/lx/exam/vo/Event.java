package com.lx.exam.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoEvent;
import com.lx.exam.util.DateUtil;

public class Event implements Serializable{
	private Long id;
	private String name;
	private String description;
	private Integer status;
	private String groupRule;
	private String steps;
	private Integer currentStep;
	private String enrollStartTime;
	private String enrollEndTime;
	private String createTime=DateUtil.format(new Date());
	
	public Event(){}
	public Event(PoEvent poEvent){
		BeanUtils.copyProperties(poEvent,this);
		this.enrollStartTime=DateUtil.format(poEvent.getEnrollStartTime());
		this.enrollEndTime=DateUtil.format(poEvent.getEnrollEndTime());
		this.createTime=DateUtil.format(poEvent.getCreateTime()); 
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGroupRule() {
		return groupRule;
	}
	public void setGroupRule(String groupRule) {
		this.groupRule = groupRule;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public Integer getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}
	public String getEnrollStartTime() {
		return enrollStartTime;
	}
	public void setEnrollStartTime(String enrollStartTime) {
		this.enrollStartTime = enrollStartTime;
	}
	public String getEnrollEndTime() {
		return enrollEndTime;
	}
	public void setEnrollEndTime(String enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
