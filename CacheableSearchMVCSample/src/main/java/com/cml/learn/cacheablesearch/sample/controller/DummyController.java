package com.cml.learn.cacheablesearch.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/list2")
	public String testPage2(Model model, @SearchCache(requestType = RequestType.RequestBody) User u) {
//		model.addAttribute("searchParam", u);
		return "user-list";
	}

	// @ResponseBody
	// @RequestMapping("/list")
	// public UserListQueryParam getUserList(UserListQueryParam param,
	// HttpServletRequest req) {
	// // 参数useCache为true时，表示需要使用缓存数据
	// param = QueryParamCacheStoreUtil.retrieveCacheIfNeed(req,
	// req.getRequestURI().toString(), param);
	//
	// return param;
	// }
}
