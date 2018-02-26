package com.supra.sso.utiities;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supra.sso.model.User;
import com.supra.sso.model.UserToken;
import com.supra.sso.repository.UserRepository;
import com.supra.sso.repository.UserTokenRepository;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private UserTokenRepository userTokenRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	
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
				
				//add userobject in request or session
				request.getSession().setAttribute("loggedInUser", user);
				
				//send the request further
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
		//return super.preHandle(request, response, handler);
	}

}
