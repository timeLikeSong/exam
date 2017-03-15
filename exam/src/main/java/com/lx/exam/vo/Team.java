package com.lx.exam.vo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoTeam;
import com.lx.exam.po.PoUser;

public class Team {
	private Long id;
	private String name;
	private String description;
	private Integer status;
	private Long eventId;
	private String eventName;
	private Long leaderId;
	private String leaderName;
	private Long teacherId;
	private String teacherName;
	private Set<User> users;
	private Long[] userIds;
	
	public Team(){}
	public Team(PoTeam poTeam){
		BeanUtils.copyProperties(poTeam, this);
		this.eventId=poTeam.getPoEvent().getId();
		this.eventName=poTeam.getPoEvent().getName();
		this.leaderId=poTeam.getLeader().getId();
		this.leaderName=poTeam.getLeader().getRealname();
		this.teacherId=poTeam.getTeacher().getId();
		this.teacherName=poTeam.getTeacher().getRealname();
		
		if(poTeam.getUsers()!=null && poTeam.getUsers().size()>0){
			users = new HashSet<User>();
			for(PoUser poUser:poTeam.getUsers()){
				users.add(new User(poUser));
			}
		}
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
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long[] getUserIds() {
		return userIds;
	}
	public void setUserIds(Long[] userIds) {
		this.userIds = userIds;
	}
	
	
}
