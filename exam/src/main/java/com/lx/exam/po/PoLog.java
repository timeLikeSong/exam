package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.Log;

import java.util.Date;
@Entity
@Table(name = "T_LOG")
public class PoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 操作者 id
	 */
	private PoAdmin operator;
	/**
	 * 发生时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String ip;
	public PoLog() {
	}

	public PoLog(Log log) {
		BeanUtils.copyProperties(log, this);
		time = DateUtil.parseDate(log.getTime());
		operator = new PoAdmin();
		operator.setId(log.getOperatorId());
		
	}

	public PoLog wrap(Log log) {
		BeanUtils.copyProperties(log, this);
		time = DateUtil.parseDate(log.getTime());
		operator = new PoAdmin();
		operator.setId(log.getOperatorId());
		return this;
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


	public PoAdmin getOperator() {
		return operator;
	}

	public void setOperator(PoAdmin operator) {
		this.operator = operator;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

}