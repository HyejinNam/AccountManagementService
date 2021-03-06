package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ams.dto.UserDTO;
import com.ams.service.UserService2;

@Controller
public class UserController2 {

	@Autowired
	UserService2 service;
	
	@RequestMapping("/")
	public ModelAndView SelectAllFromUser() {
		ModelAndView mav = new ModelAndView("test");
		List<UserDTO> userList = service.userList();
		mav.addObject("list", userList);
		return mav;
	}
	
}
