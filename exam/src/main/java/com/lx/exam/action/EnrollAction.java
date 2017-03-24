package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoEnroll;
import com.lx.exam.searchmodel.EnrollSM;
import com.lx.exam.vo.Enroll;

@Controller
@RequestMapping("/enroll/")
public class EnrollAction {
	@Autowired
	IBfService<PoEnroll, Enroll, EnrollSM> enrollIbfService;
	@RequestMapping("add")
	public void add(Model model,Enroll enroll){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Enroll enroll){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Enroll enroll){
		
	}
	@RequestMapping("get")
	public void get(Model model,Enroll enroll,EnrollSM enrollSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,EnrollSM enrollSm){
		
	}
}
