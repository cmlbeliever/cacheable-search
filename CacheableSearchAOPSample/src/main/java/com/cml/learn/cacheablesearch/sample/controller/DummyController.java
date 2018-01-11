package com.cml.learn.cacheablesearch.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cml.learn.cacheablesearch.annotation.SearchCache;
import com.cml.learn.cacheablesearch.sample.model.User;

@Controller
@RequestMapping("/user")
public class DummyController {

	@RequestMapping("/list")
	public String userList(Model model, @SearchCache() User u) {
		model.addAttribute("searchParam", u);
		return "user-list";
	}

	/**
	 * 测试没有model类型的参数
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("/list2")
	public String userList2(@SearchCache() User u) {
		return "user-list";
	}
}
