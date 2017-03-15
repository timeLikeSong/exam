package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.vo.School;
@Entity
@Table(name="T_SCHOOL")
public class PoSchool implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 地区
	 */
	private String place;
	/**
	 * 类型 ：综合  师范  工科  财经 医药 语言 政法  艺术   体育  农业  民族
	 */
	private String type;
	/**
	 * 属性 ：高职专科 本科 独立学院  其它 ---
	 */
	private String properties;
	public PoSchool(){}
	
	public PoSchool(School school){
		BeanUtils.copyProperties(school,this);
	}
	public PoSchool wrap(School school){
		BeanUtils.copyProperties(school,this);
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
}
