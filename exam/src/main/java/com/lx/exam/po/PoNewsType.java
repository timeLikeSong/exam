package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.NewsType;
@Entity
@Table(name="PO_NEWS_TYPE")
public class PoNewsType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 通知类型名称
	 */
	private String name;
	
	public PoNewsType(){}
	public PoNewsType(NewsType newsType){
		ObjectUtil.o2o(this, newsType);
	}
	public PoNewsType wrap(NewsType newsType){
		ObjectUtil.o2o(this, newsType);
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
