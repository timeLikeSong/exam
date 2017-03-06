package com.lx.exam.vo;

import java.util.Date;

import com.lx.exam.po.PoAdmin;
import com.lx.exam.po.PoQuestion;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class Question {
	private Long id;
	private String name;
	private String logo;
	private Integer status;
	private String description;
	private String posterName;
	private Long posterId;
	private String createDate;
	private Long modifyorId;
	private String modifyorName;
	private String modifyDate;
	private Long typeId;
	private String typeName;
	public Question(){}
	public Question(PoQuestion poQuestion){
		ObjectUtil.o2o(this, poQuestion);
		posterName=poQuestion.getPoster().getRealname();
		posterId = poQuestion.getPoster().getId();
		modifyorId = poQuestion.getModifyor().getId();
		modifyorName= poQuestion.getModifyor().getRealname();
		createDate=DateUtil.format(poQuestion.getCreateDate());
		modifyDate=DateUtil.format(poQuestion.getModifyDate());
		typeId=poQuestion.getType().getId();
		typeName=poQuestion.getType().getName();
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public Long getPosterId() {
		return posterId;
	}
	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Long getModifyorId() {
		return modifyorId;
	}
	public void setModifyorId(Long modifyorId) {
		this.modifyorId = modifyorId;
	}
	public String getModifyorName() {
		return modifyorName;
	}
	public void setModifyorName(String modifyorName) {
		this.modifyorName = modifyorName;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
