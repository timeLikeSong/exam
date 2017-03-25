package com.lx.exam.searchmodel;

import java.io.Serializable;

public class UserSM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1111979560535189L;
	private String sm_eq_email;
	private String sm_eq_phone;
	private String sm_like_realname;
	private String sm_eq_id;
	private String sm_eq_password;
	private String sm_eq_username;
	private String sm_orderby;
	public String getSm_eq_email() {
		return sm_eq_email;
	}
	public void setSm_eq_email(String sm_eq_email) {
		this.sm_eq_email = sm_eq_email;
	}
	public String getSm_eq_phone() {
		return sm_eq_phone;
	}
	public void setSm_eq_phone(String sm_eq_phone) {
		this.sm_eq_phone = sm_eq_phone;
	}
	public String getSm_like_realname() {
		return sm_like_realname;
	}
	public void setSm_like_realname(String sm_like_realname) {
		this.sm_like_realname = sm_like_realname;
	}
	public String getSm_eq_id() {
		return sm_eq_id;
	}
	public void setSm_eq_id(String sm_eq_id) {
		this.sm_eq_id = sm_eq_id;
	}
	public String getSm_eq_password() {
		return sm_eq_password;
	}
	public void setSm_eq_password(String sm_eq_password) {
		this.sm_eq_password = sm_eq_password;
	}
	public String getSm_orderby() {
		return sm_orderby;
	}
	public void setSm_orderby(String sm_orderby) {
		this.sm_orderby = sm_orderby;
	}
	public String getSm_eq_username() {
		return sm_eq_username;
	}
	public void setSm_eq_username(String sm_eq_username) {
		this.sm_eq_username = sm_eq_username;
	}
	
}
