package com.lx.exam.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.po.PoAddress;
import com.lx.exam.searchmodel.AddressSM;
import com.lx.exam.vo.Address;

@Controller
@RequestMapping("/address/")
public class AddressAction {
	@Autowired
	IBfService<PoAddress, Address, AddressSM> addressIbfService;
	@RequestMapping("add")
	public void add(Model model,Address address){
		
	}
	@RequestMapping("delete")
	public void delete(Model model,Address address){
		
	}
	@RequestMapping("edit")
	public void edit(Model model,Address address){
		
	}
	@RequestMapping("get")
	public void get(Model model,Address address,AddressSM addressSm){
		
	}
	@RequestMapping("list")
	public void get(Model model,PageBean pb,AddressSM addressSm){
		
	}
}
