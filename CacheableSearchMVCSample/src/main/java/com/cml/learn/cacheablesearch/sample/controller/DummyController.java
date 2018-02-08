package com.cml.learn.cacheablesearch.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.learn.cacheablesearch.sample.model.User;
import com.github.cmlbeliever.cacheablesearch.annotation.SearchCache;
import com.github.cmlbeliever.cacheablesearch.annotation.SearchCache.RequestType;

@Controller
@RequestMapping("/user")
public class DummyController {

	@RequestMapping("/list")
	public String testPage(Model model, @SearchCache() User u) {
		model.addAttribute("searchParam", u);
		return "user-list";
	}

	@ResponseBody
	@RequestMapping("/list2")
	public User testPage2(HttpServletRequest req, @SearchCache() User u) {
		if (null != u) {
			u.setCacheToken(String.valueOf(req.getAttribute("cacheToken")));
		}
		return u;
	}

	@ResponseBody
	@RequestMapping("/name")
	public String testSingleArg(HttpServletRequest req, @SearchCache String name) {
		return name + ",cacheToken:" + req.getAttribute("cacheToken");
	}

}
