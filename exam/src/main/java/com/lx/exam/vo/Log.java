package com.lx.exam.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoLog;
import com.lx.exam.util.DateUtil;


public class Log implements Serializable {
	private static final long serialVersionUID = -521356121612825099L;
	private Long id;
	private String operation;
	private Long operatorId;
	private String operatorName;
	private String time;
	private String ip;
	public Log() {
	}

	public Log(PoLog poLog) {
		BeanUtils.copyProperties(poLog, this);
		time = DateUtil.format(poLog.getTime());
		operatorId = poLog.getOperator().getId();
		operatorName = poLog.getOperator().getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
