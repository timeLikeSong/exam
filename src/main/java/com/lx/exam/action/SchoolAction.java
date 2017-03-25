package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoEvent;
import com.lx.exam.po.PoSchool;
import com.lx.exam.searchmodel.EventSM;
import com.lx.exam.searchmodel.SchoolSM;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Event;
import com.lx.exam.vo.School;

@Controller
@RequestMapping("/school/")
public class SchoolAction {
	@Autowired
	IBfService<PoSchool, School, SchoolSM> schoolIbfService;
	@RequestMapping("add")
	public void add(Model model,School school){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(null==school){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return ;
			}
			if(
				StringUtil.isEmpty(school.getName())
				){
					model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
					model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
					return ;
			}
			school = schoolIbfService.add(PoSchool.class, school, "id", "Long");
			model.addAttribute("school",school);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,School school,SchoolSM schoolSM){
		try {
			schoolIbfService.delete(PoSchool.class, SchoolSM.class,schoolSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,School school){
		try {
			if(null==school.getId()||
				StringUtil.isEmpty(school.getName())
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			school = schoolIbfService.edit(PoSchool.class, school, "id");
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("school",school);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,SchoolSM schoolSm){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			School school = schoolIbfService.get(PoSchool.class, School.class, schoolSm);
			if(school!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("school",school);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,SchoolSM schoolSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = schoolIbfService.getCount(PoSchool.class, schoolSM);
			int recordsTotal = 0;
			if(count>0){
				List<School> list = schoolIbfService.list(PoSchool.class, School.class, schoolSM, pb);
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("data",  list);
				recordsTotal =list.size();
			}
			else{
				model.addAttribute("data", "");
			}
			//总记录数
			model.addAttribute("recordsTotal",recordsTotal);
			//符合条件的记录数
			model.addAttribute("recordsFiltered",count);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
}
