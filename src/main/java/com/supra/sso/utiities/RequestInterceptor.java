package com.supra.sso.utiities;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supra.sso.model.User;
import com.supra.sso.model.UserToken;
import com.supra.sso.repository.UserRepository;
import com.supra.sso.repository.UserTokenRepository;
import com.supra.sso.service.SecurityService;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private UserTokenRepository userTokenRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private SecurityService securityService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Enumeration<String> params = request.getParameterNames();
		String token = null;
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			token = request.getParameter(paramName); 
		}
		
		if(token != null) {
			UserToken userToken = userTokenRepository.findByToken(token);
			if(userToken != null) {
				
				//fetch user object
				User user = userRepository.findByUsername(userToken.getUsername());
				
				//add userobject in request or session and autologin
				if(user != null) {
					securityService.autologin(user.getUsername(), user.getPassword());
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("loggedInUser", user);
					httpSession.setAttribute("token", token);
				}
				
				//send the request further
				return true;
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
		
		//return super.preHandle(request, response, handler);
	}

}
