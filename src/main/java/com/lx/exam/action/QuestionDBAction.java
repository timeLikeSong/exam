package com.lx.exam.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoQuestionDB;
import com.lx.exam.searchmodel.QuestionDBSM;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.QuestionDB;

@Controller
@RequestMapping("/admin/questiondb/")
public class QuestionDBAction {
	@Autowired
	IBfService<PoQuestionDB, QuestionDB, QuestionDBSM> questionDBIbfService;
	@RequestMapping("add")
	public void add(Model model,QuestionDB questionDB){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(null==questionDB){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return ;
			}
			if(
				StringUtil.isEmpty(questionDB.getName())||
				null==questionDB.getStatus()
				){
					model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
					model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
					return ;
			}
			questionDB.setPosterId(1L);
			questionDB.setCreateDate(DateUtil.getDateTime(new Date()));
			questionDB.setModifyorId(1L);
			questionDB.setModifyDate(DateUtil.getDateTime(new Date()));
			questionDB = questionDBIbfService.add(PoQuestionDB.class,questionDB, "id", "Long");
			model.addAttribute("questiondb",questionDB);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,QuestionDBSM questionDBSM){
		try {
			questionDBIbfService.delete(PoQuestionDB.class, QuestionDBSM.class,questionDBSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,QuestionDB questionDB){
		try {
			if(null==questionDB.getId()||
				StringUtil.isEmpty(questionDB.getName())||
				null==questionDB.getStatus()
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			questionDB.setModifyorId(1L);
			questionDB.setModifyDate(DateUtil.getDateTime(new Date()));
			questionDB = questionDBIbfService.edit(PoQuestionDB.class,questionDB,"id");
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("questiondb",questionDB);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,QuestionDBSM questionDBSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			QuestionDB questionDB = questionDBIbfService.get(PoQuestionDB.class, QuestionDB.class, questionDBSM);
			if(questionDB!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("questiondb",questionDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,QuestionDBSM questionDBSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = questionDBIbfService.getCount(PoQuestionDB.class, questionDBSM);
			int recordsTotal = 0;
			if(count>0){
				List<QuestionDB> list = questionDBIbfService.list(PoQuestionDB.class, QuestionDB.class, questionDBSM, pb);
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
	@RequestMapping("getSelector")
	public void getSelector(Model model){
		List<QuestionDB> list = questionDBIbfService.listBySql("questionDB.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
	@RequestMapping("switchStatus")
	public void switchStatus(Model model,Long id,Integer status){
		if(null==id){
			model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
			model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
			return;
		}
		try {
			PoQuestionDB poQuestionDB = questionDBIbfService.getById(PoQuestionDB.class, id);
			if(null==poQuestionDB){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			else{
				if(poQuestionDB.getStatus()==0){
					poQuestionDB.setStatus(1);
				}
				else{
					poQuestionDB.setStatus(0);
				}
				questionDBIbfService.edit(poQuestionDB);
				model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
				model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
}