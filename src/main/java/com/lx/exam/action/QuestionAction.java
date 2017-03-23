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
import com.lx.exam.po.PoQuestion;
import com.lx.exam.searchmodel.QuestionSM;
import com.lx.exam.util.DateUtil;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Question;

@Controller
@RequestMapping("/admin/question/")
public class QuestionAction {
	@Autowired
	IBfService<PoQuestion, Question, QuestionSM> questionIbfService;
	@RequestMapping("add")
	public void add(Model model,Question question){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(
				null==question.getTypeId()||
				null==question.getStatus()||
				null==question.getQuestionDBId()||
				null==question.getLevelId()||
				null==question.getScore()||
				StringUtil.isEmpty(question.getContent())||
				StringUtil.isEmpty(question.getAnswer())||
				StringUtil.isEmpty(question.getData())
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			question.setCreateDate(DateUtil.getDateTime(new Date()));
			question.setModifyDate(DateUtil.getDateTime(new Date()));
			question.setPosterId(1L);
			question.setModifyorId(1L);
			question = questionIbfService.add(PoQuestion.class, question, "id", "Long");
			model.addAttribute("question",question);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,QuestionSM questionSM){
		try {
			questionIbfService.delete(PoQuestion.class, QuestionSM.class,questionSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,Question question){
		try {
			if(
				null==question.getId()||
				null==question.getTypeId()||
				null==question.getStatus()||
				null==question.getQuestionDBId()||
				null==question.getLevelId()||
				null==question.getScore()||
				StringUtil.isEmpty(question.getContent())||
				StringUtil.isEmpty(question.getAnswer())||
				StringUtil.isEmpty(question.getData())
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			question.setModifyDate(DateUtil.getDateTime(new Date()));
			question.setModifyorId(1L);
			question = questionIbfService.edit(PoQuestion.class,question,"id");
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("question",question);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,QuestionSM questionSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Question question = questionIbfService.get(PoQuestion.class, Question.class, questionSM);
			if(question!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("question",question);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,QuestionSM questionSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = questionIbfService.getCount(PoQuestion.class, questionSM);
			int recordsTotal = 0;
			if(count>0){
				List<Question> list = questionIbfService.list(PoQuestion.class, Question.class, questionSM, pb);
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
}
