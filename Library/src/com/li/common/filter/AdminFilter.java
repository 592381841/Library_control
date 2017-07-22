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

import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.dto.LibraryAdminDto;

public class AdminFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁AdminFilter拦截器");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse)resp;
		LibraryAdminDto r=(LibraryAdminDto) request.getSession().getAttribute("Liadmin");
		if(r==null){
//			System.out.println("admin");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("开启AdminFilter拦截器");
	}

}
