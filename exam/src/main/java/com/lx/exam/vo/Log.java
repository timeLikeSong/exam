package com.lx.exam.vo;

import java.io.Serializable;

import com.lx.exam.po.PoLog;
import com.lx.exam.util.DateUtil;


public class Log implements Serializable {
	private static final long serialVersionUID = -521356121612825099L;
	private String description;
	private Long id;
	private String ipaddress;
	private String logtime;
	private String optype;
	private String paras;
	private String tname;
	private String username;

	public Log() {
	}

	public Log(PoLog poLog) {
		this.id = poLog.getId();
		this.username = poLog.getUsername();
		this.tname = poLog.getTname();
		this.optype = poLog.getOptype();
		this.ipaddress = poLog.getIpaddress();
		this.logtime = DateUtil.format(poLog.getLogtime(),
				"yyyy-MM-dd HH:mm:ss");
		this.description = poLog.getDescription();
		this.paras = poLog.getParas();
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public String getLogtime() {
		return logtime;
	}

	public String getOptype() {
		return optype;
	}

	public String getParas() {
		return paras;
	}

	public String getTname() {
		return tname;
	}

	public String getUsername() {
		return username;
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

	public void setLogtime(String logtime) {
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
}
