package com.lx.exam.vo;

import org.springframework.beans.BeanUtils;

import com.lx.exam.po.PoEmail;
import com.lx.exam.util.DateUtil;

public class Email {
	private Long id;
	private String title;
	private String content;
	private Long senderId;
	private String senderName;
	private Long receiverId;
	private String receiverName;
	private String sendTime;
	private Integer status;
	private Long typeId;
	private String typeName;
	
	public Email(){}
	public Email(PoEmail poEmail){
		BeanUtils.copyProperties(poEmail, this);
		senderId = poEmail.getSender().getId();
		senderName=poEmail.getSender().getUsername();
		receiverId = poEmail.getReceiver().getId();
		receiverName=poEmail.getReceiver().getUsername();
		sendTime=DateUtil.format(poEmail.getSendTime());
		typeId= poEmail.getType().getId();
		typeName=  poEmail.getType().getTitle();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
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
	
}
