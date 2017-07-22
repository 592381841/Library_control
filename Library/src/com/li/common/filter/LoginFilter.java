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
import com.li.common.dto.ReaderDto;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		System.out.println("销毁LoginFilter拦截器");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse)res;
		LibraryAdminDto a=(LibraryAdminDto) request.getSession().getAttribute("Liadmin");
		ReaderDto r=(ReaderDto) request.getSession().getAttribute("reader");
		if(a==null&&r==null){
			chain.doFilter(request, response);
		}else if(a!=null){
			response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
		}
		else if(r!=null){
	
			response.sendRedirect(request.getContextPath()+"/users/index.jsp");
			
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("开启Loginfilter拦截器");
	}
	

}
