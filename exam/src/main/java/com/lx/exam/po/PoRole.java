package com.lx.exam.po;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Function;
import com.lx.exam.vo.Role;
@Entity
@Table(name="PO_ROLE")
public class PoRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496464476710519717L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//,generator="S_PO_ROLE"
	private Long id;
	@Column(unique=true)
	private String roleName;
	private Integer status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="T_ROLE_FUNCTION",joinColumns=@JoinColumn(name="ROLE_ID"),inverseJoinColumns=@JoinColumn(name="FUNCTION_ID"))
	private Set<PoFunction> poFunctions;
//	@ManyToMany(fetch = FetchType.LAZY,mappedBy="poRoles")
	@ManyToMany(fetch=FetchType.LAZY)//多对多
	@JoinTable(name="T_ADMIN_ROLE",joinColumns=@JoinColumn(name="ROLE_ID"),inverseJoinColumns=@JoinColumn(name="ADMIN_ID"))//生成中间表
	private Set<PoAdmin> poAdmins;
	
	public PoRole(){}
	public PoRole(Role role){
		BeanUtils.copyProperties(role, this);
		if(role.getFunctionIds()!=null && role.getFunctionIds().length>0){
			this.poFunctions=new HashSet<PoFunction>();
			for(Long pid:role.getFunctionIds()){
				PoFunction poFunction = new PoFunction();
				poFunction.setId(pid);
				this.poFunctions.add(poFunction);
			}
		}
		this.createDate=new Date();
	}
	public PoRole wrap(Role role){
		BeanUtils.copyProperties(role, this);
		if(role.getFunctionIds()!=null && role.getFunctionIds().length>0){
			this.poFunctions=new HashSet<PoFunction>();
			for(Long pid:role.getFunctionIds()){
				PoFunction poFunction = new PoFunction();
				poFunction.setId(pid);
				this.poFunctions.add(poFunction);
			}
		}
		return this;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<PoFunction> getPoFunctions() {
		return poFunctions;
	}
	public void setPoFunctions(Set<PoFunction> poFunctions) {
		this.poFunctions = poFunctions;
	}
//	public Set<PoAdmin> getPoAdmins() {
//		return poAdmins;
//	}
//	public void setPoAdmins(Set<PoAdmin> poAdmins) {
//		this.poAdmins = poAdmins;
//	}
	@Override
	public String toString() {
		return "PoRole [id=" + id + ", roleName=" + roleName + ", status=" + status + ", createDate=" + createDate
				+ ", poFunctions=" + poFunctions;
	}
	
	
}
