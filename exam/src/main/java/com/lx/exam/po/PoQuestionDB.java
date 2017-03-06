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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.QuestionDB;
@Entity
@Table(name="PO_QUESTION_DB")
public class PoQuestionDB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 题库名称
	 */
	private String name;
	/**
	 * 题库logo URL
	 */
	private String logo;
	/**
	 * 题库状态   0不可用   1正常
	 */
	private Integer status;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name="poster_id")
	private PoAdmin poster;
	/**
	 * 创建日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	/**
	 * 修改人
	 */
	@ManyToOne
	@JoinColumn(name="modifyor_id")
	private PoAdmin modifyor;
	/**
	 * 修改日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	public PoQuestionDB(){}
	public PoQuestionDB(QuestionDB questionDB){
		ObjectUtil.o2o(this, questionDB);
		this.poster=new PoAdmin();
		this.poster.setId(questionDB.getPosterId());
		this.modifyor=new PoAdmin();
		this.modifyor.setId(questionDB.getModifyorId());
	}                 
	public PoQuestionDB wrap(QuestionDB questionDB){
		ObjectUtil.o2o(this, questionDB);
		this.modifyor=new PoAdmin();
		this.modifyor.setId(questionDB.getModifyorId());
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
	public PoAdmin getPoster() {
		return poster;
	}
	public void setPoster(PoAdmin poster) {
		this.poster = poster;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	
}
