package com.cml.learn.cacheablesearch.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// @RequestMapping("/user/list")
	// public String getUserList(UserListQueryParam param, HttpServletRequest
	// req) {
	// // 参数useCache为true时，表示需要使用缓存数据
	// if (param.isUseCache()) {
	// // 从缓存中读取数据
	// param = QueryParamCacheStoreUtil.getCache(req, "/user/list");
	// } else {
	// // 将数据放入缓存中
	// QueryParamCacheStoreUtil.putCache(req, "/user/list", param);
	// }
	// // 其他业务处理
	//
	// return "/user/list";
	// }

	@ResponseBody
	@RequestMapping("/user/list")
	public UserListQueryParam getUserList(UserListQueryParam param, HttpServletRequest req) {
		// 参数useCache为true时，表示需要使用缓存数据
		param = QueryParamCacheStoreUtil.retrieveCacheIfNeed(req, req.getRequestURI().toString(), param);

		return param;
	}
}
