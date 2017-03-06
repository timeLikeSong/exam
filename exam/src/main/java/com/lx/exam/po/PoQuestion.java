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
import com.lx.exam.vo.Question;

@Entity
@Table(name="PO_QUESTION")
public class PoQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2880253941618258989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 所属题库
	 */
	@ManyToOne
	@JoinColumn(name="questiondb_id")
	private PoQuestionDB poQuestionDB;
	/**
	 * 类型
	 */
	private PoQuestionType type;
	/**
	 * 难度等级
	 */
	private Integer level;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 题干
	 */
	private String content;
	/**
	 * 答案
	 */
	private String answer;
	/**
	 * 问题解释
	 */
	private String resolve;
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
	/**
	 * xml格式的数据
	 */
	private String data;
	public PoQuestion(){}
	public PoQuestion(Question question){
		ObjectUtil.o2o(this,question);
		poster=new PoAdmin();
		poster.setId(question.getPosterId());
		modifyor=new PoAdmin();
		modifyor.setId(question.getModifyorId());
		type.setId(question.getTypeId());
	}
	public PoQuestion wrap(Question question){
		ObjectUtil.o2o(this,question);
		poster=new PoAdmin();
		poster.setId(question.getPosterId());
		modifyor=new PoAdmin();
		modifyor.setId(question.getModifyorId());
		type.setId(question.getTypeId());
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PoQuestionDB getPoQuestionDB() {
		return poQuestionDB;
	}
	public void setPoQuestionDB(PoQuestionDB poQuestionDB) {
		this.poQuestionDB = poQuestionDB;
	}
	public PoQuestionType getType() {
		return type;
	}
	public void setType(PoQuestionType type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getResolve() {
		return resolve;
	}
	public void setResolve(String resolve) {
		this.resolve = resolve;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
