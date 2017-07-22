package com.li.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.li.common.utils.PropertiesUtils;

public  class  BaseFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁BaseFilter拦截器");

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse)arg1;
		// TODO Auto-generated method stub
		String name=PropertiesUtils.getAnProperties("library_name");
		req.setAttribute("li_name", name);
//		System.out.println("BaseFilter过滤通过");
		arg2.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	System.out.println("开启BaseFilter拦截器");
	}

}
