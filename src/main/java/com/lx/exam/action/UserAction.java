package com.lx.exam.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lx.exam.common.Constant;
import com.lx.exam.common.MessageConstant;
import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoAddress;
import com.lx.exam.po.PoUser;
import com.lx.exam.searchmodel.AddressSM;
import com.lx.exam.searchmodel.UserSM;
import com.lx.exam.util.StringUtil;
import com.lx.exam.vo.Address;
import com.lx.exam.vo.User;
import com.mchange.v2.lang.ObjectUtils;

@Controller
@RequestMapping("/user/")
public class UserAction {
	@Autowired
	IBfService<PoUser, User, UserSM> userIbfService;
	@RequestMapping("regist")
	public void regist(Model model,User user){
		model.addAttribute("STATUS", MessageConstant.STATUS.ADD_SUCCESS);
		model.addAttribute("MSG", MessageConstant.MESSAGE.ADD_SUCCESS);
		try {
			if(null==user){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return ;
			}
			if(
				StringUtil.isEmpty(user.getEmail())||
				StringUtil.isEmpty(user.getUsername())||
				StringUtil.isEmpty(user.getPassword())||
				StringUtil.isEmpty(user.getPhone())||
				null==user.getTypeId()||
				null==user.getLevel()
				){
					model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
					model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
					return ;
			}
			UserSM userSM = new UserSM();
			userSM.setSm_eq_email(user.getEmail());
			User user2 = userIbfService.get(PoUser.class, User.class, userSM);
			if(null!=user2){
				model.addAttribute("STATUS", MessageConstant.STATUS.EMAIL_EXIST);
				model.addAttribute("MSG", MessageConstant.MESSAGE.EMAIL_EXIST);
				return;
			}
			userSM.setSm_eq_email(null);
			userSM.setSm_eq_username(user.getUsername());
			user2 = userIbfService.get(PoUser.class, User.class, userSM);
			if(null!=user2){
				model.addAttribute("STATUS", MessageConstant.STATUS.USER_EXIST);
				model.addAttribute("MSG", MessageConstant.MESSAGE.USER_EXIST);
				return;
			}
			user = userIbfService.add(PoUser.class, user, "id", "Long");
			model.addAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
//	@RequestMapping("delete")
//	public void delete(Model model,UserSM userSM){
//		try {
//			userIbfService.delete(PoUser.class, UserSM.class,userSM );
//			model.addAttribute("STATUS", MessageConstant.STATUS.DEL_SUCCESS);
//			model.addAttribute("MSG", MessageConstant.MESSAGE.DEL_SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
//			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
//		}
//	}
	@RequestMapping("edit")
	public void edit(Model model,User user,HttpSession session){
		User user2 = (User) session.getAttribute(Constant.LOGIN);
		if(null==user2){
			model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
			model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
			return;
		}
		try {
			if(
				null==user.getTypeId()||
				null==user.getLevel()
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			user2.setAddressId(user.getAddressId());
			user2.setDescription(user.getDescription());
			user2.setIdcard(user.getIdcard());
			user2.setPhoto(user.getPhoto());
			user2.setPhone(user.getPhone());
			user2.setRealname(user.getRealname());
			user = userIbfService.edit(PoUser.class, user2, "id");
			model.addAttribute("STATUS", MessageConstant.STATUS.EDIT_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EDIT_SUCCESS);
			model.addAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("login")
	public void login(Model model,UserSM userSM,String validCode,HttpSession session){
		try {
			if(
				StringUtil.isEmpty(userSM.getSm_eq_email())||
				StringUtil.isEmpty(userSM.getSm_eq_password())
				){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			String validCode2 = (String)session.getAttribute(Constant.VALID_CODE);
			if(StringUtil.isEmpty(validCode)|| !validCode.equals(validCode2)){
				model.addAttribute("STATUS", MessageConstant.STATUS.VALIDCODE_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.VALIDCODE_ERROR);
				return;
			}
			UserSM userSM2 = new UserSM();
			userSM2.setSm_eq_email(userSM.getSm_eq_email());
			User user = userIbfService.get(PoUser.class, User.class, userSM2);
			if(null==user){
				model.addAttribute("STATUS", MessageConstant.STATUS.USER_NOT_EXIST);
				model.addAttribute("MSG", MessageConstant.MESSAGE.USER_NOT_EXIST);
				return;
			}
			userSM2.setSm_eq_password(userSM.getSm_eq_password());
			user = userIbfService.get(PoUser.class, User.class, userSM2);
			if(null==user){
				model.addAttribute("STATUS", MessageConstant.STATUS.PASSWORD_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PASSWORD_ERROR);
				return;
			}
			
			model.addAttribute("STATUS", MessageConstant.STATUS.LOGIN_SUCCESS);
			model.addAttribute("MSG", MessageConstant.MESSAGE.LOGIN_SUCCESS);
			session.setAttribute(Constant.LOGIN, user);
			model.addAttribute("user",user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("get")
	public void get(Model model,UserSM userSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			User user = userIbfService.get(PoUser.class, User.class, userSM);
			if(user!=null){
				model.addAttribute("STATUS",  MessageConstant.STATUS.FOUND);
				model.addAttribute("user",user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("STATUS", MessageConstant.STATUS.EXCEPTION);
			model.addAttribute("MSG", MessageConstant.MESSAGE.EXCEPTION);
		}
	}
	@RequestMapping("list")
	public void list(Model model,PageBean pb,UserSM userSM){
		model.addAttribute("STATUS",  MessageConstant.STATUS.NOT_FOUND);
		try {
			Long count = userIbfService.getCount(PoUser.class, userSM);
			int recordsTotal = 0;
			if(count>0){
				List<User> list = userIbfService.list(PoUser.class, User.class, userSM, pb);
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
			PoUser poUser = userIbfService.getById(PoUser.class, id);
			if(null==poUser){
				model.addAttribute("STATUS", MessageConstant.STATUS.PARAM_ERROR);
				model.addAttribute("MSG", MessageConstant.MESSAGE.PARAM_ERROR);
				return;
			}
			else{
				if(poUser.getStatus()==0){
					poUser.setStatus(1);
				}
				else{
					poUser.setStatus(0);
				}
				userIbfService.edit(poUser);
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