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
import com.lx.exam.vo.Paper;

@Entity
@Table(name="T_PAPER")
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
	 * 总分
	 */
	@Column(name="total_score")
	@Value("0")
	private Double totalScore;
	/**
	 * 试题顺序  0 正常顺序  1随机顺序
	 */
	@Column(name="view_order")
	@Value("0")
	private Integer viewOrder;
	/**
	 * 是否随机出题
	 */
	@Column(name="is_random")
	@Value("false")
	private Boolean isRandom;
	/**
	 * 是否自动阅卷
	 */
	@Column(name="is_auto_check")
	@Value("false")
	private Boolean isAutoCheck;
	/**
	 * 试卷描述 
	 */
	private String description;
	/**
	 * 存放具体试题的xml数据
	 */
	private String data;
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
	@Column(name="create_date")
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
	@Column(name="modify_date")
	private Date modifyDate;
	/**
	 * 是否显示答案
	 */
	@Column(name="is_show_answer")
	private Integer isShowAnswer;
	
	public PoPaper(){}
	public PoPaper(Paper paper){
		BeanUtils.copyProperties(paper, this);
		poster=new PoAdmin();
		poster.setId(paper.getPosterId());
		modifyor = new PoAdmin();
		modifyor.setId(paper.getModifyorId());
		createDate=DateUtil.parseDate(paper.getCreateDate());
		modifyDate = DateUtil.parseDate(paper.getModifyDate());
	}
	public PoPaper wrap(Paper paper){
		BeanUtils.copyProperties(paper, this);
		poster=new PoAdmin();
		poster.setId(paper.getPosterId());
		modifyor=new PoAdmin();
		modifyor.setId(paper.getModifyorId());
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
	public Integer getViewOrder() {
		return viewOrder;
	}
	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}
	public Boolean getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(Boolean isRandom) {
		this.isRandom = isRandom;
	}
	public Boolean getIsAutoCheck() {
		return isAutoCheck;
	}
	public void setIsAutoCheck(Boolean isAutoCheck) {
		this.isAutoCheck = isAutoCheck;
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
	public Integer getIsShowAnswer() {
		return isShowAnswer;
	}
	public void setIsShowAnswer(Integer isShowAnswer) {
		this.isShowAnswer = isShowAnswer;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	
}
