package com.li.common.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.li.common.dto.DownloadInformation;
import com.li.common.service.ICommonService;
import com.opensymphony.xwork2.ActionSupport;
public class BaseCommonAction extends ActionSupport implements ApplicationAware, RequestAware, SessionAware,ServletRequestAware,ServletResponseAware {
	private Map<String,Object> request;
	private Map<String,Object> session;
	private Map<String,Object> application;
	private ICommonService commonService;
	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

	private HttpServletRequest httpServletRequest;
	private HttpSession httpSession;
	private ServletContext servletContext;
	private HttpServletResponse httpServletResponse; 
	private Map<String,Object> jsonDataMap;

	private DownloadInformation downloadInformation;
	//登录所需
	private String usersType;
	private String usersName;
	private String vertiyCode;
	private String passwd;
	
	
	
	public String getVertiyCode() {
		return vertiyCode;
	}

	public void setVertiyCode(String vertiyCode) {
		this.vertiyCode = vertiyCode;
	}

	public String getUsersType() {
		return usersType;
	}

	public void setUsersType(String usersType) {
		this.usersType = usersType;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	

		public DownloadInformation getDownloadInformation() {
		return downloadInformation;
	}

	public void setDownloadInformation(DownloadInformation downloadInformation) {
		this.downloadInformation = downloadInformation;
	}



		public Map<String, Object> getJsonDataMap() {
		return jsonDataMap;
	}

	public void setJsonDataMap(Map<String, Object> jsonDataMap) {
		this.jsonDataMap = jsonDataMap;
	}

		@Override
		public void setApplication(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
					this.application=arg0;
		}

		@Override
		public void setRequest(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
				this.request=arg0;
		}

		@Override
		public void setSession(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
				this.session=arg0;
		}

		@Override
		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			this.httpServletResponse=arg0;
			
			
		}

		@Override
		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			this.httpServletRequest=arg0;
			this.servletContext=arg0.getServletContext();
			this.httpSession=arg0.getSession();
			
		}

		public HttpServletRequest getHttpServletRequest() {
			return httpServletRequest;
		}

		public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
			this.httpServletRequest = httpServletRequest;
		}

		public HttpSession getHttpSession() {
			return httpSession;
		}

		public void setHttpSession(HttpSession httpSession) {
			this.httpSession = httpSession;
		}

		public ServletContext getServletContext() {
			return servletContext;
		}

		public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
		}

		public HttpServletResponse getHttpServletResponse() {
			return httpServletResponse;
		}

		public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
			this.httpServletResponse = httpServletResponse;
		}

		public Map<String, Object> getRequest() {
			return request;
		}

		public Map<String, Object> getSession() {
			return session;
		}

		public Map<String, Object> getApplication() {
			return application;
		}


}
