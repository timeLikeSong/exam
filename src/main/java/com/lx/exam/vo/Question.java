package com.lx.exam.vo;


import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoQuestion;
import com.lx.exam.util.DateUtil;

public class Question {
	private Long id;
	private Long questionDBId;
	private String questionDBName;
	private Long typeId;
	private String typeName;
	private Long levelId;
	private String levelName;
	private Integer status;
	private String content;
	private String answer;
	private String resolve;
	private String posterName;
	private Long posterId;
	private String createDate;
	private Long modifyorId;
	private String modifyorName;
	private String modifyDate;
	private String data;
	private Float score;
	public Question(){}
	public Question(PoQuestion poQuestion){
		BeanUtils.copyProperties(poQuestion, this);
		questionDBId = poQuestion.getPoQuestionDB().getId();
		questionDBName=poQuestion.getPoQuestionDB().getName();
		typeId = poQuestion.getType().getId();
		typeName = poQuestion.getType().getTitle();
		levelId=poQuestion.getLevel().getId();
		levelName= poQuestion.getLevel().getTitle();
		posterName=poQuestion.getPoster().getUsername();
		posterId = poQuestion.getPoster().getId();
		modifyorId = poQuestion.getModifyor().getId();
		modifyorName= poQuestion.getModifyor().getUsername();
		createDate=DateUtil.format(poQuestion.getCreateDate());
		modifyDate=DateUtil.format(poQuestion.getModifyDate());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionDBId() {
		return questionDBId;
	}
	public void setQuestionDBId(Long questionDBId) {
		this.questionDBId = questionDBId;
	}
	public String getQuestionDBName() {
		return questionDBName;
	}
	public void setQuestionDBName(String questionDBName) {
		this.questionDBName = questionDBName;
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
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
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
