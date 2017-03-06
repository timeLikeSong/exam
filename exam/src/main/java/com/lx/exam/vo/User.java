package com.lx.exam.vo;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoUser;
import com.lx.exam.util.DateUtil;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String photo;
	private String realname;
	private String idcard;
	private String phone;
	private Long addrId;
	private String addrName;
	private Integer status;
	private String description;
	private String regDate;
	private String email;
	private Long schoolId;

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	private String schoolName;
	

	public User(){}
	
	public User(Long id,String username,String regDate){
		this.id=id;
		this.username=username;
		this.regDate=regDate;
	}
	public User(PoUser poUser){
		BeanUtils.copyProperties(poUser, this);
		this.regDate=DateUtil.format(poUser.getRegDate(), "yyyy-MM-dd");
		this.schoolName=poUser.getSchool().getName();
		this.schoolId=poUser.getSchool().getId();
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


	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	
}
