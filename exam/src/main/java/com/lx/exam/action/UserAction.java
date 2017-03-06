package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoUser;
import com.lx.exam.searchmodel.UserSM;
import com.lx.exam.vo.User;

@Controller
@RequestMapping("/user/")
public class UserAction {
	@Autowired
	IBfService<PoUser, User, UserSM> userIbfService;
	@RequestMapping("add")
	public void add(Model model,User user){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,User user){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,User user){
		
	}
	@RequestMapping("get")
	public void get(Model model,User user,UserSM userSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,UserSM userSm){
		
	}
}
