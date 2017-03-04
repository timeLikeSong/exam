package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.*;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Log;

import java.util.Date;
@Entity
@Table(name = "PO_LOG")
public class PoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_PO_LOG")
	@SequenceGenerator(name = "S_PO_LOG", sequenceName = "S_PO_LOG")
	private Long id;

	private String ipaddress;

	@Temporal(TemporalType.TIMESTAMP)
	private Date logtime;

	private String optype;

	private String paras;

	private String tname;

	private String username;

	public PoLog() {
	}

	public PoLog(Log log) {
		ObjectUtil.o2o(this, log);
		this.logtime = DateUtil.parseDate(log.getLogtime(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getDescription() {
		return this.description;
	}

	public Long getId() {
		return this.id;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public Date getLogtime() {
		return this.logtime;
	}

	public String getOptype() {
		return this.optype;
	}


	public String getParas() {
		return this.paras;
	}

	public String getTname() {
		return this.tname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public void setParas(String paras) {
		this.paras = paras;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public PoLog wrap(Log log) {
		ObjectUtil.o2o(this, log);
		this.logtime = DateUtil.parseDate(log.getLogtime(), "yyyy-MM-dd HH:mm:ss");
		return this;
	}

}