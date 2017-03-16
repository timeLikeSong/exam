package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.vo.Address;
@Entity
@Table(name="T_ADDRESS")
public class PoAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 地址的父id
	 */
	private Long pid;

	/**
	 * 地址名称
	 */
	private String name;
	/**
	 * 排序号
	 */
	@Column(name="view_order")
	private Integer viewOrder;
	public PoAddress(){}
	
	public PoAddress(Address address){
		BeanUtils.copyProperties(address,this);
	}
	public PoAddress wrap(Address address){
		BeanUtils.copyProperties(address,this);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
