package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoConfig;

public class Config {
	private Long id;
	private String name;
	private String value;
	public Config(){}
	
	public Config(PoConfig poConfig){
		BeanUtils.copyProperties(poConfig, this);
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
