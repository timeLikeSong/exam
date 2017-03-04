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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.util.ObjectUtil;
import com.lx.exam.vo.User;

@Entity
@Table(name="PO_USER")
public class PoUser implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="S_PO_USER")
	@SequenceGenerator(name="S_PO_USER",sequenceName="S_PO_USER")
	private Long id;
	/**
	 * 用户账号：唯一
	 */
	@Column(unique=true)
	private String username;
	/**
	 * 用户密码
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
	 * 身份证
	 */
	@Column(unique=true)
	private String idcard;
	/**
	 * 手机号
	 */
	@Column(unique=true)
	private String phone;
	/**
	 * 地址
	 */
	@ManyToOne
	@JoinColumn(name="addr_id")
	private PoAddress address;
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
	private Date regDate;
	/**
	 * 邮箱：用于注册，找回密码 ，唯一
	 */
	@Column(unique=true)
	private String email;
	/**
	 * 学校
	 */
	@ManyToOne
	@JoinColumn(name="school_id")
	private PoSchool school;

	public PoUser(){}
	
	public PoUser(User user){
		ObjectUtil.o2o(this, user);
		this.regDate=DateUtil.parseDate(user.getRegDate(), "yyyy-MM-dd HH:mm:ss");
		this.school=new PoSchool();
		this.school.setId(user.getSchoolId());
		this.address=new PoAddress();
		this.address.setId(user.getAddrId());
	}
	public PoUser wrap(User user){
		ObjectUtil.o2o(this, user);
		this.regDate=DateUtil.parseDate(user.getRegDate(), "yyyy-MM-dd HH:mm:ss");
		this.school=new PoSchool();
		this.school.setId(user.getSchoolId());
		this.address=new PoAddress();
		this.address.setId(user.getAddrId());
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

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public PoAddress getAddress() {
		return address;
	}

	public void setAddress(PoAddress address) {
		this.address = address;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "PoUser [id=" + id + ", username=" + username + ", password=" + password + ", photo=" + photo
				+ ", realname=" + realname + ", idcard=" + idcard + ", phone=" + phone + ", address=" + address
				+ ", status=" + status + ", description=" + description + ", regDate=" + regDate + ", email=" + email
				+ ", school=" + school.getName() + "]";
	}

	public PoSchool getSchool() {
		return school;
	}

	public void setSchool(PoSchool school) {
		this.school = school;
	}
	
}
