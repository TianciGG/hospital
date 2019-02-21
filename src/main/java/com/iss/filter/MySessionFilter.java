package com.iss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MySessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String redirectURL = httpRequest.getContextPath();
		String url = httpRequest.getRequestURI();
		if (url.indexOf("mylogin.do") != -1 || url.indexOf("goToMyRegPage.do") != -1
				|| url.indexOf("addSysUser.do") != -1) {
			chain.doFilter(request, response);
		} else {
			Object obj = session.getAttribute("myuser");
			if (obj == null || "".equals(obj.toString())) {
				httpResponse.sendRedirect(redirectURL);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
