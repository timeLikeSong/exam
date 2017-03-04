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

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Exam;
@Entity
@Table(name="PO_EXAM")
public class PoExam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="paper_id")
	private PoPaper poPaper;
	@ManyToOne
	@JoinColumn(name="user_id")
	private PoUser poUser;
	private Date startTime;
	private Date endTime;
	private String ip;
	private Integer score;
	private Integer status;
	private String data;
	
	public PoExam(){}
	public PoExam(Exam exam){
		ObjectUtil.o2o(this, exam);
		poPaper=new PoPaper();
		poPaper.setId(exam.getPaperId());
		poUser=new PoUser();
		poUser.setId(exam.getUserId());
		startTime=DateUtil.parseDate(exam.getStartTime());
		endTime=DateUtil.parseDate(exam.getEndTime());
		
	}
	public PoExam wrap(Exam exam){
		ObjectUtil.o2o(this, exam);
//		poPaper=new PoPaper();
//		poPaper.setId(exam.getPaperId());
//		poUser=new PoUser();
//		poUser.setId(exam.getUserId());
//		startTime=DateUtil.parseDate(exam.getStartTime());
		endTime=DateUtil.parseDate(exam.getEndTime());
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
