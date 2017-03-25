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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Question;

@Entity
@Table(name="T_QUESTION")
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
	@ManyToOne
	@JoinColumn(name="type")
	private PoDataCode type;
	/**
	 * 难度等级
	 */
	@ManyToOne
	@JoinColumn(name="level")
	private PoDataCode level;
	/**
	 * 试题状态，0不可用，1可用
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
	@Column(name="create_date")
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
	@Column(name="modify_date")
	private Date modifyDate;
	/**
	 * json格式的数据
	 */
	private String data;
	/**
	 * 试题分数
	 */
	private Float score;
	public PoQuestion(){}
	public PoQuestion(Question question){
		BeanUtils.copyProperties(question, this);
		createDate = DateUtil.parseDate(question.getCreateDate());
		poster=new PoAdmin();
		poster.setId(question.getPosterId());
		modifyDate = DateUtil.parseDate(question.getModifyDate());
		modifyor=new PoAdmin();
		modifyor.setId(question.getModifyorId());
		poQuestionDB= new PoQuestionDB();
		poQuestionDB.setId(question.getQuestionDBId());
		type = new PoDataCode();
		type.setId(question.getTypeId());
		level = new PoDataCode();
		level.setId(question.getLevelId());
		
	}
	public PoQuestion wrap(Question question){
		ObjectUtil.o2o(this,question);
		modifyDate = DateUtil.parseDate(question.getModifyDate());
		modifyor=new PoAdmin();
		modifyor.setId(question.getModifyorId());
		poQuestionDB= new PoQuestionDB();
		poQuestionDB.setId(question.getQuestionDBId());
		type = new PoDataCode();
		type.setId(question.getTypeId());
		level = new PoDataCode();
		level.setId(question.getLevelId());
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
	public PoDataCode getType() {
		return type;
	}
	public void setType(PoDataCode type) {
		this.type = type;
	}
	public PoDataCode getLevel() {
		return level;
	}
	public void setLevel(PoDataCode level) {
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
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	
}
