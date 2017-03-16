package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoPaperRandom;
import com.lx.exam.searchmodel.PaperRandomSM;
import com.lx.exam.vo.PaperRandom;

@Controller
@RequestMapping("/paperRandom/")
public class PaperRandomAction {
	@Autowired
	IBfService<PoPaperRandom, PaperRandom, PaperRandomSM> paperRandomIbfService;
	@RequestMapping("add")
	public void add(Model model,PaperRandom paperRandom){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,PaperRandom paperRandom){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,PaperRandom paperRandom){
		
	}
	@RequestMapping("get")
	public void get(Model model,PaperRandom paperRandom,PaperRandomSM paperRandomSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,PaperRandomSM paperRandomSm){
		
	}
}
