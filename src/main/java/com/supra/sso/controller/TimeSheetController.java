package com.supra.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeSheetController {

    @RequestMapping(value="/welcometimesheet")
    public String openPageForModule() {
    	return "welcometimesheet";
    }
    
}