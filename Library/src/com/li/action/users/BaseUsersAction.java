package com.li.action.users;

import java.sql.Timestamp;
import java.util.List;
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

import com.li.common.domain.BorrowInfo;
import com.li.common.dto.BookInfoDtoByUser;
import com.li.common.dto.BookTypeDto;
import com.li.common.dto.BorrowInfoDto;
import com.li.common.dto.BorrowOperationDto;
import com.li.common.dto.DownloadInformation;
import com.li.common.dto.LibraryInfo;
import com.li.common.dto.PageInfo;
import com.li.service.users.IUsersService;
import com.opensymphony.xwork2.ActionSupport;
public class BaseUsersAction extends ActionSupport implements ApplicationAware, RequestAware, SessionAware,ServletRequestAware,ServletResponseAware {
	public  static String FORWARD ="forward";
	private Map<String,Object> request;
	private Map<String,Object> session;
	private Map<String,Object> application;
	private HttpServletRequest httpServletRequest;
	private HttpSession httpSession;
	private ServletContext servletContext;
	private HttpServletResponse httpServletResponse; 
	private Map<String,Object> jsonDataMap;
	private DownloadInformation downloadInformation;
	private IUsersService usersService;
	
	//修改密码
	private String  oldPasswd;
	private String newPasswd;
	private String  newpasswdagain;
	//图书分类
	private List<List<BookTypeDto>> bookTypeDtosList;
	private BookTypeDto bookTypeDto;
	//分页
	private PageInfo pageInfo;
	//图书
	private BookInfoDtoByUser bookInfoDto;
	//借阅
	private BorrowInfo borrowInfo;
	private List<BorrowInfo> borrowInfos;
	private List<BorrowInfoDto> borrowInfoDtos;
	private List<BorrowOperationDto> borrowOperationDtos;
	private Timestamp BeginTime;
	private Timestamp EndTime;
	private  int operationType;

	
	
	public List<BorrowOperationDto> getBorrowOperationDtos() {
		return borrowOperationDtos;
	}

	public void setBorrowOperationDtos(List<BorrowOperationDto> borrowOperationDtos) {
		this.borrowOperationDtos = borrowOperationDtos;
	}

	public Timestamp getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		BeginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return EndTime;
	}

	public void setEndTime(Timestamp endTime) {
		EndTime = endTime;
	}

	public List<BorrowInfoDto> getBorrowInfoDtos() {
		return borrowInfoDtos;
	}

	public void setBorrowInfoDtos(List<BorrowInfoDto> borrowInfoDtos) {
		this.borrowInfoDtos = borrowInfoDtos;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	//图书馆信息
	private LibraryInfo inf;

		public LibraryInfo getInf() {
		return inf;
	}

	public BorrowInfo getBorrowInfo() {
			return borrowInfo;
		}

		public void setBorrowInfo(BorrowInfo borrowInfo) {
			this.borrowInfo = borrowInfo;
		}

		public List<BorrowInfo> getBorrowInfos() {
			return borrowInfos;
		}

		public void setBorrowInfos(List<BorrowInfo> borrowInfos) {
			this.borrowInfos = borrowInfos;
		}

	public BookInfoDtoByUser getBookInfoDto() {
			return bookInfoDto;
		}

		public void setBookInfoDto(BookInfoDtoByUser bookInfoDto) {
			this.bookInfoDto = bookInfoDto;
		}

	public BookTypeDto getBookTypeDto() {
			return bookTypeDto;
		}

		public void setBookTypeDto(BookTypeDto bookTypeDto) {
			this.bookTypeDto = bookTypeDto;
		}

		public PageInfo getPageInfo() {
			return pageInfo;
		}

		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}

	public List<List<BookTypeDto>> getBookTypeDtosList() {
			return bookTypeDtosList;
		}

		public void setBookTypeDtosList(List<List<BookTypeDto>> bookTypeDtosList) {
			this.bookTypeDtosList = bookTypeDtosList;
		}

	public String getOldPasswd() {
			return oldPasswd;
		}

		public void setOldPasswd(String oldPasswd) {
			this.oldPasswd = oldPasswd;
		}

		public String getNewPasswd() {
			return newPasswd;
		}

		public void setNewPasswd(String newPasswd) {
			this.newPasswd = newPasswd;
		}

		public String getNewpasswdagain() {
			return newpasswdagain;
		}

		public void setNewpasswdagain(String newpasswdagain) {
			this.newpasswdagain = newpasswdagain;
		}

	public void setInf(LibraryInfo inf) {
		this.inf = inf;
	}

		public IUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
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
