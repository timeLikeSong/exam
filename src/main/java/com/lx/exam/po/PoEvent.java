package com.lx.exam.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.Event;
@Entity
@Table(name="T_EVENT")
@NamedQueries({
	@NamedQuery(name="event.selector",query="from PoEvent where status=1")
})
public class PoEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962576426307717206L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 赛事名称
	 */
	private String name;
	
	/**
	 * 赛事描述
	 */
	private String description;
	
	/**
	 * 状态
	 * 控制竞赛开启与结束的标志，
	 *	0：报名开始，1：报名结束，2：赛事进行中，3：赛事结束
	 */
	@Value("0")
	private Integer status=0;
	
	/**
	 * 组队规则
	 */
	@Column(name="group_rule")
	@Value("{'A':1}")
	private String groupRule="{'A':1}";
	
	/**
	 * 赛事阶段
	 */
	@Value("{0:'初赛'}")
	private String steps="{0:'初赛'}";
	
	/**
	 * 当前赛事阶段
	 */
	@Column(name="current_step")
	@Value("0")
	private Integer currentStep=0;
	
	/**
	 * 报名开始时间
	 */
	@Column(name="enroll_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enrollStartTime=new Date();
	
	/**
	 * 报名结束时间
	 */
	@Column(name="enroll_end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enrollEndTime;
	
	/**
	 * 创建日期
	 */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	public PoEvent(){}
	public PoEvent(Event event){
		BeanUtils.copyProperties(event,this);
		this.enrollStartTime=DateUtil.parseDate(event.getEnrollStartTime());
		this.enrollEndTime=DateUtil.parseDate(event.getEnrollEndTime());
		this.createTime=DateUtil.parseDate(event.getCreateTime());
	}
	public PoEvent wrap(Event event){
		BeanUtils.copyProperties(event,this);
		this.enrollStartTime=DateUtil.parseDate(event.getEnrollStartTime());
		this.enrollEndTime=DateUtil.parseDate(event.getEnrollEndTime());
		this.createTime=DateUtil.parseDate(event.getCreateTime());
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGroupRule() {
		return groupRule;
	}
	public void setGroupRule(String groupRule) {
		this.groupRule = groupRule;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public Integer getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}
	public Date getEnrollStartTime() {
		return enrollStartTime;
	}
	public void setEnrollStartTime(Date enrollStartTime) {
		this.enrollStartTime = enrollStartTime;
	}
	public Date getEnrollEndTime() {
		return enrollEndTime;
	}
	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
