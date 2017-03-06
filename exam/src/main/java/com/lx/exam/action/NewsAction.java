package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoNews;
import com.lx.exam.searchmodel.NewsSM;
import com.lx.exam.vo.News;

@Controller
@RequestMapping("/news/")
public class NewsAction {
	@Autowired
	IBfService<PoNews, News, NewsSM> newsIbfService;
	@RequestMapping("add")
	public void add(Model model,News news){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,News admin){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,News news){
		
	}
	@RequestMapping("get")
	public void get(Model model,News admin,NewsSM newsSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,NewsSM newsSm){
		
	}
}
