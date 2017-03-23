package com.lx.exam.po;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.BeanUtils;

import com.lx.exam.vo.DataCode;
@Entity
@Table(name="T_DATACODE")
@NamedQueries({
	@NamedQuery(name="questionType.selector",query="from PoDataCode where poDataCode.id=14"),
	@NamedQuery(name="eventStep.selector",query="from PoDataCode where poDataCode.id=20"),
	@NamedQuery(name="questionLevel.selector",query="from PoDataCode where poDataCode.id=24")
})
public class PoDataCode implements Serializable{
	private static final long serialVersionUID = -5449174047350692088L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 上级代码
	 */
	@ManyToOne
	@JoinColumn(name = "PID",nullable=true)
	private PoDataCode poDataCode;
	/**
	 * 图标类
	 */
	private String icon;
	
	public PoDataCode(){}
	public PoDataCode(DataCode dataCode){
		BeanUtils.copyProperties(dataCode, this);
		if(dataCode.getPid()!=null){
			this.poDataCode=new PoDataCode();
			this.poDataCode.setId(dataCode.getPid());
		}
	}
	public PoDataCode wrap(DataCode dataCode){
		BeanUtils.copyProperties(dataCode, this);
		if(dataCode.getPid()!=null){
			this.poDataCode=new PoDataCode();
			this.poDataCode.setId(dataCode.getPid());
		}
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public PoDataCode getPoDataCode() {
		return poDataCode;
	}
	public void setPoDataCode(PoDataCode poDataCode) {
		this.poDataCode = poDataCode;
	}
	@Override
	public String toString() {
		return "PoDataCode [id=" + id + ", title=" + title + ", description=" + description + ", poDataCode="
				+ poDataCode + ", icon=" + icon + "]";
	}
	 
}
