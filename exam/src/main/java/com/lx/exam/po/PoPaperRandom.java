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
import com.lx.exam.vo.PaperRandom;
import com.lx.exam.vo.Question;

@Entity
@Table(name="PO_PAPER_RANDOM")
public class PoPaperRandom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2811123941618258989L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 该随机试卷 属于哪个试卷
	 */
	@ManyToOne
	@JoinColumn(name="paper_id")
	private PoPaper poPaper;
	/**
	 * 所属用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private PoUser poUser;
	/**
	 * xml保存随机试卷信息
	 */
	private String data;
	public PoPaperRandom(){}
	public PoPaperRandom(PaperRandom paperRandom){
		ObjectUtil.o2o(this, paperRandom);
		poPaper = new PoPaper();
		poPaper.setId(paperRandom.getPaperId());
		poUser.setId(paperRandom.getUserId());
	}
	public PoPaperRandom wrap(PaperRandom paperRandom){
		ObjectUtil.o2o(this, paperRandom);
		poPaper = new PoPaper();
		poPaper.setId(paperRandom.getPaperId());
		poUser.setId(paperRandom.getUserId());
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
