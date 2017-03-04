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

import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Enroll;

@Entity
@Table(name="PO_ENROLL")
public class PoEnroll implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -28111239411158989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 该报名信息属于哪个试卷
	 */
	@ManyToOne
	@JoinColumn(name="paper_id")
	private PoPaper poPaper;
	/**
	 * 报名用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private PoUser poUser;
	/**
	 * 报名时间
	 */
	private Date enrollTime;
	
	public PoEnroll(){}
	public PoEnroll(Enroll enroll){
		ObjectUtil.o2o(this, enroll);
	}
	public PoEnroll wrap(Enroll enroll){
		ObjectUtil.o2o(this, enroll);
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PoPaper getPoPaper() {
		return poPaper;
	}
	public void setPoPaper(PoPaper poPaper) {
		this.poPaper = poPaper;
	}
	public PoUser getPoUser() {
		return poUser;
	}
	public void setPoUser(PoUser poUser) {
		this.poUser = poUser;
	}
	public Date getEnrollTime() {
		return enrollTime;
	}
	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}
	
}
