package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.dao.DataCodeRepository;
import com.lx.exam.po.PoDataCode;
import com.lx.exam.service.itf.DataCodeService;
import com.lx.exam.vo.DataCode;

@Controller
@RequestMapping("/datacode/")
public class DataCodeAction {
	@Autowired
	DataCodeRepository dataCodeRepository;
	@Autowired
	DataCodeService dataCodeService;
	
	@RequestMapping("tree")
	public String tree(){
		return "admin/html/tree";
	}
	@RequestMapping("list")
	public void list(Long pid,Model model){
		model.addAttribute("nodes",dataCodeService.list(pid));
	}
	@RequestMapping("get")
	public void get(Long id,Model model){
		PoDataCode poDataCode = dataCodeRepository.findOne(id);
		if(poDataCode!=null){
			DataCode dataCode = new DataCode(poDataCode);
			model.addAttribute("msg", "OK");
			model.addAttribute("dataCode", dataCode);
		}
		else{
			model.addAttribute("msg", "Not Found");
		}
	}
	@RequestMapping("delete")
	public void delete(Long id,Model model){
		boolean flag = dataCodeService.delete(id);
		if(flag){
			model.addAttribute("msg", "OK");
		}
		else{
			model.addAttribute("msg", "Failed");
		}
	}
	@RequestMapping("edit")
	public void edit(DataCode dataCode,Model model){
		if(dataCode!=null && dataCode.getId()!=null){
			PoDataCode poDataCode = dataCodeRepository.findOne(dataCode.getId());
			if(poDataCode!=null){
				poDataCode.wrap(dataCode);
				dataCodeRepository.save(poDataCode);
				model.addAttribute("msg", "OK");
			}
			else{
				model.addAttribute("msg", "Failed");
			}
		}
		else{
			model.addAttribute("msg", "Not Found");
		}
	}
	@RequestMapping("add")
	public void add(DataCode dataCode,Model model){
		if(dataCode!=null && dataCode.getTitle()!=null&& !"".equals(dataCode.getTitle())){
			PoDataCode poDataCode = new PoDataCode(dataCode);
			dataCode=new DataCode(dataCodeRepository.save(poDataCode));
			model.addAttribute("msg", "OK");
			model.addAttribute("dataCode", dataCode);
		}
		else{
			model.addAttribute("msg", "Failed");
		}
	}
}
