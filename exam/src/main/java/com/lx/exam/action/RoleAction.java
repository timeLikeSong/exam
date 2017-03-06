package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoRole;
import com.lx.exam.searchmodel.RoleSM;
import com.lx.exam.vo.Role;

@Controller
@RequestMapping("/role/")
public class RoleAction {
	@Autowired
	IBfService<PoRole, Role, RoleSM> roleIbfService;
	@RequestMapping("add")
	public void add(Model model,Role role){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Role role){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Role role){
		
	}
	@RequestMapping("get")
	public void get(Model model,Role role,RoleSM roleSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,RoleSM roleSm){
		
	}
}
