package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import com.lx.exam.vo.Result;

@Entity
@Table(name="T_RESULT")
public class PoResult implements Serializable{

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
	 * 是否通过
	 */
	@Column(name="is_pass")
	private Boolean isPass;
	/**
	 * 个人分数
	 */
	@Column(name="person_score")
	private Double personScore;
	/**
	 * 队伍平均分
	 */
	@Column(name="team_average_score")
	private Double teamAverageScore;
	
	public PoResult(){}
	public PoResult(Result result){
		BeanUtils.copyProperties(result,this);
		poExam=new PoExam();
		poExam.setId(result.getExamId());
		poUser=new PoUser();
		poUser.setId(result.getUserId());
	}
	public PoResult wrap(Result result){
		BeanUtils.copyProperties(result,this);
		poExam=new PoExam();
		poExam.setId(result.getExamId());
		poUser=new PoUser();
		poUser.setId(result.getUserId());
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
	public Boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	public Double getPersonScore() {
		return personScore;
	}
	public void setPersonScore(Double personScore) {
		this.personScore = personScore;
	}
	public Double getTeamAverageScore() {
		return teamAverageScore;
	}
	public void setTeamAverageScore(Double teamAverageScore) {
		this.teamAverageScore = teamAverageScore;
	}
}
