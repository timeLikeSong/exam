package com.lx.exam.action;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoAddress;
import com.lx.exam.po.PoEvent;
import com.lx.exam.searchmodel.AddressSM;
import com.lx.exam.searchmodel.EventSM;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Address;
import com.lx.exam.vo.Event;
import com.mchange.v2.lang.ObjectUtils;

@Controller
@RequestMapping("/event/")
public class EventAction {
	@Autowired
	IBfService<PoEvent, Event, EventSM> eventIbfService;
	@RequestMapping("add")
	public void add(Model model,Event event){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(null==event){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return ;
			}
			if(
				StringUtil.isEmpty(event.getName())||
				null==event.getCurrentStep()||
				StringUtil.isEmpty(event.getEnrollStartTime())||
				StringUtil.isEmpty(event.getEnrollEndTime())||
				StringUtil.isEmpty(event.getSteps())||
				StringUtil.isEmpty(event.getGroupRule())||
				null==event.getStatus()
				){
					model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
					model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
					return ;
			}
			event = eventIbfService.add(PoEvent.class, event, "id", "Long");
			model.addAttribute("event",event);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("delete")
	public void delete(Model model,EventSM eventSM){
		try {
			eventIbfService.delete(PoEvent.class, EventSM.class,eventSM );
			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("edit")
	public void edit(Model model,Event event){
		try {
			if(null==event.getId()||
				StringUtil.isEmpty(event.getName())||
				null==event.getCurrentStep()||
				StringUtil.isEmpty(event.getEnrollStartTime())||
				StringUtil.isEmpty(event.getEnrollEndTime())||
				StringUtil.isEmpty(event.getSteps())||
				StringUtil.isEmpty(event.getGroupRule())||
				null==event.getStatus()
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			PoEvent poEvent = new PoEvent(event);
			event = new Event(eventIbfService.edit(poEvent));
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("event",event);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,EventSM eventSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Event event = eventIbfService.get(PoEvent.class, Event.class, eventSM);
			if(event!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("event",event);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,EventSM eventSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = eventIbfService.getCount(PoEvent.class, eventSM);
			int recordsTotal = 0;
			if(count>0){
				List<Event> list = eventIbfService.list(PoEvent.class, Event.class, eventSM, pb);
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
	@RequestMapping("switchStatus")
	public void switchStatus(Model model,Long id,Integer status){
		if(null==id){
			model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
			model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
			return;
		}
		try {
			PoEvent poEvent = eventIbfService.getById(PoEvent.class, id);
			if(null==poEvent){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			else{
				if(poEvent.getStatus()==0){
					poEvent.setStatus(1);
				}
				else{
					poEvent.setStatus(0);
				}
				eventIbfService.edit(poEvent);
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
