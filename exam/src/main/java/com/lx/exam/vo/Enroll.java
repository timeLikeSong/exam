package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoEnroll;
import com.lx.exam.util.DateUtil;

public class Enroll {
	private Long id;
	private String type;
	private Long userId;
	private String userName;
	private String realName;
	private String enrollTime;
	private Long eventId;
	private String eventName;
	
	public Enroll(){}
	public Enroll(PoEnroll poEnroll){
		BeanUtils.copyProperties(poEnroll,this);
		userId= poEnroll.getPoUser().getId();
		userName=poEnroll.getPoUser().getUsername();
		realName=poEnroll.getPoUser().getRealname();
		enrollTime = DateUtil.format(poEnroll.getEnrollTime());
		eventId = poEnroll.getPoEvent().getId();
		eventName = poEnroll.getPoEvent().getName();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(String enrollTime) {
		this.enrollTime = enrollTime;
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
	
}
