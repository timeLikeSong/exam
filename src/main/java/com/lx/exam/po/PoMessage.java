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
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.lx.exam.util.DateUtil;
import com.lx.exam.vo.Message;

@Entity
@Table(name = "T_MESSAGE")
public class PoMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2460329071744489046L;
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发送者
	 */
	@ManyToOne
	@JoinColumn(name="sender_id")
	private PoAdmin sender;
	/**
	 * 接收者
	 */
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private PoUser receiver;
	/**
	 * 发送时间
	 */
	@Column(name="send_time")
	private Date sendTime;
	/**
	 * 状态 0未读，1已读
	 */
	private Integer status;
	/**
	 * 消息类型
	 */
	private PoDataCode type;
	
	public PoMessage(){}
	public PoMessage(Message message){
		BeanUtils.copyProperties(message, this);
		receiver = new PoUser();
		receiver.setId(message.getReceiverId());
		sender = new PoAdmin();
		sender.setId(message.getSenderId());
		sendTime=DateUtil.parseDate(message.getSendTime());
		type=new PoDataCode();
		type.setId(message.getTypeId());
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
	public PoAdmin getSender() {
		return sender;
	}
	public void setSender(PoAdmin sender) {
		this.sender = sender;
	}
	public PoUser getReceiver() {
		return receiver;
	}
	public void setReceiver(PoUser receiver) {
		this.receiver = receiver;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public PoDataCode getType() {
		return type;
	}
	public void setType(PoDataCode type) {
		this.type = type;
	}
	
}
