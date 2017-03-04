package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Address;
@Entity
@Table(name="PO_ADDRESS")
public class PoAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="S_PO_ADDRESS",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="S_PO_ADDRESS",sequenceName="S_PO_ADDRESS")
	private Long id;
	/**
	 * 地址的父id
	 */
	private Long pid;

	/**
	 * 地址名称
	 */
	private String addrName;
	/**
	 * 排序号
	 */
	private Integer viewOrder;
	public PoAddress(){}
	
	public PoAddress(Address address){
		ObjectUtil.o2o(this, address);
	}
	public PoAddress wrap(Address address){
		ObjectUtil.o2o(this, address);
		return this;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}

}
