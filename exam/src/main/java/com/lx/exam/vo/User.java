package com.lx.exam.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoTeam;
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
	private Long addressId;
	private String addressName;
	private String addressDetail;
	private Integer status;
	private String description;
	private String regDate;
	private String email;
	private Long schoolId;
	private String schoolName;
	private Long typeId;
	private String typeName;
	private String lastLoginTime;
	private String level;
	private Long[] teamIds;
	private Set<Team> teams;
	
	public User(){}
	
	public User(Long id,String username,String regDate){
		this.id=id;
		this.username=username;
		this.regDate=regDate;
	}
	public User(PoUser poUser){
		BeanUtils.copyProperties(poUser, this);
		this.regDate=DateUtil.format(poUser.getRegDate());
		this.lastLoginTime = DateUtil.format(poUser.getLastLoginTime());
		this.schoolName=poUser.getSchool().getName();
		this.schoolId=poUser.getSchool().getId();
		this.addressId = poUser.getAddress().getId();
		this.addressName = poUser.getAddress().getName();
		this.typeId = poUser.getType().getId();
		this.typeName = poUser.getType().getTitle();
		
		if(poUser.getTeams()!=null && poUser.getTeams().size()>0){
			teams = new HashSet<Team>();
			for(PoTeam poTeam:poUser.getTeams()){
				teams.add(new Team(poTeam));
			}
		}
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

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long[] getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(Long[] teamIds) {
		this.teamIds = teamIds;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	
}
