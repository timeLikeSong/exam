package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoFunction;

public class Function {
	private Long id;
	private String funcName;
	private String url;
	private Integer status;
	private String icon;
	//0 查看、1管理
	private Integer type;
	private Long pid;
	public Function(){}
	public Function(PoFunction poFunction){
		BeanUtils.copyProperties(poFunction, this);
		if(poFunction.getPoFunction()!=null && poFunction.getPoFunction().getId()!=null){
			this.pid=poFunction.getPoFunction().getId();
		}
	}
	@Override
	public String toString() {
		return "Function [id=" + id + ", funcName=" + funcName + ", url=" + url + ", status=" + status + ", pid=" + pid
				+ "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
