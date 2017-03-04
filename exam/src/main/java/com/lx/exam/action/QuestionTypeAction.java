package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoQuestionType;
import com.lx.exam.searchmodel.QuestionTypeSM;
import com.lx.exam.vo.QuestionType;

@Controller
@RequestMapping("/questionType/")
public class QuestionTypeAction {
	@Autowired
	IBfService<PoQuestionType, QuestionType, QuestionTypeSM> questionTypeIbfService;
	@RequestMapping("add")
	public void add(Model model,QuestionType questionType){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,QuestionType questionType){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,QuestionType questionType){
		
	}
	@RequestMapping("get")
	public void get(Model model,QuestionType questionType,QuestionTypeSM questionTypeSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,QuestionTypeSM questionTypeSm){
		
	}
}
