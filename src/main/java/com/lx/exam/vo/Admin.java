package com.lx.exam.vo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoAdmin;
import com.lx.exam.po.PoRole;
import com.lx.exam.util.DateUtil;

public class Admin {
	private Long id;
	private String username;
	private String password;
	private String photo;
	private String realname;
	private String phone;
	private Integer status;
	private String description;
	private String createDate;
	private String email;
	private Long[] roleIds;
	private Set<Role> roles;
	public Admin(){}
	public Admin(PoAdmin poAdmin){
		BeanUtils.copyProperties(poAdmin, this);
		if(poAdmin.getPoRoles()!=null && poAdmin.getPoRoles().size()>0){
			roles = new HashSet<Role>();
			for(PoRole poRole:poAdmin.getPoRoles()){
				roles.add(new Role(poRole));
			}
		}
		this.createDate=DateUtil.format(poAdmin.getCreateDate());
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
