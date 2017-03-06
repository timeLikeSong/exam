package com.lx.exam.vo;

import com.lx.exam.po.PoNewsType;
import com.lx.exam.util.ObjectUtil;

public class NewsType {
	private Long id;
	private String name;
	public NewsType(){}
	public NewsType(PoNewsType poNewsType){
		ObjectUtil.o2o(this, poNewsType);
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
	
}
