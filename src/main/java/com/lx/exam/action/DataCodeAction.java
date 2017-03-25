package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.dao.DataCodeRepository;
import com.lx.exam.po.PoDataCode;
import com.lx.exam.po.PoPaper;
import com.lx.exam.searchmodel.PaperSM;
import com.lx.exam.service.itf.DataCodeService;
import com.lx.exam.vo.DataCode;
import com.lx.exam.vo.Paper;

@Controller
@RequestMapping("/admin/datacode/")
public class DataCodeAction {
	@Autowired
	DataCodeRepository dataCodeRepository;
	@Autowired
	DataCodeService dataCodeService;
	@Autowired
	IBfService<PoPaper, Paper, PaperSM> dataCodeIbfService;
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
	@RequestMapping("getQuestionTypeSelector")
	public void getQuestionTypeSelector(Model model){
		List<PoDataCode> list = dataCodeIbfService.listBySql("questionType.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
	@RequestMapping("getUserTypeSelector")
	public void getEventStepSelector(Model model){
		List<PoDataCode> list = dataCodeIbfService.listBySql("userType.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
	@RequestMapping("getQuestionLevelSelector")
	public void getQuestionLevelSelector(Model model){
		List<PoDataCode> list = dataCodeIbfService.listBySql("questionLevel.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
}
