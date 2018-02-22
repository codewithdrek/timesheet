package com.supra.sso.utiities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supra.sso.model.UserToken;
import com.supra.sso.repository.UserTokenRepository;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private UserTokenRepository userTokenRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = (String) request.getSession().getAttribute("token");
		UserToken userToken = userTokenRepository.findOne(Long.parseLong(token));
		if(userToken != null) {
			return true;
		}
		else {
			return false;
		}
		//return super.preHandle(request, response, handler);
	}

}
