package com.lx.exam.vo;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoDataCode;

public class DataCode {
	private Long id;
	private String title;
	private String description;
	private Long pid;
	private String icon;
	
	public DataCode(){}
	public DataCode(PoDataCode poDataCode){
		BeanUtils.copyProperties(poDataCode, this);
		if(poDataCode.getPoDataCode()!=null){
			this.setPid(poDataCode.getPoDataCode().getId());
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "DataCode [id=" + id + ", title=" + title + ", description=" + description + ", pid=" + pid + ", icon="
				+ icon + "]";
	}
	
}
