package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.po.PoFunction;
import com.lx.exam.service.itf.FunctionService;
import com.lx.exam.vo.Function;

@Controller
@RequestMapping("/function/")
public class FunctionAction {
	@Autowired
	FunctionService functionService;
	@RequestMapping("add")
	public void add(Model model,Function function){
		function = functionService.add(function);
		if(function!=null){
			model.addAttribute("msg","OK");
			model.addAttribute("function", function);
		}
		else{
			model.addAttribute("msg","Failed");
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,Function function){
		if(functionService.delete(function)){
			model.addAttribute("msg","OK");
		}
		else{
			model.addAttribute("msg","Failed");
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,Function function){
		function = functionService.edit(function);
		if(function!=null){
			model.addAttribute("msg","OK");
			model.addAttribute("function", function);
		}
		else{
			model.addAttribute("msg","Failed");
		}
	}
	@RequestMapping("get")
	public void get(Model model,Long id){
		Function function = functionService.get(id);
		if(function!=null){
			model.addAttribute("msg","OK");
			model.addAttribute("function", function);
		}
		else{
			model.addAttribute("msg","Not Found");
		}
	}
	@RequestMapping("list")
	public void list(Model model,Long pid){
		List<Function> list = functionService.list(pid);
		if(list!=null && list.size()>0){
			model.addAttribute("msg","OK");
			model.addAttribute("functions", list);
		}
		else{
			model.addAttribute("msg","Not Found");
		}
	}
}
