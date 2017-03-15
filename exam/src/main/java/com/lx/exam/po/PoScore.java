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
import com.lx.exam.vo.Score;

@Entity
@Table(name="T_SCORE")
public class PoScore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -28111239411158989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 所属竞赛
	 */
	@ManyToOne
	@JoinColumn(name="exam_id")
	private PoExam poExam;
	/**
	 * 用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private PoUser poUser;
	/**
	 * 试卷
	 */
	@ManyToOne
	@JoinColumn(name="paper_id")
	private PoPaper poPaper;
	/**
	 * 试题详情  json形式
	 */
	private String data;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 开始考试的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;
	/**
	 * 提交试卷的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="submit_time")
	private Date submitTime;
	/**
	 * 状态 0:已开始 1已提交
	 */
	private Integer status;
	
	public PoScore(){}
	public PoScore(Score score){
		BeanUtils.copyProperties(score,this);
		poExam=new PoExam();
		poExam.setId(score.getExamId());
		poUser=new PoUser();
		poUser.setId(score.getUserId());
		poPaper= new PoPaper();
		poPaper.setId(score.getPaperId());
		startTime=DateUtil.parseDate(score.getStartTime());
		submitTime=DateUtil.parseDate(score.getSubmitTime());
		 
	}
	public PoScore wrap(Score score){
		BeanUtils.copyProperties(score,this);
//		poExam=new PoExam();
//		poExam.setId(score.getExamId());
//		poUser=new PoUser();
//		poUser.setId(score.getUserId());
//		poPaper= new PoPaper();
//		poPaper.setId(score.getPaperId());
//		startTime=DateUtil.parseDate(score.getStartTime());
		submitTime=DateUtil.parseDate(score.getSubmitTime());
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PoExam getPoExam() {
		return poExam;
	}
	public void setPoExam(PoExam poExam) {
		this.poExam = poExam;
	}
	public PoUser getPoUser() {
		return poUser;
	}
	public void setPoUser(PoUser poUser) {
		this.poUser = poUser;
	}
	public PoPaper getPoPaper() {
		return poPaper;
	}
	public void setPoPaper(PoPaper poPaper) {
		this.poPaper = poPaper;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
