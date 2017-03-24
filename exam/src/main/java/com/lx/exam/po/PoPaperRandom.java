package com.lx.exam.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.vo.PaperRandom;

@Entity
@Table(name="T_PAPER_RANDOM")
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
	 * xml保存随机试卷信息
	 */
	private String data;
	public PoPaperRandom(){}
	public PoPaperRandom(PaperRandom paperRandom){
		BeanUtils.copyProperties(paperRandom, this);
		poPaper = new PoPaper();
		poPaper.setId(paperRandom.getPaperId());
	}
	public PoPaperRandom wrap(PaperRandom paperRandom){
		BeanUtils.copyProperties(paperRandom, this);
		poPaper = new PoPaper();
		poPaper.setId(paperRandom.getPaperId());
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
