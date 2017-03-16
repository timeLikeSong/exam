package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoLog;
import com.lx.exam.searchmodel.LogSM;
import com.lx.exam.vo.Log;

@Controller
@RequestMapping("/log/")
public class LogAction {
	@Autowired
	IBfService<PoLog, Log, LogSM> logIbfService;
	@RequestMapping("add")
	public void add(Model model,Log log){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Log log){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Log log){
		
	}
	@RequestMapping("get")
	public void get(Model model,Log log,LogSM logSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,LogSM logSm){
		
	}
}
