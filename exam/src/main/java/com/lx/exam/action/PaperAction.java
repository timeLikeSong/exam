package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoPaper;
import com.lx.exam.searchmodel.PaperSM;
import com.lx.exam.vo.Paper;

@Controller
@RequestMapping("/paper/")
public class PaperAction {
	@Autowired
	IBfService<PoPaper, Paper, PaperSM> paperIbfService;
	@RequestMapping("add")
	public void add(Model model,Paper paper){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Paper paper){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Paper paper){
		
	}
	@RequestMapping("get")
	public void get(Model model,Paper admin,PaperSM paperSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,PaperSM paperSm){
		
	}
}
