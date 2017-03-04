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

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Paper;
import com.lx.exam.vo.Question;

@Entity
@Table(name="PO_PAPER")
public class PoPaper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2812253941618258989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 试卷名称
	 */
	private String name;
	/**
	 * 试卷状态   0未开考 1正在考试 2考试结束 
	 */
	private Integer status;
	/**
	 * 开考时间
	 */
	private Date startTime;
	/**
	 * 考试结束时间
	 */
	private Date endTime;
	/**
	 * 报名开始时间
	 */
	private Date enrollStart;
	/**
	 * 报名结束时间
	 */
	private Date enrollEnd;
	/**
	 * 考试时长
	 */
	private Integer duration;
	/**
	 * 总分
	 */
	private Integer totalScore;
	/**
	 * 试题顺序  0 正常顺序  1随机顺序
	 */
	private Integer questionOrder;
	/**
	 * 是否随机出题
	 */
	private Integer isRandom;
	/**
	 * 试卷描述 
	 */
	private String description;
	/**
	 * 存放具体试题的xml数据
	 */
	private String data;
	/**
	 * 是否自动判卷 ，0 不是  1 是
	 */
	private Integer isAutoCheck;
	/**
	 * 试卷创建人
	 */
	@ManyToOne
	@JoinColumn(name="poster_id")
	private PoAdmin poster;
	/**
	 * 试卷创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	/**
	 * 试卷修改人
	 */
	@ManyToOne
	@JoinColumn(name="modifyor_id")
	private PoAdmin modifyor;
	/**
	 * 试卷修改时间
	 */
	private Date modifyDate;
	/**
	 * 分数公布时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date showTime;
	/**
	 * 是否显示答案
	 */
	private Integer isShowAnswer;
	
	public PoPaper(){}
	public PoPaper(Paper paper){
		ObjectUtil.o2o(this, paper);
		poster=new PoAdmin();
		poster.setId(paper.getPosterId());
		modifyor.setId(paper.getModifyorId());
		startTime=DateUtil.parseDate(paper.getStartTime(),"yyyy-MM-dd HH:mm:ss");
		endTime=DateUtil.parseDate(paper.getEndTime(),"yyyy-MM-dd HH:mm:ss");
		enrollStart=DateUtil.parseDate(paper.getEnrollStart(),"yyyy-MM-dd HH:mm:ss");
		enrollEnd=DateUtil.parseDate(paper.getEnrollEnd(),"yyyy-MM-dd HH:mm:ss");
		showTime=DateUtil.parseDate(paper.getShowTime(),"yyyy-MM-dd HH:mm:ss");

	}
	public PoPaper wrap(Paper paper){
		ObjectUtil.o2o(this, paper);
		poster=new PoAdmin();
		poster.setId(paper.getPosterId());
		modifyor=new PoAdmin();
		modifyor.setId(paper.getModifyorId());
		startTime=DateUtil.parseDate(paper.getStartTime(),"yyyy-MM-dd HH:mm:ss");
		endTime=DateUtil.parseDate(paper.getEndTime(),"yyyy-MM-dd HH:mm:ss");
		enrollStart=DateUtil.parseDate(paper.getEnrollStart(),"yyyy-MM-dd HH:mm:ss");
		enrollEnd=DateUtil.parseDate(paper.getEnrollEnd(),"yyyy-MM-dd HH:mm:ss");
		showTime=DateUtil.parseDate(paper.getShowTime(),"yyyy-MM-dd HH:mm:ss");
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Date getEnrollStart() {
		return enrollStart;
	}
	public void setEnrollStart(Date enrollStart) {
		this.enrollStart = enrollStart;
	}
	public Date getEnrollEnd() {
		return enrollEnd;
	}
	public void setEnrollEnd(Date enrollEnd) {
		this.enrollEnd = enrollEnd;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getQuestionOrder() {
		return questionOrder;
	}
	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
	public Integer getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(Integer isRandom) {
		this.isRandom = isRandom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getIsAutoCheck() {
		return isAutoCheck;
	}
	public void setIsAutoCheck(Integer isAutoCheck) {
		this.isAutoCheck = isAutoCheck;
	}
	public PoAdmin getPoster() {
		return poster;
	}
	public void setPoster(PoAdmin poster) {
		this.poster = poster;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public PoAdmin getModifyor() {
		return modifyor;
	}
	public void setModifyor(PoAdmin modifyor) {
		this.modifyor = modifyor;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public Integer getIsShowAnswer() {
		return isShowAnswer;
	}
	public void setIsShowAnswer(Integer isShowAnswer) {
		this.isShowAnswer = isShowAnswer;
	}
	
	
}
