package com.ams.jpa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ams.dto.UserInfoDto;
import com.ams.jpa.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	/**
	 * 회원가입
	 * 
	 * @param infoDto
	 * @return
	 */
	@PostMapping("/user")
	public String signUp(UserInfoDto infoDto) {
		userService.save(infoDto);
		return "redirect:/login";
	}

	/**
	 * logout
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}

}
