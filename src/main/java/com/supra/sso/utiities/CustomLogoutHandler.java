package com.supra.sso.utiities;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.supra.sso.model.UserToken;
import com.supra.sso.repository.UserTokenRepository;

public class CustomLogoutHandler implements LogoutHandler{

	@Autowired
	UserTokenRepository userTokenRepository;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Enumeration<String> params = request.getParameterNames();
		String token = null;
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			token = request.getParameter(paramName); 
		}
		
		if(token != null) {
			UserToken userToken = userTokenRepository.findByToken(token);
			if(userToken != null) {
				//delete token
				userTokenRepository.delete(userToken.getId());
			}
		}
		//invalidate session
		authentication = null;
		request.getSession().invalidate();
		
		//redirect to logout of single sign on
		try {
			response.sendRedirect("http://localhost:8080/sso/login?logout");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
