package com.li.action.users;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.li.common.domain.BookInfo;
import com.li.common.domain.BookType;
import com.li.common.domain.BorrowInfo;
import com.li.common.domain.BorrowOperation;
import com.li.common.domain.Reader;
import com.li.common.dto.BookInfoDto;
import com.li.common.dto.BookInfoDtoByUser;
import com.li.common.dto.BookTypeDto;
import com.li.common.dto.BorrowInfoDto;
import com.li.common.dto.BorrowOperationDto;
import com.li.common.dto.LibraryInfo;
import com.li.common.dto.PageInfo;
import com.li.common.dto.ReaderDto;
import com.li.common.utils.BeanUtilsEx;
import com.li.common.utils.PropertiesUtils;
import com.li.common.utils.TimeUtils;

public class UsersAction extends BaseUsersAction {
	
	/**
	 * 进入Library_info界面
	 * @return
	 * @throws IOException
	 */
	public String library_info() throws IOException{
		
		
		this.setInf(PropertiesUtils.getLibraryInfo());
		return FORWARD ;
		
	}
	/**
	 * 修改密码
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String modify_passwd() throws NoSuchAlgorithmException{
		String oldPasswd=this.getOldPasswd();
		String newPasswd =this.getNewPasswd();
		String newPasswdA=this.getNewpasswdagain();
		String account=((ReaderDto)this.getSession().get("reader")).getAccount();
		Map<String,Object> jsonMap=new HashMap<String,Object>(); 
		int len=newPasswd.trim().length();
		if(len>=8&&len<=16){
		if(newPasswd.equals(newPasswdA)){
			if(this.getUsersService().txmodify_passwd(account, newPasswd, oldPasswd)){
				jsonMap.put("info", "密码修改成功,现在进行注销");
				jsonMap.put("result", true);
			}
			else{
				jsonMap.put("info", "原密码错误。。。");
				jsonMap.put("result", false);
			}
			
		}
		else{
			jsonMap.put("info", "两次输入密码不一致。。。");
			jsonMap.put("result", false);
		}
		
		}
		else{
			jsonMap.put("info", "密码应该在8~16位");
			jsonMap.put("result", false);
		}
		this.setJsonDataMap(jsonMap);
		
		return SUCCESS;
	}
	public String modify_is_status(){
	this.getUsersService().txmodify_status(	((ReaderDto)this.getSession().get("reader")).getAccount());
	this.getSession().remove("reader");
		return SUCCESS;
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------

	public String book_info() throws Exception{
		int ID=this.getBookInfoDto().getId();
		List<BookInfo> bookInfoList=this.getUsersService().findAnBookInfo(ID);
		if(bookInfoList==null){
			throw new Exception("前台界面传参有误");
		}
		BookInfo bookInfo=bookInfoList.get(0);
		
		BookInfoDtoByUser dto=new BookInfoDtoByUser();
		
		if(bookInfo.getBookrack().getBookrackId()==1){
			dto.setBookUnuse(true);
		}
		else{
			dto.setBookUnuse(false);
		}
		BookTypeDto btdto=new BookTypeDto();
		BeanUtilsEx.copyProperties(btdto, bookInfo.getBookType());
		BeanUtilsEx.copyProperties(dto,bookInfo );
		dto.setBookRackName(bookInfo.getBookrack().getBookrName());
		dto.setBookTypeDto(btdto);
		dto.setStackRoomName(bookInfo.getBookrack().getStackRoom().getSrName());
		this.setBookInfoDto(dto);
		if(dto.getBookIsBorrow()){
			String[] fieldName={"bookId","borrowStatus"};
			Object[] fieldValue={ID,1};
			BorrowInfo borrowInfo=this.getUsersService().findAnBorrowInfo(fieldName, fieldValue);
			if(borrowInfo!=null){
				this.setBorrowInfo(borrowInfo);
				
			}
		
		}
		
		//查看图书
		return FORWARD;
	}
/**
 * 进入目录查询图书页面
 * @return
 */
public String keyword_search(){
	this.getSession().remove("keySearchBookInfoCondition");
	return FORWARD;
}
/**
 * 进入type_book_search页面
 * @return
 * @throws Exception 
 */
public String type_search() throws Exception{
	List<BookType> l=this.getUsersService().findALLDomain(BookType.class);
	List<List<BookTypeDto>> BookTypedtosList=new ArrayList<List<BookTypeDto>>() ;
	
	int i=0;
	if(l!=null){
		int size=l.size();
		if(size%3!=0){
			size=size/3+1;
		}else{
			size=size/3;
		}
			for(int j=0;j<size;j++){
				List<BookTypeDto> dtos=new ArrayList<BookTypeDto>();
				int ij=0;
				
				for(;i<l.size();i++){
					ij++;
					BookType b=l.get(i);
					
					BookTypeDto dto =new BookTypeDto();
					BeanUtilsEx.copyProperties(dto, b);
					if(b.getBookInfos()!=null){
					dto.setSize(b.getBookInfos().size());
					}
					else{
						dto.setSize(0);
					}
					dtos.add(dto);
					if(ij==3){
						i++;
						break;
					}
						
					
				}
				BookTypedtosList.add(dtos);
				
			}
			this.setBookTypeDtosList(BookTypedtosList);
		
	
	}
	return FORWARD;
}

/**
 * 根据分类号分页加载图书
 * @return
 * @throws Exception 
 */
public String findBookInfoByBookType() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int page=this.getPageInfo().getPage();
	String id=this.getBookTypeDto().getBotyId();
	if(id==null ||id.trim().length()==0 ){
		throw new Exception("参数出错");
	}
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getUsersService().countPageBookInfo(id, maxResult));
	if(page>p.getCount()){
		p.setPage(p.getCount());
	}
	else{
		p.setPage(page);
	}

	mapjson.put("pageInfo",p);
List<BookInfo> lList=	this.getUsersService().findPageBookInfo(id, maxResult, p.getPage());
	if(lList==null ||lList.size()==0)
	{
	mapjson.put("bookInfoDtos", null);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	}

List<BookInfoDtoByUser> dtoList=new ArrayList<BookInfoDtoByUser>();
for(BookInfo r :lList){
	BookInfoDtoByUser dto=new BookInfoDtoByUser();
	
	if(r.getBookrack().getBookrackId()==1){
		dto.setBookUnuse(true);
	}
	else{
		dto.setBookUnuse(false);
	}
	
	BeanUtilsEx.copyProperties(dto,r );
	dto.setBookRackName(r.getBookrack().getBookrName());
	dto.setStackRoomName(r.getBookrack().getStackRoom().getSrName());
	dtoList.add(dto);
	
}
mapjson.put("bookInfoDtos", dtoList);
this.setJsonDataMap(mapjson);
return SUCCESS;
}
/**
 * 加载关联词汇
 * @return
 * @throws Exception 
 */
public String findKeyBookInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<String> jsonList=null;
	List<Integer> jsonList1=null;
	Map<String,Object> map =new HashMap<String,Object>();
	map.put("bookIsDelete", false);
	if(this.getBookInfoDto()==null){
		throw new Exception("参数错误");
	}
	BookInfoDtoByUser bdto=this.getBookInfoDto();
	if(bdto.getBookId()!=null &&bdto.getBookId().trim().length()!=0){
		jsonList =this.getUsersService().findKeyBookInfo(BookInfo.class, String.class, "bookId",bdto.getBookId(),map);
		
	}
	else if(bdto.getBookName()!=null &&bdto.getBookName().trim().length()!=0){
		jsonList =this.getUsersService().findKeyBookInfo(BookInfo.class, String.class, "bookName",bdto.getBookName(),map);
	}
	else if(bdto.getId()!=null &&bdto.getId()!=0){
		jsonList1 =this.getUsersService().findKeyBookInfo(BookInfo.class, Integer.class, "id",bdto.getId(),map);
	}
	else if(bdto.getBookWriter()!=null &&bdto.getBookWriter().trim().length()!=0){
		jsonList =this.getUsersService().findKeyBookInfo(BookInfo.class, String.class, "bookWriter",bdto.getBookWriter(),map);
	}
	
	if(jsonList!=null){
		HashSet<String> h=new HashSet<String>(jsonList);
		jsonList.clear();
		jsonList.addAll(h);
		mapjson.put("resultList", jsonList);
	}
	else if(jsonList1!=null){
		HashSet<Integer> h=new HashSet<Integer>(jsonList1);
		jsonList1.clear();
		jsonList1.addAll(h);
		mapjson.put("resultList", jsonList1);
		
	}
	
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	
}
/**
 * 根据关键字搜索图书
 * @return
 * @throws Exception 
 */
public String search_page_keyboardBookInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<BookInfo> bookInfos=null;
	PageInfo pageInfo=null;
	BookInfoDtoByUser bdto=null;
	int count=0;
	int maxResult=PropertiesUtils.getSetting_page_result();
	if(this.getBookInfoDto()==null){
		bdto=(BookInfoDtoByUser) this.getSession().get("keySearchBookInfoCondition");
		if(bdto==null){
			throw new Exception("参数错误");
		}
	}else{
		bdto=this.getBookInfoDto();
		this.getSession().put("keySearchBookInfoCondition", bdto);
	}
	if(this.getPageInfo()==null){
		pageInfo=new PageInfo();
		pageInfo.setPage(1);
	}else{
		pageInfo=this.getPageInfo();
	}
	
	
	if(bdto.getBookId()!=null &&bdto.getBookId().trim().length()!=0){
		bookInfos =this.getUsersService().findBookInfoBykey( "bookId",bdto.getBookId(), pageInfo.getPage(), maxResult);
		 count=this.getUsersService().countBookInfoBykey("bookId",bdto.getBookId(), maxResult);
	}
	else if(bdto.getBookName()!=null &&bdto.getBookName().trim().length()!=0){
		bookInfos =this.getUsersService().findBookInfoBykey("bookName",bdto.getBookName(), pageInfo.getPage(), maxResult);
		 count=this.getUsersService().countBookInfoBykey("bookName",bdto.getBookName(), maxResult);
	}
	else if(bdto.getId()!=null &&bdto.getId()!=0){
		bookInfos =this.getUsersService().findBookInfoBykey("id",bdto.getId(), pageInfo.getPage(), maxResult);
		 count=this.getUsersService().countBookInfoBykey("id",bdto.getId(), maxResult);
	}
	else if(bdto.getBookWriter()!=null &&bdto.getBookWriter().trim().length()!=0){
		bookInfos =this.getUsersService().findBookInfoBykey("bookWriter",bdto.getBookWriter(), pageInfo.getPage(), maxResult);
		 count=this.getUsersService().countBookInfoBykey("bookWriter",bdto.getBookWriter(), maxResult);
	}
	pageInfo.setCount(count);
	
	mapjson.put("pageInfo", pageInfo);
	

List<BookInfoDtoByUser> dtoList=new ArrayList<BookInfoDtoByUser>();
for(BookInfo r :bookInfos){
	BookInfoDtoByUser dto=new BookInfoDtoByUser();
	
	if(r.getBookrack().getBookrackId()==1){
		dto.setBookUnuse(true);
	}
	else{
		dto.setBookUnuse(false);
	}
	BeanUtilsEx.copyProperties(dto,r );
	BookTypeDto t=new BookTypeDto();
	BeanUtilsEx.copyProperties(t, r.getBookType());
	dto.setBookRackName(r.getBookrack().getBookrName());
	dto.setStackRoomName(r.getBookrack().getStackRoom().getSrName());
	dto.setBookTypeDto(t);
	dtoList.add(dto);
	
}
	
	
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	
	
	
	return SUCCESS;
	
}
	
	
/**
 * 进入all_borrow_history.jsp
 * @return
 * @throws Exception 
 */
public String borrow_history() throws Exception{
	
	Date[] dates=TimeUtils.getWholeDaybyToday(new Date());
	this.getSession().put("adminBeginTime",new Timestamp(dates[0].getTime()) );
	this.getSession().put("adminEndTime",new Timestamp(dates[1].getTime() ));
	this.getSession().put("operationType", null);
	List<BorrowOperation> borrowOperations =this.getUsersService().findBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates,1, null);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	PageInfo pageInfo = new PageInfo();
	pageInfo.setCount(this.getUsersService().countBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates, null));
	pageInfo.setPage(1);
	this.setPageInfo(pageInfo);
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getUsersService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			ReaderDto readerDto=(ReaderDto) this.getSession().get("reader");
			dto.setReaderDto(readerDto);
			borrowOperationDto.setBorrowInfoDto(dto);
			borrowOperationDtos.add(borrowOperationDto);
			
		}

		this.setBorrowOperationDtos(borrowOperationDtos);
	}
	
	return FORWARD;
}
/***
 * 分页加载OperationDtos
 * @return
 * @throws Exception 
 */
public String page_borrow_history() throws Exception{
	Map<String,Object> map=new HashMap<String,Object>();
	Timestamp begin=(Timestamp) this.getSession().get("adminBeginTime");
	Timestamp end =(Timestamp) this.getSession().get("adminEndTime");
	Boolean flag=(Boolean) this.getSession().get("operationType");
	PageInfo pageInfo = this.getPageInfo();
	
	if(begin == null ||end ==null||pageInfo ==null){
		throw new Exception("参数错误");
	}
	
	Date[] dates={begin,end};
	pageInfo.setCount(this.getUsersService().countBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates, flag));
	
	List<BorrowOperation> borrowOperations =this.getUsersService().findBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates,pageInfo.getPage(), flag);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getUsersService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			ReaderDto readerDto=(ReaderDto) this.getSession().get("reader");
			dto.setReaderDto(readerDto);
			borrowOperationDto.setBorrowInfoDto(dto);
			borrowOperationDtos.add(borrowOperationDto);
			
		}

		map.put("borrowOperationDtos", borrowOperationDtos);
	}else{
		map.put("borrowOperationDtos", null);
	}
	map.put("pageInfo", pageInfo);
	this.setJsonDataMap(map);
	return SUCCESS;
	
}
/***
 * 修改时间段查看borrowOperation history
 * @return
 * @throws Exception
 */
public String borrow_history_by_time() throws Exception{
	Map<String,Object> map=new HashMap<String,Object>();
	
	if(this.getBeginTime()==null ||this.getEndTime()==null){
	map.put("info", "参数错误");
	map.put("result", false);
	this.setJsonDataMap(map);
	return SUCCESS;
	}
	Timestamp begin = new Timestamp(TimeUtils.getWholeDaybyToday(new Date(this.getBeginTime().getTime()))[0].getTime());
	Timestamp end = new Timestamp(TimeUtils.getWholeDaybyToday(new Date(this.getEndTime().getTime()))[1].getTime());
	this.getSession().put("adminBeginTime",begin);
	this.getSession().put("adminEndTime",end);
	Boolean bool=(Boolean) this.getSession().get("operationType");
	Date[] dates={begin,end}; 
	PageInfo pageInfo = new PageInfo();
	pageInfo.setCount(this.getUsersService().countBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates, bool));
	pageInfo.setPage(1);
	map.put("pageInfo", pageInfo);
	List<BorrowOperation> borrowOperations =this.getUsersService().findBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates,1,bool);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getUsersService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			ReaderDto readerDto=(ReaderDto) this.getSession().get("reader");
			dto.setReaderDto(readerDto);
			borrowOperationDto.setBorrowInfoDto(dto);
			borrowOperationDtos.add(borrowOperationDto);
			
		}
	}

		map.put("result",true);
		map.put("borrowOperationDtos", borrowOperationDtos);
		this.setJsonDataMap(map);
	
	return SUCCESS;
}
/***
 * 修改类型查看borrowOperation history
 * @return
 * @throws Exception
 */
public String borrow_history_by_type() throws Exception{
Map<String,Object> map=new HashMap<String,Object>();
Timestamp begin=(Timestamp) this.getSession().get("adminBeginTime");
Timestamp end =(Timestamp) this.getSession().get("adminEndTime");
Boolean bool = null;
int boolInt=this.getOperationType();
switch (boolInt) {
case 1:
	bool=null;
	break;
case 2:
	bool=true;
	break;
case 3:
	bool=false;
	break;

default:
	break;
}	
this.getSession().put("operationType",bool);
	Date[] dates={begin,end}; 
	List<BorrowOperation> borrowOperations =this.getUsersService().findBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates,1,bool);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getUsersService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			ReaderDto readerDto=(ReaderDto) this.getSession().get("reader");
			dto.setReaderDto(readerDto);
			borrowOperationDto.setBorrowInfoDto(dto);
			borrowOperationDtos.add(borrowOperationDto);
			
		}
	}
	PageInfo pageInfo = new PageInfo();
	pageInfo.setCount(this.getUsersService().countBorrowOperation(((ReaderDto)this.getSession().get("reader")).getAccount(),dates, bool));
	pageInfo.setPage(1);
	map.put("pageInfo", pageInfo);
		map.put("borrowOperationDtos", borrowOperationDtos);
		this.setJsonDataMap(map);
	
	return SUCCESS;
	
	
} 



/**
 * 进入borrow_status.jsp
 * @return
 * @throws Exception
 */
	public String borrow_status() throws Exception{
		
		List<BorrowInfo> bli=this.getUsersService().findBorrowInfoByStatus(((ReaderDto)this.getSession().get("reader")).getAccount(), 1);
		List<BorrowInfoDto> bdtoli=new ArrayList<BorrowInfoDto>();
			if(bli!=null){
			for(BorrowInfo b :bli){
				BorrowInfoDto dto=new BorrowInfoDto();
				BeanUtilsEx.copyProperties(dto, b);
				List<BookInfo> bookInfos =this.getUsersService().findAnBookInfo(b.getBookId());
				
				if(bookInfos!=null){
					BookInfoDto bookInfoDto =new BookInfoDto();
					BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
					dto.setBookInfoDto(bookInfoDto);
				}
				ReaderDto readerDto=(ReaderDto) this.getSession().get("reader");
				dto.setReaderDto(readerDto);
				bdtoli.add(dto);
				
			}
			
			
			
		}
		
			
		this.setBorrowInfoDtos(bdtoli);	
		return FORWARD;
		
		
		
	}
	
	/**
	 * 续借
	 * @return
	 * @throws Exception
	 */
	public String renewBook() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		BorrowInfo b=this.getBorrowInfo();
		if(b==null){
			throw new Exception("参数错误");
		}
		String id=b.getId();
		if(this.getUsersService().txRenewBook(id)){
			map.put("result", true);
			map.put("info", "续借成功");
			
		}else{
			map.put("result", false);
			map.put("info", "续借失败");
		}
		this.setJsonDataMap(map);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	

}
