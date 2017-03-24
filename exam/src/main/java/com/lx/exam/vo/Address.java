package com.lx.exam.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoAddress;

public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long pid;
	
	private String name;

	private Integer viewOrder;
	public Address(){}
	public Address(PoAddress poAddress){
		BeanUtils.copyProperties(poAddress, this);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getViewOrder() {
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}


	
	
}
