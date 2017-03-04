package com.lx.exam.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.News;
@Entity
@Table(name="PO_NEWS")
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
	@ManyToOne
	@JoinColumn(name="poster_id")
	private PoAdmin poster;
	private Date publishDate;
	@ManyToOne
	@JoinColumn(name="modifyor_id")
	private PoAdmin modifyor;
	private Date modifyDate;
	/**
	 * 公告类型
	 */
	@ManyToOne
	@JoinColumn(name="news_type_id")
	private PoNewsType poNewsType;
	
	public PoNews(){}
	public PoNews(News news){
		ObjectUtil.o2o(this, news);
		poNewsType = new PoNewsType();
		poNewsType.setId(news.getNewsTypeId());
		poster=new PoAdmin();
		poster.setId(news.getPosterId());
		publishDate=DateUtil.parseDate(news.getPublishDate(),"yyyy-MM-dd HH:mm:ss");
		modifyor=new PoAdmin();
		modifyor.setId(news.getModifyorId());
		modifyDate=DateUtil.parseDate(news.getModifyDate(),"yyyy-MM-dd HH:mm:ss");
	}
	public PoNews wrap(News news){
		ObjectUtil.o2o(this, news);
		poNewsType = new PoNewsType();
		poNewsType.setId(news.getNewsTypeId());
		modifyor=new PoAdmin();
		modifyor.setId(news.getModifyorId());
		modifyDate=DateUtil.parseDate(news.getModifyDate(),"yyyy-MM-dd HH:mm:ss");
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
	public PoAdmin getPoster() {
		return poster;
	}
	public void setPoster(PoAdmin poster) {
		this.poster = poster;
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
	public PoNewsType getPoNewsType() {
		return poNewsType;
	}
	public void setPoNewsType(PoNewsType poNewsType) {
		this.poNewsType = poNewsType;
	}

}
