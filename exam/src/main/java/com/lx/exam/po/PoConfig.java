package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Config;
@Entity
@Table(name="PO_CONFIG")
public class PoConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="S_PO_CONFIG",strategy=GenerationType.AUTO)
	@SequenceGenerator(sequenceName="S_PO_CONFIG",name="S_PO_CONFIG")
	private Long id;
	@Column(unique=true)
	private String configKey;
	private String configName;
	private String configValue;
	
	public PoConfig(){}
	
	public PoConfig(Config config){
		ObjectUtil.o2o(this, config);
	}
	public PoConfig wrap(Config config){
		ObjectUtil.o2o(this, config);
		return this;
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
