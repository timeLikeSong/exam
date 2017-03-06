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
import com.lx.exam.vo.QuestionType;

@Entity
@Table(name="PO_QUESTION_TYPE")
public class PoQuestionType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -28122512348258989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 试题类型名称
	 */
	private String name;
	public PoQuestionType(){}
	public PoQuestionType(QuestionType questionType){
		ObjectUtil.o2o(this, questionType);
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
