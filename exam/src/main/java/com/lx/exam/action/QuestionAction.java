package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoQuestion;
import com.lx.exam.searchmodel.QuestionSM;
import com.lx.exam.vo.Question;

@Controller
@RequestMapping("/question/")
public class QuestionAction {
	@Autowired
	IBfService<PoQuestion, Question, QuestionSM> questionIbfService;
	@RequestMapping("add")
	public void add(Model model,Question question){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Question question){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Question question){
		
	}
	@RequestMapping("get")
	public void get(Model model,Question question,QuestionSM questionSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,QuestionSM questionSm){
		
	}
}
