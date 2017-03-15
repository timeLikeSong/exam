package com.lx.exam.vo;

import com.lx.exam.po.PoNews;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class News {
	private Long id;
	private String title;
	private String content;
	private Long publisherId;
	private String publisherName;
	private String publishDate;
	private Long modifyorId;
	private String modifyorName;
	private String modifyDate;
	private Long typeId;
	private String typeName;
	public News(){}
	public News(PoNews poNews){
		ObjectUtil.o2o(this, poNews);
		publisherId=poNews.getPublisher().getId();
		publisherName=poNews.getPublisher().getUsername();
		modifyorId=poNews.getModifyor().getId();
		modifyorName=poNews.getModifyor().getUsername();
		publishDate=DateUtil.format(poNews.getPublishDate());
		modifyDate=DateUtil.format(poNews.getModifyDate());
		typeId=poNews.getType().getId();
		typeName=poNews.getType().getTitle();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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
