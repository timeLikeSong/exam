package com.lx.exam.vo;

import com.lx.exam.po.PoQuestionType;
import com.lx.exam.util.ObjectUtil;

public class QuestionType {
	private Long id;
	private String name;
	public QuestionType(){}
	public QuestionType(PoQuestionType poQuestionType){
		ObjectUtil.o2o(this, poQuestionType);
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
	
}
