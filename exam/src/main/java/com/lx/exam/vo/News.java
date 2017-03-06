package com.lx.exam.vo;

import com.lx.exam.po.PoNews;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;

public class News {
	private Long id;
	private String title;
	private String content;
	private Long posterId;
	private String posterName;
	private String publishDate;
	private Long modifyorId;
	private String modifyorName;
	private String modifyDate;
	private Long newsTypeId;
	private String newsTypeName;
	public News(){}
	public News(PoNews poNews){
		ObjectUtil.o2o(this, poNews);
		posterId=poNews.getPoster().getId();
		posterName=poNews.getPoster().getRealname();
		modifyorId=poNews.getModifyor().getId();
		modifyorName=poNews.getModifyor().getRealname();
		publishDate=DateUtil.format(poNews.getPublishDate(),"yyyy-MM-dd HH:mm:ss");
		modifyDate=DateUtil.format(poNews.getModifyDate(),"yyyy-MM-dd HH:mm:ss");
		newsTypeId=poNews.getPoNewsType().getId();
		newsTypeName=poNews.getPoNewsType().getName();
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
	public Long getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(Long newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public String getNewsTypeName() {
		return newsTypeName;
	}
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
}
