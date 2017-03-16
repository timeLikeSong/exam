package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoSchool;
import com.lx.exam.searchmodel.SchoolSM;
import com.lx.exam.vo.School;

@Controller
@RequestMapping("/school/")
public class SchoolAction {
	@Autowired
	IBfService<PoSchool, School, SchoolSM> schoolIbfService;
	@RequestMapping("add")
	public void add(Model model,School school){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,School school){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,School school){
		
	}
	@RequestMapping("get")
	public void get(Model model,School school,SchoolSM schoolSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,SchoolSM schoolSm){
		
	}
}
