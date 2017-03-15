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
import org.springframework.beans.factory.annotation.Value;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.Enroll;

@Entity
@Table(name="T_ENROLL")
public class PoEnroll implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -28111239411158989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 报名组别 A,B,C
	 */
	@Value("A")
	private String type;
	/**
	 * 报名用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private PoUser poUser;
	/**
	 * 报名时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="enroll_time")
	private Date enrollTime;
	/**
	 * 报名的赛事
	 */
	@ManyToOne
	@JoinColumn(name="event_id")
	private PoEvent poEvent;
	
	public PoEnroll(){}
	public PoEnroll(Enroll enroll){
		BeanUtils.copyProperties(enroll,this);
		poUser=new PoUser();
		poUser.setId(enroll.getUserId());
		poEvent = new PoEvent();
		poEvent.setId(enroll.getEventId());
		enrollTime = DateUtil.parseDate(enroll.getEnrollTime());
	}
	public PoEnroll wrap(Enroll enroll){
		BeanUtils.copyProperties(enroll,this);
		poUser=new PoUser();
		poUser.setId(enroll.getUserId());
		poEvent = new PoEvent();
		poEvent.setId(enroll.getEventId());
		enrollTime = DateUtil.parseDate(enroll.getEnrollTime());
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public PoEvent getPoEvent() {
		return poEvent;
	}
	public void setPoEvent(PoEvent poEvent) {
		this.poEvent = poEvent;
	}
	
}
