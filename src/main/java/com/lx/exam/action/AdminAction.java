package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoAdmin;
import com.lx.exam.searchmodel.AdminSM;
import com.lx.exam.vo.Admin;

@Controller
@RequestMapping("/admin/")
public class AdminAction {
	@Autowired
	IBfService<PoAdmin, Admin, AdminSM> adminIbfService;
	@RequestMapping("add")
	public void add(Model model,Admin admin){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Admin admin){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Admin admin){
		
	}
	@RequestMapping("get")
	public void get(Model model,Admin admin,AdminSM adminSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,AdminSM adminSm){
		
	}
}
