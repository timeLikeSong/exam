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
import com.lx.exam.po.PoExam;
import com.lx.exam.searchmodel.EventSM;
import com.lx.exam.searchmodel.ExamSM;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Event;
import com.lx.exam.vo.Exam;

@Controller
@RequestMapping("/admin/exam/")
public class ExamAction {
	@Autowired
	IBfService<PoExam, Exam, ExamSM> examIbfService;
	@RequestMapping("add")
	public void add(Model model,Exam exam){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(
				StringUtil.isEmpty(exam.getName())||
				StringUtil.isEmpty(exam.getCanIn())||
				null==exam.getEventId()||
				StringUtil.isEmpty(exam.getFaceGroup())||
				StringUtil.isEmpty(exam.getStartTime())||
				StringUtil.isEmpty(exam.getEndTime())||
				null==exam.getDuration()||
				null==exam.getPaperId()||
				null==exam.getStep()
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			exam = examIbfService.add(PoExam.class, exam, "id", "Long");
			model.addAttribute("exam",exam);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,ExamSM examSM){
		try {
			examIbfService.delete(PoExam.class, ExamSM.class,examSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,Exam exam){
		try {
			if(
				null==exam.getId()||
				StringUtil.isEmpty(exam.getName())||
				StringUtil.isEmpty(exam.getCanIn())||
				null==exam.getEventId()||
				StringUtil.isEmpty(exam.getFaceGroup())||
				StringUtil.isEmpty(exam.getStartTime())||
				StringUtil.isEmpty(exam.getEndTime())||
				null==exam.getDuration()||
				null==exam.getPaperId()||
				null==exam.getStep()
					){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			exam = examIbfService.edit(PoExam.class,exam,"id");
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("exam",exam);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,ExamSM examSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Exam exam = examIbfService.get(PoExam.class, Exam.class, examSM);
			if(exam!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("exam",exam);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,ExamSM examSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = examIbfService.getCount(PoExam.class, examSM);
			int recordsTotal = 0;
			if(count>0){
				List<Exam> list = examIbfService.list(PoExam.class, Exam.class, examSM, pb);
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("data",  list);
				recordsTotal =list.size();
			}
			else{
				model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
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
	@RequestMapping("getSelector")
	public void getSelector(Model model){
		List<Exam> list = examIbfService.listBySql("exam.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
}
