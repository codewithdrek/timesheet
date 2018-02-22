package com.supra.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimeSheetController {

    @RequestMapping(value="/welcometimesheet")
    public String openPageForModule(@RequestParam("username") String username, @RequestParam(name="role") String roles, Model m) {
    	m.addAttribute("username", username);
    	m.addAttribute("role", roles.substring(1, roles.length()-1));
    	return "welcometimesheet";
    }
    
}