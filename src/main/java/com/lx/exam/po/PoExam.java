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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.Exam;
@Entity
@Table(name="T_EXAM")
@NamedQueries({
	@NamedQuery(name="exam.selector",query="from PoExam")
})
public class PoExam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 竞赛名称
	 */
	private String name;
	/**
	 * 竞赛阶段
	 */
	@Value("0")
	private Integer step;
	/**
	 * 面向的组别 A,B,C
	 */
	@Column(name="face_group")
	@Value("A")
	private String faceGroup;
	/**
	 * 可进入此竞赛的限制
	 * {enroll:event_id | pass:exam_id}
	 */
	private String canIn;
	/**
	 * 所属赛事
	 */
	@ManyToOne
	@JoinColumn(name="event_id")
	private PoEvent poEvent;
	/**
	 * 试卷
	 */
	@ManyToOne
	@JoinColumn(name="paper_id")
	private PoPaper poPaper;
	/**
	 * 竞赛开始时间
	 */
	@Column(name="start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	/**
	 * 竞赛结束时间
	 */
	@Column(name="end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	/**
	 * 竞赛持续时长,单位分钟
	 */
	@Value("120")
	private Integer duration;
	
	public PoExam(){}
	public PoExam(Exam exam){
		BeanUtils.copyProperties(exam,this);
		poEvent = new PoEvent();
		poEvent.setId(exam.getEventId());
		poPaper=new PoPaper();
		poPaper.setId(exam.getPaperId());
		startTime=DateUtil.parseDate(exam.getStartTime());
		endTime=DateUtil.parseDate(exam.getEndTime());
		
	}
	public PoExam wrap(Exam exam){
		BeanUtils.copyProperties(exam,this);
		poEvent = new PoEvent();
		poEvent.setId(exam.getEventId());
		poPaper=new PoPaper();
		poPaper.setId(exam.getPaperId());
		startTime=DateUtil.parseDate(exam.getStartTime());
		endTime=DateUtil.parseDate(exam.getEndTime());
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
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public String getFaceGroup() {
		return faceGroup;
	}
	public void setFaceGroup(String faceGroup) {
		this.faceGroup = faceGroup;
	}
	public String getCanIn() {
		return canIn;
	}
	public void setCanIn(String canIn) {
		this.canIn = canIn;
	}
	public PoEvent getPoEvent() {
		return poEvent;
	}
	public void setPoEvent(PoEvent poEvent) {
		this.poEvent = poEvent;
	}
	public PoPaper getPoPaper() {
		return poPaper;
	}
	public void setPoPaper(PoPaper poPaper) {
		this.poPaper = poPaper;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
}
