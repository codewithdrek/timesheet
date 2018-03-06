package com.supra.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supra.sso.model.User;

@Controller
public class TimeSheetController {

	@RequestMapping(value="/welcometimesheet")
	public String openPageForModule(Model m, HttpServletRequest request, HttpSession httpSession) {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			m.addAttribute("username", user.getUsername());
			
			String roleName = user.getRoles().iterator().next().getAuthority();
			m.addAttribute("role", roleName);
			
			m.addAttribute("token", httpSession.getAttribute("token"));
			return "welcometimesheet";
		}
		else {
			return "loginButton";
		}
	}
}