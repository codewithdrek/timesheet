package com.supra.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supra.sso.model.User;

@Controller
public class TimeSheetController {

    @RequestMapping(value="/welcometimesheet")
    public String openPageForModule(Model m, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute("loggedInUser");
    	m.addAttribute("username", user.getUsername());
    	String roleName = user.getRoles().iterator().next().getAuthority();
    	m.addAttribute("role", roleName);
    	return "welcometimesheet";
    }
    
}