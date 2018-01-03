package com.cml.learn.cacheablesearch.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cml.learn.cacheablesearch.annotation.SearchCache;
import com.cml.learn.cacheablesearch.sample.model.User;

@Controller
@RequestMapping("/")
public class DummyController {

	@RequestMapping("/testPage")
	public String testPage(Model model, @SearchCache() User u) {
		model.addAttribute("key", "from server:" + u);
		return "dummy";
	}
}
