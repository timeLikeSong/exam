package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoConfig;

public class Config {
	private Long id;
	private String configKey;
	private String configName;
	private String configValue;
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

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
}
