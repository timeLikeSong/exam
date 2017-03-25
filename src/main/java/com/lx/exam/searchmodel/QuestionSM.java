package com.lx.exam.searchmodel;

import java.io.Serializable;

public class QuestionSM implements Serializable{
	private String sm_eq_id;
	private String sm_like_name;
	public String getSm_eq_id() {
		return sm_eq_id;
	}
	public void setSm_eq_id(String sm_eq_id) {
		this.sm_eq_id = sm_eq_id;
	}
	public String getSm_like_name() {
		return sm_like_name;
	}
	public void setSm_like_name(String sm_like_name) {
		this.sm_like_name = sm_like_name;
	}
}
