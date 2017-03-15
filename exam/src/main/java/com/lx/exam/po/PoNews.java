package com.lx.exam.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.News;
@Entity
@Table(name="T_NEWS")
public class PoNews implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 公告标题
	 */
	private String title;
	/**
	 * 公告内容
	 */
	private String content;
	/**
	 * 发布者
	 */
	@ManyToOne
	@JoinColumn(name="publisher_id")
	private PoAdmin publisher;
	/**
	 * 发布日期
	 */
	@Column(name="publish_date")
	private Date publishDate;
	/**
	 * 修改者
	 */
	@ManyToOne
	@JoinColumn(name="modifyor_id")
	private PoAdmin modifyor;
	/**
	 * 修改日期
	 */
	@Column(name="modify_date")
	private Date modifyDate;
	/**
	 * 公告类型
	 */
	@ManyToOne
	@JoinColumn(name="type_id")
	private PoDataCode type;
	
	public PoNews(){}
	public PoNews(News news){
		BeanUtils.copyProperties(news, this);
		type = new PoDataCode();
		type.setId(news.getTypeId());
		publisher=new PoAdmin();
		publisher.setId(news.getPublisherId());
		publishDate=DateUtil.parseDate(news.getPublishDate());
		modifyor=new PoAdmin();
		modifyor.setId(news.getModifyorId());
		modifyDate=DateUtil.parseDate(news.getModifyDate());
	}
	public PoNews wrap(News news){
		ObjectUtil.o2o(this, news);
		type = new PoDataCode();
		type.setId(news.getTypeId());
		modifyor=new PoAdmin();
		modifyor.setId(news.getModifyorId());
		modifyDate=DateUtil.parseDate(news.getModifyDate());
		return this;
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
	public PoAdmin getPublisher() {
		return publisher;
	}
	public void setPublisher(PoAdmin publisher) {
		this.publisher = publisher;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public PoAdmin getModifyor() {
		return modifyor;
	}
	public void setModifyor(PoAdmin modifyor) {
		this.modifyor = modifyor;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public PoDataCode getType() {
		return type;
	}
	public void setType(PoDataCode type) {
		this.type = type;
	}

}
