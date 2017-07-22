package com.li.common.filter.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.li.common.dto.LibraryAdminDto;

public class BorrowControlFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("销毁BorrowControlFilter拦截器");
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse)resp;
		LibraryAdminDto r=(LibraryAdminDto) request.getSession().getAttribute("Liadmin");
		if(r==null){
			chain.doFilter(request, response);
		}
		else if(r.getLibJu()){
			chain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
System.out.println("开启BorrowControlFilter拦截器");
	}

}
