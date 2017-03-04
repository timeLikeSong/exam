package com.lx.exam.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.Admin;
@Entity
@Table(name="PO_ADMIN")
public class PoAdmin implements Serializable{
	private static final long serialVersionUID = -395929811594015977L;
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="S_PO_ADMIN")
	@SequenceGenerator(name="S_PO_ADMIN",sequenceName="S_PO_ADMIN")
	private Long id;
	/**
	 * 管理员账号：唯一
	 */
	@Column(unique=true)
	private String username;
	/**
	 * 管理员密码
	 */
	private String password;
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 手机号
	 */
	@Column(unique=true)
	private String phone;
	/**
	 * 状态：0未激活，1待审核(开启审核配置时才有)，2冻结，3正常
	 */
	private Integer status;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 注册日期
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createDate;
	/**
	 * 邮箱：用于注册，找回密码 ，唯一
	 */
	@Column(unique=true)
	private String email;
	/**
	 * 角色
	 */
	@ManyToMany(fetch=FetchType.EAGER)//多对多
	@JoinTable(name="T_ADMIN_ROLE",joinColumns=@JoinColumn(name="ADMIN_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_ID"))//生成中间表
	private Set<PoRole> poRoles;
	public PoAdmin(){}
	public PoAdmin(Admin admin){
		BeanUtils.copyProperties(admin, this);
		if(admin.getRoleIds()!=null && admin.getRoleIds().length>0){
			this.poRoles=new HashSet<PoRole>();
			for(Long pid:admin.getRoleIds()){
				PoRole poRole = new PoRole();
				poRole.setId(pid);
				this.poRoles.add(poRole);
			}
		}
		this.createDate=new Date();
	}
	public PoAdmin wrap(Admin admin){
		BeanUtils.copyProperties(admin, this);
		if(admin.getRoleIds()!=null && admin.getRoleIds().length>0){
			this.poRoles=new HashSet<PoRole>();
			for(Long pid:admin.getRoleIds()){
				PoRole poRole = new PoRole();
				poRole.setId(pid);
				this.poRoles.add(poRole);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Set<PoRole> getPoRoles() {
		return poRoles;
	}
	public void setPoRoles(Set<PoRole> poRoles) {
		this.poRoles = poRoles;
	}
	@Override
	public String toString() {
		return "PoAdmin [id=" + id + ", username=" + username + ", password=" + password + ", photo=" + photo
				+ ", realname=" + realname + ", phone=" + phone + ", status=" + status + ", description=" + description
				+ ", createDate=" + createDate + ", email=" + email + ", poRoles=" + poRoles + "]";
	}

	
}

