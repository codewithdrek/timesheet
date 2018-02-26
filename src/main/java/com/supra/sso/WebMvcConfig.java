package com.supra.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.supra.sso.utiities.RequestInterceptor;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(requestInterceptor);
    }
    
}