package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoQuestionDB;
import com.lx.exam.searchmodel.QuestionDBSM;
import com.lx.exam.vo.QuestionDB;

@Controller
@RequestMapping("/questionDB/")
public class QuestionDBAction {
	@Autowired
	IBfService<PoQuestionDB, QuestionDB, QuestionDBSM> questionDBIbfService;
	@RequestMapping("add")
	public void add(Model model,QuestionDB questionDB){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,QuestionDB questionDB){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,QuestionDB questionDB){
		
	}
	@RequestMapping("get")
	public void get(Model model,QuestionDB questionDB,QuestionDBSM questionDBSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,QuestionDBSM questionDBSm){
		
	}
}
