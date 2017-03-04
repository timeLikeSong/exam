package com.lx.exam.vo;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoSchool;

public class School {
	private Long id;
	private String name;
	private String place;
	private String type;
	private String properties;
	public School(){}
	
	public School(PoSchool poSchool){
		BeanUtils.copyProperties(poSchool, this);
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
}
