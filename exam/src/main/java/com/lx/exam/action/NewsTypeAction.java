package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoNewsType;
import com.lx.exam.searchmodel.NewsTypeSM;
import com.lx.exam.vo.NewsType;

@Controller
@RequestMapping("/newsType/")
public class NewsTypeAction {
	@Autowired
	IBfService<PoNewsType, NewsType, NewsTypeSM> newsTypeIbfService;
	@RequestMapping("add")
	public void add(Model model,NewsType newsType){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,NewsType newsType){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,NewsType newsType){
		
	}
	@RequestMapping("get")
	public void get(Model model,NewsType newsType,NewsTypeSM newsTypeSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,NewsTypeSM newsTypeSm){
		
	}
}
