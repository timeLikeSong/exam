package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoExam;
import com.lx.exam.searchmodel.ExamSM;
import com.lx.exam.vo.Exam;

@Controller
@RequestMapping("/exam/")
public class ExamAction {
	@Autowired
	IBfService<PoExam, Exam, ExamSM> examIbfService;
	@RequestMapping("add")
	public void add(Model model,Exam exam){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Exam exam){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Exam exam){
		
	}
	@RequestMapping("get")
	public void get(Model model,Exam admin,ExamSM examSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,ExamSM examSm){
		
	}
}
