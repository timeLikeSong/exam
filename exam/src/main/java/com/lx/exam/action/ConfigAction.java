package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoConfig;
import com.lx.exam.searchmodel.ConfigSM;
import com.lx.exam.vo.Config;

@Controller
@RequestMapping("/config/")
public class ConfigAction {
	@Autowired
	IBfService<PoConfig, Config, ConfigSM> configIbfService;
	@RequestMapping("add")
	public void add(Model model,Config config){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Config config){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Config config){
		
	}
	@RequestMapping("get")
	public void get(Model model,Config config,ConfigSM configSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,ConfigSM configSm){
		
	}
}
