package com.supra.sso.utiities;

import java.net.InetAddress;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
			throws Exception {System.out.println(SecurityContextHolder.getContext().getAuthentication());
			Enumeration<String> params = request.getParameterNames();
			String token = null;
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				token = request.getParameter(paramName); 
			}

			UserToken userToken = null;
			if(StringUtils.isEmpty(token)) {
				userToken = userTokenRepository.findByIpAddress(InetAddress.getLocalHost().getHostAddress().toString());
			}
			else if(token != null) {
				userToken = userTokenRepository.findByToken(token);
			}
			else {
				return true;
			}

			if(userToken != null) {
				//fetch user object
				User user = userRepository.findByUsername(userToken.getUsername());

				//add userobject in request or session and autologin
				if(user != null) {
					securityService.autologin(user.getUsername(), user.getPassword());
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("loggedInUser", user);
					httpSession.setAttribute("token", (StringUtils.isEmpty(token) ? userToken.getToken() : token));
				}
				return true;
			}
			else {
				return true;
			}
	}
}
