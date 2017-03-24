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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.User;

@Entity
@Table(name="T_USER")
public class PoUser implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@Column(unique=true,length=18)
	private String idcard;
	/**
	 * 手机号
	 */
	@Column(unique=true,length=11)
	private String phone;
	/**
	 * 地址
	 */
	@ManyToOne
	@JoinColumn(name="address_id")
	private PoAddress address;
	
	/**
	 * 详细地址
	 */
	@Column(name="address_detail")
	private String addressDetail;
	/**
	 * 状态：0待审核，1正常，2冻结
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
	@Column(name="reg_date")
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

	/**
	 * 类型
	 */
	@ManyToOne
	@JoinColumn(name="type")
	private PoDataCode type;
	
	/**
	 * 最后一次登录时间
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name="last_login_time")
	private Date lastLoginTime;
	/**
	 * 年级
	 */
	
	private String level;
	/**
	 * 所属队伍
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="T_TEAM_USER",joinColumns=@JoinColumn(name="USER_ID"),inverseJoinColumns=@JoinColumn(name="TEAM_ID"))
	private Set<PoTeam> teams;
	
	public PoUser(){}
	
	public PoUser(User user){
		BeanUtils.copyProperties(user, this);
		this.regDate=DateUtil.parseDate(user.getRegDate());
		this.lastLoginTime=DateUtil.parseDate(user.getLastLoginTime());
		this.school=new PoSchool();
		this.school.setId(user.getSchoolId());
		this.address=new PoAddress();
		this.address.setId(user.getAddressId());
		this.type = new PoDataCode();
		this.type.setId(user.getTypeId());
		
		if(user.getTeamIds()!=null && user.getTeamIds().length>0){
			this.teams=new HashSet<PoTeam>();
			for(Long pid:user.getTeamIds()){
				PoTeam poTeam = new PoTeam();
				poTeam.setId(pid);
				this.teams.add(poTeam);
			}
		}
	}
	public PoUser wrap(User user){
		BeanUtils.copyProperties(user, this);
		this.regDate=DateUtil.parseDate(user.getRegDate());
		this.lastLoginTime=DateUtil.parseDate(user.getLastLoginTime());
		this.school=new PoSchool();
		this.school.setId(user.getSchoolId());
		this.address=new PoAddress();
		this.address.setId(user.getAddressId());
		this.type = new PoDataCode();
		this.type.setId(user.getTypeId());
		
		if(user.getTeamIds()!=null && user.getTeamIds().length>0){
			this.teams=new HashSet<PoTeam>();
			for(Long pid:user.getTeamIds()){
				PoTeam poTeam = new PoTeam();
				poTeam.setId(pid);
				this.teams.add(poTeam);
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

	public PoSchool getSchool() {
		return school;
	}

	public void setSchool(PoSchool school) {
		this.school = school;
	}

	public PoDataCode getType() {
		return type;
	}

	public void setType(PoDataCode type) {
		this.type = type;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Set<PoTeam> getTeams() {
		return teams;
	}

	public void setTeams(Set<PoTeam> teams) {
		this.teams = teams;
	}

	
}
