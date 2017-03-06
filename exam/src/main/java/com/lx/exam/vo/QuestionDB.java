package com.lx.exam.vo;

import com.lx.exam.po.PoQuestionDB;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class QuestionDB {
	private Long id;
	private String name;
	private String logo;
	private Integer status;
	private String description;
	private Long posterId;
	private String posterName;
	private String createDate;
	private Long modifyorId;
	private String modifyorName;
	private String modifyDate;
	public QuestionDB(){}
	public QuestionDB(PoQuestionDB poQuestionDB){
		ObjectUtil.o2o(this, poQuestionDB);
		this.posterId=poQuestionDB.getPoster().getId();
		this.posterName=poQuestionDB.getPoster().getRealname();
		this.createDate=DateUtil.format(poQuestionDB.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
		this.modifyorId=poQuestionDB.getModifyor().getId();
		this.modifyorName=poQuestionDB.getModifyor().getRealname();
		this.modifyDate=DateUtil.format(poQuestionDB.getModifyDate(),"yyyy-MM-dd HH:mm:ss");
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
	public Long getPosterId() {
		return posterId;
	}
	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
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
	
}
