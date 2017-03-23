package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoExam;
import com.lx.exam.po.PoPaper;
import com.lx.exam.searchmodel.ExamSM;
import com.lx.exam.searchmodel.PaperSM;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Event;
import com.lx.exam.vo.Exam;
import com.lx.exam.vo.Paper;

@Controller
@RequestMapping("/admin/paper/")
public class PaperAction {
	@Autowired
	IBfService<PoPaper, Paper, PaperSM> paperIbfService;
	@RequestMapping("add")
	public void add(Model model,Paper paper){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(
				StringUtil.isEmpty(paper.getName())
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			paper = paperIbfService.add(PoPaper.class, paper, "id", "Long");
			model.addAttribute("paper",paper);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,PaperSM paperSM){
		try {
			paperIbfService.delete(PoPaper.class, PaperSM.class,paperSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,Paper paper){
		try {
			if(
				null==paper.getId()||
				StringUtil.isEmpty(paper.getName())
					){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			PoPaper poPaper = new PoPaper(paper);
			paper = new Paper(paperIbfService.edit(poPaper));
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("paper",paper);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,PaperSM paperSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Paper paper = paperIbfService.get(PoPaper.class, Paper.class, paperSM);
			if(paper!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("paper",paper);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,PaperSM paperSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = paperIbfService.getCount(PoPaper.class, paperSM);
			int recordsTotal = 0;
			if(count>0){
				List<Paper> list = paperIbfService.list(PoPaper.class, Paper.class, paperSM, pb);
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
		List<Paper> list = paperIbfService.listBySql("paper.selector");
		if(list.isEmpty()){
			model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		}
		else{
			model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
			model.addAttribute("data",list);
		}
	}
}
