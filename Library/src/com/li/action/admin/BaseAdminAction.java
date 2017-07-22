package com.li.action.admin;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
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
import com.li.common.domain.StackRoom;
import com.li.common.dto.BookInfoDto;
import com.li.common.dto.BookTypeDto;
import com.li.common.dto.BookrackDto;
import com.li.common.dto.BorrowInfoDto;
import com.li.common.dto.BorrowOperationDto;
import com.li.common.dto.DownloadInformation;
import com.li.common.dto.LibraryAdminDto;
import com.li.common.dto.LibraryInfo;
import com.li.common.dto.PageInfo;
import com.li.common.dto.ReaderDto;
import com.li.common.dto.ReaderTypeDto;
import com.li.common.dto.StackRoomDto;
import com.li.service.admin.IAdminService;
import com.opensymphony.xwork2.ActionSupport;
public class BaseAdminAction extends ActionSupport implements ApplicationAware, RequestAware, SessionAware,ServletRequestAware,ServletResponseAware {
	public static String FORWARD ="forward";
	private Map<String,Object> request;
	private Map<String,Object> session;
	private Map<String,Object> application;
	private HttpServletRequest httpServletRequest;
	private HttpSession httpSession;
	private ServletContext servletContext;
	private HttpServletResponse httpServletResponse; 
	private Map<String,Object> jsonDataMap;
	private DownloadInformation downloadInformation;
	private IAdminService adminService;
	
	
	//修改密码
		private String  oldPasswd;
		private String newPasswd;
		private String  newpasswdagain;

		//激活停用用户
		private boolean modify_status;
		private String account;
		private boolean users_type;//true 为admin  false为用户
		private boolean is_me;  //是不是自己
		
		//图书馆信息
		private LibraryInfo libraryInfo;
		//分页资料
		private PageInfo pageInfo;
		
		//管理员列表
		private List<LibraryAdminDto> libraryAdminDto;
		//单个管理员信息
		private LibraryAdminDto adminDto;
		//多个用户（管理员/读者）的
		private String accountListStr;
		//书库列表
		private List<StackRoomDto> stackRoomDtos;
		private StackRoom stackRoom;
		//书架列表
		private List<BookrackDto> bookrackDtos;
		private BookrackDto bookrackDto;
		private Integer id;
		//书架详情页
		private List<BookInfoDto>	bookInfoDtos;
		//图书
		private BookInfoDto bookInfoDto;
		//书籍个数
		private int bookSum;
		private BookTypeDto bookTypeDto;
		private List<BookTypeDto> bookTypeDtos;
		private List<List<BookTypeDto>> bookTypeDtosList;
		//判断进入addOrModify_book.jsp是用于modify(false)还是add（true）
		private Boolean addOrmodify;
		//上传信息
		private String uploadFileFileName;
		private File uploadFile;	
		private String uploadFileContentType;
		//读者属性
		private List<ReaderDto> readerDtos;
		private ReaderDto readerDto;
		private List<ReaderTypeDto> readerTypeDtos;
		private ReaderTypeDto	readerTypeDto;
		//图片地址
		private String pic;
		//借阅记录
		private BorrowInfo borrowInfo;
		private List<BorrowInfo> borrowinfos;
		private List<BorrowInfoDto> borrowInfoDtos;
		private List<BorrowOperationDto> borrowOperationDtos;
		private Timestamp BeginTime;
		private Timestamp EndTime;
		private  int operationType;
		private int borrowStatus;
		


		public int getBorrowStatus() {
			return borrowStatus;
		}

		public void setBorrowStatus(int borrowStatus) {
			this.borrowStatus = borrowStatus;
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


		public int getOperationType() {
			return operationType;
		}

		public void setOperationType(int operationType) {
			this.operationType = operationType;
		}

		public List<BorrowOperationDto> getBorrowOperationDtos() {
			return borrowOperationDtos;
		}

		public void setBorrowOperationDtos(List<BorrowOperationDto> borrowOperationDtos) {
			this.borrowOperationDtos = borrowOperationDtos;
		}

		public List<BorrowInfoDto> getBorrowInfoDtos() {
			return borrowInfoDtos;
		}

		public void setBorrowInfoDtos(List<BorrowInfoDto> borrowInfoDtos) {
			this.borrowInfoDtos = borrowInfoDtos;
		}

		public String getPic() {
			return pic;
		}

		public BorrowInfo getBorrowInfo() {
			return borrowInfo;
		}

		public void setBorrowInfo(BorrowInfo borrowInfo) {
			this.borrowInfo = borrowInfo;
		}

		public List<BorrowInfo> getBorrowinfos() {
			return borrowinfos;
		}

		public void setBorrowinfos(List<BorrowInfo> borrowinfos) {
			this.borrowinfos = borrowinfos;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public List<ReaderTypeDto> getReaderTypeDtos() {
			return readerTypeDtos;
		}

		public void setReaderTypeDtos(List<ReaderTypeDto> readerTypeDtos) {
			this.readerTypeDtos = readerTypeDtos;
		}

		public ReaderTypeDto getReaderTypeDto() {
			return readerTypeDto;
		}

		public void setReaderTypeDto(ReaderTypeDto readerTypeDto) {
			this.readerTypeDto = readerTypeDto;
		}

		public List<List<BookTypeDto>> getBookTypeDtosList() {
			return bookTypeDtosList;
		}

		public void setBookTypeDtosList(List<List<BookTypeDto>> bookTypeDtosList) {
			this.bookTypeDtosList = bookTypeDtosList;
		}

		public String getUploadFileContentType() {
			return uploadFileContentType;
		}

		public void setUploadFileContentType(String uploadFileContentType) {
			this.uploadFileContentType = uploadFileContentType;
		}

		public String getUploadFileFileName() {
			return uploadFileFileName;
		}

		public File getUploadFile() {
			return uploadFile;
		}

		public void setUploadFile(File uploadFile) {
			this.uploadFile = uploadFile;
		}

		public void setUploadFileFileName(String uploadFileFileName) {
			this.uploadFileFileName = uploadFileFileName;
		}

		

		public String getAccountListStr() {
			return accountListStr;
		}

		public void setAccountListStr(String accountListStr) {
			this.accountListStr = accountListStr;
		}

		public BookInfoDto getBookInfoDto() {
			return bookInfoDto;
		}

		public void setBookInfoDto(BookInfoDto bookInfoDto) {
			this.bookInfoDto = bookInfoDto;
		}

		public BookTypeDto getBookTypeDto() {
			return bookTypeDto;
		}

		public void setBookTypeDto(BookTypeDto bookTypeDto) {
			this.bookTypeDto = bookTypeDto;
		}

		public Boolean getAddOrmodify() {
			return addOrmodify;
		}

		public void setAddOrmodify(Boolean addOrmodify) {
			this.addOrmodify = addOrmodify;
		}

		public List<BookTypeDto> getBookTypeDtos() {
			return bookTypeDtos;
		}

		public void setBookTypeDtos(List<BookTypeDto> bookTypeDtos) {
			this.bookTypeDtos = bookTypeDtos;
		}

		public List<BookInfoDto> getBookInfoDtos() {
			return bookInfoDtos;
		}

		public void setBookInfoDtos(List<BookInfoDto> bookInfoDtos) {
			this.bookInfoDtos = bookInfoDtos;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public List<BookrackDto> getBookrackDtos() {
			return bookrackDtos;
		}

		public void setBookrackDtos(List<BookrackDto> bookrackDtos) {
			this.bookrackDtos = bookrackDtos;
		}



		public BookrackDto getBookrackDto() {
			return bookrackDto;
		}

		public void setBookrackDto(BookrackDto bookrackDto) {
			this.bookrackDto = bookrackDto;
		}

		public StackRoom getStackRoom() {
			return stackRoom;
		}

		public void setStackRoom(StackRoom stackRoom) {
			this.stackRoom = stackRoom;
		}



		public List<StackRoomDto> getStackRoomDtos() {
			return stackRoomDtos;
		}

		public void setStackRoomDtos(List<StackRoomDto> stackRoomDtos) {
			this.stackRoomDtos = stackRoomDtos;
		}

		public LibraryAdminDto getAdminDto() {
			return adminDto;
		}

		public int getBookSum() {
			return bookSum;
		}

		public void setBookSum(int bookSum) {
			this.bookSum = bookSum;
		}

		public void setAdminDto(LibraryAdminDto adminDto) {
			this.adminDto = adminDto;
		}

		public PageInfo getPageInfo() {
			return pageInfo;
		}

		public List<LibraryAdminDto> getLibraryAdminDto() {
			return libraryAdminDto;
		}

		public void setLibraryAdminDto(List<LibraryAdminDto> libraryAdminDto) {
			this.libraryAdminDto = libraryAdminDto;
		}

		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}

		public LibraryInfo getLibraryInfo() {
			return libraryInfo;
		}

		public void setLibraryInfo(LibraryInfo libraryInfo) {
			this.libraryInfo = libraryInfo;
		}

		public boolean isIs_me() {
			return is_me;
		}

		public void setIs_me(boolean is_me) {
			this.is_me = is_me;
		}

		public boolean isIs_root() {
			return is_root;
		}

		public void setIs_root(boolean is_root) {
			this.is_root = is_root;
		}

		private boolean is_root;  //是否是root
		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public boolean isUsers_type() {
			return users_type;
		}

		public void setUsers_type(boolean users_type) {
			this.users_type = users_type;
		}

		
		
		
		public boolean isModify_status() {
			return modify_status;
		}

		public void setModify_status(boolean modify_status) {
			this.modify_status = modify_status;
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

		public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
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

		public List<ReaderDto> getReaderDtos() {
			return readerDtos;
		}

		public void setReaderDtos(List<ReaderDto> readerDtos) {
			this.readerDtos = readerDtos;
		}

		public ReaderDto getReaderDto() {
			return readerDto;
		}

		public void setReaderDto(ReaderDto readerDto) {
			this.readerDto = readerDto;
		}


}
