package com.lx.exam.po;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.vo.Function;
@Entity
@Table(name="T_FUNCTION")
public class PoFunction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2830317559217305090L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String funcName;
	private String url;
	private Integer status;
	private String icon;
	//0 查看、1管理
	private Integer type;
//	@ManyToMany(mappedBy = "poFunctions",fetch=FetchType.LAZY)
//	@Column(nullable=true,updatable=true)
	//让多对多双方都可维护关系
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="T_ROLE_FUNCTION",joinColumns=@JoinColumn(name="FUNCTION_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	private Set<PoRole> poRoles;
	
	@ManyToOne
	@JoinColumn(name = "PID",nullable=true)
	private PoFunction poFunction;
	
	public PoFunction(){}
	public PoFunction(Function function){
		BeanUtils.copyProperties(function,this);
		if(function.getPid()!=null){
			this.poFunction=new PoFunction();
			this.poFunction.id=function.getPid();
		}
	}
	public PoFunction wrap(Function function){
		BeanUtils.copyProperties(function,this);
		if(function.getPid()!=null){
			this.poFunction=new PoFunction();
			this.poFunction.id=function.getPid();
		}
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
//	public Set<PoRole> getPoRoles() {
//		return poRoles;
//	}
//	public void setPoRoles(Set<PoRole> poRoles) {
//		this.poRoles = poRoles;
//	}
	
	public PoFunction getPoFunction() {
		return poFunction;
	}
	public void setPoFunction(PoFunction poFunction) {
		this.poFunction = poFunction;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "PoFunction [id=" + id + ", funcName=" + funcName + ", url=" + url + ", status=" + status
				+ ", poFunction=" + poFunction + "]";
	}
	
	
}
