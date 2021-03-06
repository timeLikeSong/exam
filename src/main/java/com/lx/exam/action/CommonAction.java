package com.lx.exam.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonAction {
	@RequestMapping("/")
	public String index(){
		return "user/html/index";
	}
	@RequestMapping("index")
	public String index2(){
		return "user/html/index";
	}
	@RequestMapping("login")
	public String login(){
		return "user/html/login";
	}
	@RequestMapping("logout")
	public String logout(){
		return "user/html/logout";
	}
	@RequestMapping("admin")
	public String admin(){
		return "admin/html/index";
	}
	@RequestMapping("{module}/{url}")
	public String goUrl(@PathVariable String module, @PathVariable String url, Model model) {
		return module + "/html/" + url;
	}
	@RequestMapping("{module}/{url}/{value}")
	public String goUrl1P(@PathVariable String module, @PathVariable String url, @PathVariable String value,
			Model model) {
		return module + "/html/" + url;
	}
	@RequestMapping("{module}/{url}/{tempparam}/{tempparam2}")
	public String goUrl2P(HttpServletRequest req, @PathVariable String module, @PathVariable String url,
			@PathVariable String tempparam, @PathVariable String tempparam2, Model model) throws Exception {
		return module + "/html/" + url;
	}
	@RequestMapping("{module}/{url}/{tempparam}/{tempparam2}/{tempparam3}")
	public String goUrl3P(HttpServletRequest req, @PathVariable String module, @PathVariable String url,
			@PathVariable String tempparam, @PathVariable String tempparam2, @PathVariable String tempparam3,
			Model model) throws Exception {
		return module + "/html/" + url;
	}
}

