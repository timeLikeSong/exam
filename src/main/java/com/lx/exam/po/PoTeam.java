package com.lx.exam.po;

import java.io.Serializable;
import java.util.HashSet;
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
import org.springframework.beans.factory.annotation.Value;

import com.lx.exam.vo.Team;
@Entity
@Table(name="T_TEAM")
public class PoTeam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 队伍名称
	 */
	private String name;
	/**
	 * 队伍描述
	 */
	private String description;
	/**
	 * 队伍状态0正在组建，1组建成功，2已报名
	 */
	@Value("0")
	private Integer status;
	/**
	 * 所属赛事
	 */
	@ManyToOne
	@JoinColumn(name="event_id")
	private PoEvent poEvent;
	/**
	 * 队长
	 */
	@ManyToOne
	@JoinColumn(name="leader_id")
	private PoUser leader;
	/**
	 * 指导老师
	 */
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private PoUser teacher;
	/**
	 * 组别 A，B，C
	 */
	@Value("A")
	private String type;
	/**
	 * 队伍成员
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="T_TEAM_USER",joinColumns=@JoinColumn(name="TEAM_ID"),inverseJoinColumns=@JoinColumn(name="USER_ID"))
	private Set<PoUser> users;
	
	public PoTeam(){}
	public PoTeam(Team team){
		BeanUtils.copyProperties(team,this);
		poEvent = new PoEvent();
		poEvent.setId(team.getEventId());
		leader = new PoUser();
		leader.setId(team.getLeaderId());
		teacher = new PoUser();
		teacher.setId(team.getTeacherId());
		if(team.getUserIds()!=null && team.getUserIds().length>0){
			this.users=new HashSet<PoUser>();
			for(Long pid:team.getUserIds()){
				PoUser poUser = new PoUser();
				poUser.setId(pid);
				this.users.add(poUser);
			}
		}
	}
	public PoTeam wrap(Team team){
		BeanUtils.copyProperties(team,this);
		poEvent = new PoEvent();
		poEvent.setId(team.getEventId());
		leader = new PoUser();
		leader.setId(team.getLeaderId());
		teacher = new PoUser();
		teacher.setId(team.getTeacherId());
		if(team.getUserIds()!=null && team.getUserIds().length>0){
			this.users=new HashSet<PoUser>();
			for(Long pid:team.getUserIds()){
				PoUser poUser = new PoUser();
				poUser.setId(pid);
				this.users.add(poUser);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public PoEvent getPoEvent() {
		return poEvent;
	}
	public void setPoEvent(PoEvent poEvent) {
		this.poEvent = poEvent;
	}
	public PoUser getLeader() {
		return leader;
	}
	public void setLeader(PoUser leader) {
		this.leader = leader;
	}
	public PoUser getTeacher() {
		return teacher;
	}
	public void setTeacher(PoUser teacher) {
		this.teacher = teacher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<PoUser> getUsers() {
		return users;
	}
	public void setUsers(Set<PoUser> users) {
		this.users = users;
	}
	
}
