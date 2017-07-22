package com.li.service.admin;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.li.common.domain.BookInfo;
import com.li.common.domain.BookType;
import com.li.common.domain.Bookrack;
import com.li.common.domain.BorrowInfo;
import com.li.common.domain.BorrowOperation;
import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.domain.ReaderType;
import com.li.common.domain.StackRoom;
import com.li.common.dto.BookTypeDto;
import com.li.common.dto.BookrackDto;
import com.li.common.dto.LibraryAdminDto;
import com.li.common.dto.ReaderDto;
import com.li.common.dto.ReaderTypeDto;
import com.li.common.utils.MD5Encrypt;
import com.li.common.utils.PropertiesUtils;
import com.li.common.utils.ShortUUID;
import com.li.dao.admin.IAdminDao;

public class AdminServiceImpl implements IAdminService {
private IAdminDao adminDao;

public IAdminDao getAdminDao() {
	return adminDao;
}

public void setAdminDao(IAdminDao adminDao) {
	this.adminDao = adminDao;
}


@Override
public void txmodify_status(String id,boolean b,boolean userType) {
	if(userType){
		LibraryAdmin r=adminDao.findByObject("from LibraryAdmin r where r.account = ?", id);
		r.setAisStatus(b);
		adminDao.save(r);
	}else{
		Reader r=adminDao.findByObject("from Reader r where r.account = ?", id);
		r.setRisStatus(b);
		adminDao.save(r);
	}
	
}
public Map<String,Object> txMoremodify_status(String[] account,String myaccount,boolean b,boolean userType){
	Map<String,Object> map=new HashMap<String, Object>();
	if(userType){
	for(String str :account){
		if(str.equals(myaccount))continue;
		List<LibraryAdmin> l=	adminDao.findObjectWithField(LibraryAdmin.class, "account",str);
		if(l==null){
			map.put("result", false);
			if(b){
				map.put("info", "批量激活失败，由于您的参数有误");
			}else{
				map.put("info", "批量停用失败，由于您的参数有误");
			}
			
			return map;	
		}
		else{
			if(l.get(0).getAisRoot())continue;
		l.get(0).setAisStatus(b);
		adminDao.save(l.get(0));
		
		}
	}
	map.put("result", true);
	if(b){
		map.put("info", "批量激活成功");
	}else{
		map.put("info", "批量停用成功");
	}
	
	}
	else{
		for(String str :account){
			List<Reader> l=	adminDao.findObjectWithField(Reader.class, "account",str);
			if(l==null){
				map.put("result", false);
				if(b){
					map.put("info", "批量激活失败，由于您的参数有误");
				}else{
					map.put("info", "批量停用失败，由于您的参数有误");
				}
				
				return map;	
			}
			else{
			l.get(0).setRisStatus(b);
			adminDao.save(l.get(0));
			
			}
		}
		map.put("result", true);
		if(b){
			map.put("info", "批量激活成功");
		}else{
			map.put("info", "批量停用成功");
		}
	}
	return map;
	
}
public boolean txmodify_passwd(String id,String passwd,String oldPasswd) throws NoSuchAlgorithmException{
	LibraryAdmin r=adminDao.findByObject("from LibraryAdmin r where r.account = ?", id);
	if(MD5Encrypt.validatePassword(oldPasswd,r.getPasswd())){
		r.setPasswd(MD5Encrypt.encryptByMD5(passwd));
		adminDao.save(r);
		return true;
	}
	return false;
}

@Override
public List<LibraryAdmin> findpageManagerList(int page,int maxResult, Map<String,Object> map,String sortField,String sortWay) {
	return adminDao.findPageObject(LibraryAdmin.class, page, maxResult, map, sortField, sortWay);
}

@Override
public int countPageManagerList(int maxResult, Map<String,Object> map) {
	return adminDao.countPage(LibraryAdmin.class, maxResult, map);
	// TODO Auto-generated method stub
	 
}

@Override
public Map<String,Object> txdelete_user(boolean userType, String account) throws Exception {
	Map<String,Object> map=new HashMap<String, Object>();
	//删除管理员
	if(userType){
	List<LibraryAdmin> l=	adminDao.findObjectWithField(LibraryAdmin.class, "account",account);
	if(l==null){
		map.put("result", false);
		map.put("info", "找不到该管理员账号");
	}else{
		adminDao.deleteObject(l.get(0));
		map.put("result", true);
		map.put("info", "删除成功");
	}


	}else{
		Object[] values={account,3,1};
		List<BorrowInfo> bli =  adminDao.findObjects("from BorrowInfo b where b.readerId =  ? and b.borrowStatus = ? or b.borrowStatus = ?", values);
		if(bli!=null){
			if(bli.size()>0){
				map.put("result", false);
				map.put("info", "还有书籍未还,无法删除");
				return map;
			}
		}
		else{
			adminDao.delUpdateObjects("delete from BorrowInfo b where b.readerId = ? ",account);
		}

		List<Reader> r=adminDao.findObjectWithField(Reader.class,"account",account);
		if(r==null||r.size()==0){
			map.put("result", false);
			map.put("info", "参数有误");
			return map;
		}
		adminDao.deleteObject(r.get(0));
		map.put("result", true);
		map.put("info", "删除成功");
		//删除reader
	}
	
	return map;	
}
@Override
public Map<String,Object> txMoreDelete_user(boolean userType, String[] account,String myaccount){
	Map<String,Object> map=new HashMap<String, Object>();
	
	if(account==null ||account.length==0){
		map.put("result", false);
		map.put("info", "批量删除失败，由于您的参数有误");
		return map;
	}
	
	if(userType){
	for(String str :account){
		if(str.equals(myaccount))continue;
		List<LibraryAdmin> l=	adminDao.findObjectWithField(LibraryAdmin.class, "account",str);
		if(l==null){
			map.put("result", false);
			map.put("info", "批量删除失败，由于您的参数有误");
			return map;	
		}
		else{
			if(l.get(0).getAisRoot())continue;
			adminDao.deleteObject(l.get(0));
		
		}
	}
	map.put("result", true);
	map.put("info", "批量删除成功");
	}
	else{
		
		//检查account数组是否有效
		for(String str :account){
		
			List<Reader> l=	adminDao.findObjectWithField(Reader.class, "account",str);
			if(l==null){
				map.put("result", false);
				map.put("info", "批量删除失败，由于您的参数有误");
				return map;	
			}
			
		}
		//删除不符合条件的account元素
		List<String> accountlist = new ArrayList<String>();
		Collections.addAll(accountlist, account);
		boolean[] bflag=new boolean[accountlist.size()];
		boolean flag=false;
		for(int i=0;i<accountlist.size();i++){
			String ac=accountlist.get(i);
			Object[] values={ac,3,1};
			List<BorrowInfo> bli =  adminDao.findObjects("from BorrowInfo b where b.readerId =  ? and b.borrowStatus = ? or b.borrowStatus = ? ", values);
			if(bli!=null){
				
				if(bli.size()>0){
					flag=true;
					bflag[i]=true;
					
				}
			}
			
		}
		
		
		
		for(int i=0;i<accountlist.size();i++){
			List<String> useraccountlist = new ArrayList<String>();
			StringBuilder strb1=new StringBuilder("delete from Reader r where 1=1");
			StringBuilder strb2=new StringBuilder("delete from BorrowInfo b where 1=1");
			if(!bflag[i]){
				useraccountlist.add(accountlist.get(i));
				strb1.append(" and r.account = ? ");
				strb2.append(" and  b.readerId = ? ");
			}
			
			
			adminDao.delUpdateObjects(strb1.toString(), useraccountlist.toArray());
			adminDao.delUpdateObjects(strb2.toString(), useraccountlist.toArray());
		
		}
		if(flag){
			map.put("info", "批量删除成功，部分读者还没归还书籍无法删除");
		}
		else{
			map.put("info", "批量删除成功");
		}
		map.put("result", true);
		
		
	}
	return map;
	
}

@Override
public LibraryAdmin findAnLibararyAdminInfo(String account) {
	// TODO Auto-generated method stub
List<LibraryAdmin> li= adminDao.findObjectWithField(LibraryAdmin.class, "account", account);
if(li==null)return null;
return li.get(0);
}

@Override
public boolean txModify_admin_info(LibraryAdminDto dto) {
	List<LibraryAdmin> li= adminDao.findObjectWithField(LibraryAdmin.class, "account", dto.getAccount());
	if(li==null)return false;
//	
	LibraryAdmin admin=li.get(0);
	admin.setAdminName(dto.getAdminName());
	admin.setIdCard(dto.getIdCard());
	admin.setAsrJu(dto.getAsrJu());
	admin.setReaderJu(dto.getReaderJu());
	admin.setReadTypeJu(dto.getReadTypeJu());
	admin.setLibJu(dto.getLibJu());
	adminDao.save(admin);

	return true;
}

@Override
public void txAdd_admin_info(LibraryAdmin l) {
	adminDao.save(l);
//	return false;
}

@Override
public List<StackRoom> findpageStackRoomList(int page, int maxResult, Map<String, Object> map, String sortField,
		String sortWay) {
	return adminDao.findPageObject(StackRoom.class, page, maxResult, map, sortField, sortWay);
}


@Override
public void txSaveStackRoom(StackRoom s) {
	Bookrack b=new Bookrack();
	b.setBookrName("默认书架1");
	b.setBrAddBookNumber(0);
	b.setStackRoom(s);
	Set<Bookrack> bset=new HashSet<Bookrack>();
	bset.add(b);
	s.setBookracks(bset);
	
	adminDao.save(s);
	// TODO Auto-generated method stub
	
}

@Override
public List<Bookrack> findPageBookrack(int id,int maxResult, int pageStart) {
	return adminDao.findPageObject("from Bookrack b where b.stackRoom.id = ? order by b.bookrackId desc", pageStart, maxResult, id);
}


@Override
public Map<String,Object> txModifyStackRoom(StackRoom s) {
	Map<String,Object> map=new HashMap<String, Object>();
	List<StackRoom> l=adminDao.findObjectWithField(StackRoom.class, "id", s.getId());
	if(l==null){
		map.put("info", "找不到该书库");
		map.put("result", false);
		return map;
	}
	else if(l.get(0).getId()==1||l.get(0).getId()==2){
		StackRoom q=l.get(0);
			map.put("info", "特殊书库无法修改");
			map.put("result", false);
			return map;
	}
	else{
		StackRoom stackRoom=l.get(0);
		stackRoom.setSrAddress(s.getSrAddress());
		stackRoom.setSrName(s.getSrName());
		adminDao.save(stackRoom);
		map.put("info", "修改成功");
		map.put("result", true);
		return map;
	}
	
	
	
}

@Override
public Map<String, Object> txDeleteStackRoom(int id) {
	Map<String,Object> map=new HashMap<String, Object>();
	List<StackRoom> sl=adminDao.findObjectWithField(StackRoom.class, "id", id);
	if(sl==null){
		map.put("info", "找不到该书库");
		map.put("result", false);
		return map;
	}
	else{
		
		StackRoom s=sl.get(0);
		if(s.getId()==1||s.getId()==2){
			map.put("info", "特殊书库无法删除");
			map.put("result", false);
			return map;
		}
		else if(s.getBookracks().size()!=0){
			map.put("info", "该书库有书架，无法删除");
			map.put("result", false);
			return map;
		}else{
			adminDao.deleteObject(s);
			map.put("info", "删除成功");
			map.put("result", true);
			return map;
		}
	}

	
}

@Override
public int countPageStackRoomList(int maxResult, Map<String, Object> map) {
	return adminDao.countPage(StackRoom.class, maxResult, map);
}

@Override
public int countPageBookrack(int id, int maxResult) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("stackRoom.id", id);
	
	return adminDao.countPage(Bookrack.class, maxResult, map);
}

@Override
public Map<String,Object> txmodifyBookrack(int id,String name) {
	Map<String ,Object> map=new HashMap<String,Object>();
	List<Bookrack> l=adminDao.findObjectWithField(Bookrack.class, "bookrackId",id);
	if(l==null){
		map.put("info", "找不到该书架");
		map.put("result", false);
		return map;
	}else{
		Bookrack bookrack=l.get(0);
		if(bookrack.getStackRoom().getId()==1 ||bookrack.getStackRoom().getId()==2){
			map.put("info", "特殊书架无法修改");
			map.put("result", false);
			return map;
		}
		else{
			bookrack.setBookrName(name);
			adminDao.save(bookrack);
			map.put("info", "修改成功");
			map.put("result", true);
			return map;
		}
		
		
	}
	
	
}

@Override
public Map<String, Object> txDeleteBookrack(int id) {
	Map<String,Object> map=new HashMap<String, Object>();
	List<Bookrack> sl=adminDao.findObjectWithField(Bookrack.class, "id", id);
	if(sl==null){
		map.put("info", "找不到该书库");
		map.put("result", false);
		return map;
	}
	else{
		
		Bookrack s=sl.get(0);
		if(s.getStackRoom().getId()==1||s.getStackRoom().getId()==2){
			map.put("info", "特殊书架无法删除");
			map.put("result", false);
			return map;
		}
		else if(s.getBookInfos().size()!=0){
			map.put("info", "该书架有书，无法删除");
			map.put("result", false); 
			return map;
		}else{
			
			map.put("stackRoomId", s.getStackRoom().getId());
			adminDao.deleteObject(s);
			map.put("info", "删除成功");
			map.put("result", true);
			return map;
		}
}
}

@Override
public boolean txSaveBookRack(BookrackDto dto,int id) {
	
	Bookrack r=new Bookrack();
	List<StackRoom> l=adminDao.findObjectWithField(StackRoom.class, "id", id);
	if(l==null||l.size()==0){
		return false;
	}
	r.setBookrName(dto.getBookrName());
	r.setBrAddBookNumber(0);
	r.setStackRoom(l.get(0));
	adminDao.save(r);
	return true;
}

@Override
public StackRoom findStackRoom(int id) {

	List<StackRoom> l=adminDao.findObjectWithField(StackRoom.class, "id", id);
	if(l==null||l.size()==0)return null;
	return l.get(0);
}

@Override
public List<BookInfo> findPageBookInfo(int bookrackId, int maxResult, int pageStart) {
	return adminDao.findPageObject("from BookInfo b where b.bookrack.bookrackId = ?", pageStart, maxResult, bookrackId);
}


@Override
public int countPageBookInfo(int bookrackId, int maxResult) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("bookrack.bookrackId", bookrackId);
	
	return adminDao.countPage(BookInfo.class, maxResult, map);
}

@Override
public List<BookInfo> findPageBookInfo(String bookTypeID, int maxResult, int pageStart) {
	return adminDao.findPageObject("from BookInfo b where b.bookType.botyId = ? and b.bookIsDelete = false", pageStart, maxResult, bookTypeID);
}


@Override
public int countPageBookInfo(String bookTypeID, int maxResult) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("bookType.botyId", bookTypeID);
	map.put("bookIsDelete", false);
	return adminDao.countPage(BookInfo.class, maxResult, map);
}



@Override
public Bookrack findAnBookrack(int bookrackId) {
	List<Bookrack> l=adminDao.findObjectWithField(Bookrack.class, "id", bookrackId);
	if(l==null||l.size()==0)return null;
	return l.get(0);
}

@Override
public <T> List<T> findALLDomain(Class clazz) throws Exception {
return	adminDao.findObjectsList(clazz);
	
	
}

@Override
public List<Bookrack> findBookrackByStackRoomID(int id) {
	return adminDao.findObjectWithField(Bookrack.class, "stackRoom.id", id);
}

@Override
public BookType findAnBookType(String botyId) {
	List<BookType> l=adminDao.findObjectWithField(BookType.class, "botyId", botyId);
	if(l==null||l.size()==0)return null;
	return l.get(0);
}

@Override
public void txSaveBookInfo(BookInfo b) {
	// TODO Auto-generated method stub
	adminDao.save(b);
}

@Override
public void txSaveBorrowInfo(BorrowInfo b) {
	// TODO Auto-generated method stub
	adminDao.save(b);
}

@Override
public boolean txDeleteBookInfo(int id) {
List<BookInfo> bookInfoList=adminDao.findObjectWithField(BookInfo.class,"id", id);
if(bookInfoList==null||bookInfoList.size()==0){
	return false;
}
BookInfo in=bookInfoList.get(0);
long l=adminDao.findByObject("select  COUNT(*)  from BorrowInfo b where b.bookId = ? and b.borrowStatus = 1 ", in.getId());
if(l!=0){
	return false;
}
in.getBookrack().getBookInfos().remove(in);
Bookrack r=adminDao.findObjectWithField(Bookrack.class	,"bookrackId", 2).get(0);
in.setBookrack(r);
in.setBookrackNumber(0);
in.setBookIsDelete(true);
adminDao.save(in);
return true;

}

@Override
public boolean txunuseBookInfo(int id) {
	List<BookInfo> bookInfoList=adminDao.findObjectWithField(BookInfo.class,"id", id);
	if(bookInfoList==null||bookInfoList.size()==0){
		return false;
	}
	
	
	BookInfo in=bookInfoList.get(0);
	long l=adminDao.findByObject("select  COUNT(*)  from BorrowInfo b where b.bookId = ? and b.borrowStatus = 1 ", in.getId());
	if(l!=0){
		return false;
	}
	in.getBookrack().getBookInfos().remove(in);
	Bookrack r=adminDao.findObjectWithField(Bookrack.class	,"bookrackId", 1).get(0);
	in.setBookrack(r);
	in.setBookrackNumber(0);
	adminDao.save(in);
	return true;
}

@Override
public List<BookInfo> findAnBookInfo(Object obj) throws Exception {
	// TODO Auto-generated method stub
	List<BookInfo> li=null;
	if(obj instanceof String){
		li= adminDao.findObjectWithField(BookInfo.class, "bookId", obj);
	}else if(obj instanceof Integer){
		li= adminDao.findObjectWithField(BookInfo.class, "id", obj);
	}
	else{
		throw new Exception("findAnBookInfo方法参数有误");
	}
if(li==null)return null;
return li;
}

public Map<String,Object> txDeleteBookType(String btypeId){
	Map<String,Object> map=new HashMap<String,Object>();
	List<BookType> l=adminDao.findObjectWithField(BookType.class, "botyId", btypeId);
	if(l!=null){
		BookType b=l.get(0);
		if(b.getBookInfos().size()!=0){
			map.put("info", "删除失败，该分类有书");
			map.put("result", false);
			return map;
		}
		adminDao.deleteObject(b);
		map.put("info", "删除成功");
		map.put("result", true);
		return map;
		
	}
	map.put("info", "参数有误");
	map.put("result", false);
	return map;
	}
public boolean txModifyBookType(BookTypeDto bookTypeDto){
	List<BookType> l=adminDao.findObjectWithField(BookType.class, "botyId", bookTypeDto.getBotyId());
	if(l!=null){
		BookType b=l.get(0);
		b.setBookTypeName(bookTypeDto.getBookTypeName());
		adminDao.save(b);
		return true;
	}
	return false;
}
public void txSaveBookType(String name){
	BookType b=new BookType();
	b.setBotyId(ShortUUID.generateShortUuid());
	b.setBookTypeName(name);
	adminDao.save(b);
}

@Override
public <T> List<T> findKeyDomain(Class clazz, Class<T> fieldclazz,String fieldName,Object fieldValue,Map<String, Object> map) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
	StringBuilder builder=new StringBuilder("from");
	List<Object> l=null;
	builder.append(" "+clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+" obj where 1=1");
	builder.append("  and obj."+fieldName+" LIKE ? ");
	List<String> keyList = new ArrayList<String>();
	List<Object> obj= new ArrayList<Object>();
	List<T> resultList=new ArrayList<T>(); 
	obj.add(fieldValue+"%");
	if(map!=null){
		keyList.addAll(map.keySet());
		for(String key :keyList){
			builder.append("  and obj."+key+"= ? ");
		}
		for(String key : keyList){
			obj.add(map.get(key));
			
		}
		
		
	}
	l= adminDao.findObjects(builder.toString(), obj.toArray());
	if(l!=null){
		for(Object o : l){
			Field field=clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			
			resultList.add((T) field.get(o));
			
		}
	}
	return resultList;
	
	
	
}
public List<BookInfo> findBookInfoBykey(String fieldName,Object fieldValue,int page,int maxResult){
Map<String,Object> map =new HashMap<String,Object>();
map.put(fieldName, fieldValue);
map.put("bookIsDelete", false);
	return adminDao.findPageObject(BookInfo.class, page, maxResult, map, null, null);
	
}
public int countBookInfoBykey(String fieldName,Object fieldValue,int maxResult){
	Map<String,Object> map =new HashMap<String,Object>();
	map.put(fieldName, fieldValue);
	map.put("bookIsDelete", false);
	return adminDao.countPage(BookInfo.class, maxResult, map);
}

@Override
public List<Reader> findPageReader(Map<String,Object> map,int maxResult, int pageStart) {
	return adminDao.findPageObject(Reader.class, pageStart, maxResult, map, "id", "desc");
	
	
}

@Override
public int countPageReader(int maxResult,Map<String,Object> map) {
	// TODO Auto-generated method stub
	return adminDao.countPage(Reader.class, maxResult, map);
}

@Override
public void txAddReader(Reader r) throws Exception {
	// TODO Auto-generated method stub
	String passwd = PropertiesUtils.getSettingProperties("defaultReaderPasswd");
	r.setPasswd(MD5Encrypt.encryptByMD5(passwd));
	adminDao.save(r);
	
}

@Override
public Reader findAnReader(String fieldName,Object fieldValue) {
	List<Reader> li=adminDao.findObjectWithField(Reader.class, fieldName, fieldValue);
	if(li!=null){
		return li.get(0);
	}
	return null;
}

@Override
public ReaderType findAnReaderType(int id) {
	
	return adminDao.findByObject("from ReaderType r where r.id = ?", id);	// TODO Auto-generated method stub
}

@Override
public void txAddReaderType(ReaderType r) {
	adminDao.save(r);	// TODO Auto-generated method stub
	
}

@Override
public Map<String,Object> txModifyReaderType(ReaderTypeDto r) {
	// TODO Auto-generated method stub
	Map<String,Object> map=new HashMap<String,Object>();
	ReaderType rt=adminDao.findByObject("from ReaderType r where r.id = ?", r.getId());
	if(rt!=null){
		
		rt.setBorrow(r.getBorrow());
		rt.setTypeName(r.getTypeName());
		adminDao.save(rt);
		map.put("result", true);
		map.put("info", "修改成功");
		
	}
	else{
		map.put("result", false);
		map.put("info", "参数错误，找不到该读者分类");
	}
	return map;
	
}

@Override
public Map<String,Object> txDeleteReaderType(int id) {
	Map<String,Object> map=new HashMap<String,Object>();
	ReaderType rt=adminDao.findByObject("from ReaderType r where r.id = ?",id);
	if(rt!=null){
		
		if(rt.getReaders().size()==0){
			map.put("info", "删除\""+rt.getTypeName()+"\"成功");
			adminDao.deleteObject(rt);
			map.put("result", true);
			
		}else{
			map.put("result", false);
			map.put("info", "该读者分类存在用户，无法删除");
		}
		
		
	
		
	}
	else{
	map.put("result", false);
	map.put("info", "参数错误，找不到该读者分类");
	}
	return map;
}

public List<Reader> findReaderInfoBykey(String fieldName,Object fieldValue,int page,int maxResult){
Map<String,Object> map =new HashMap<String,Object>();
map.put(fieldName, fieldValue);
	return adminDao.findPageObject(Reader.class, page, maxResult, map, null, null);
	
}
public int countReaderInfoBykey(String fieldName,Object fieldValue,int maxResult){
	Map<String,Object> map =new HashMap<String,Object>();
	map.put(fieldName, fieldValue);
	return adminDao.countPage(Reader.class, maxResult, map);
}

@Override
public boolean txModifyReader(ReaderDto readerDto) {
	List<Reader> rli=adminDao.findObjectWithField(Reader.class, "account", readerDto.getAccount());
	List<ReaderType> tli=adminDao.findObjectWithField(ReaderType.class, "id", readerDto.getReaderTypeDto().getId());
	if(rli==null ||tli==null){
		return false;	
	}
	Reader r=rli.get(0);
	r.setReaderType(tli.get(0));
	r.setIdCard(readerDto.getIdCard());
	r.setUsersName(readerDto.getUsersName());
	adminDao.save(r);
	return true;
	
}
@Override
public boolean txModifyReaderPasswd(String account) throws Exception {
	List<Reader> rli=adminDao.findObjectWithField(Reader.class, "account", account);
	if(rli==null){
		return false;	
	}
	Reader r=rli.get(0);
	String passwd = PropertiesUtils.getSettingProperties("defaultReaderPasswd");
	r.setPasswd(MD5Encrypt.encryptByMD5(passwd));
	adminDao.save(r);
	
	return true;
}

@Override
public void txSaveReaderType(ReaderTypeDto rtdto) {
	ReaderType rt=new ReaderType();
	rt.setBorrow(rtdto.getBorrow());
	rt.setTypeName(rtdto.getTypeName());
	adminDao.save(rt);
	
	}





@Override
public BorrowInfo findAnBorrowInfoById(String id) throws Exception {
	// TODO Auto-generated method stub
	return adminDao.findByObject("from BorrowInfo b where b.id = ?", id);
}

@Override
public List<BorrowInfo> findBorrowInfo(String[] fieldNames,Object[] values,String orderField,Object orderFieldValue) throws Exception {
	
	return	adminDao.findObjectWithFieldOrderByFields(BorrowInfo.class, fieldNames, values, orderField, orderFieldValue);
	 
}
@Override
public Long countBorrowInfoByStatusOneORthreeAndByReader(String reader) {
	// TODO Auto-generated method stub
	return adminDao.findByObject("select  COUNT(*)  from BorrowInfo b where b.readerId = ? and b.borrowStatus = 1", reader);
}


@Override
public List<BorrowInfo> findBorrowInfo(Date[] date, Boolean operationType) {
	if(operationType==null){
		Object[] con={new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
		return this.getAdminDao().findObjects("from BorrowInfo b where b.operationTime >= ? and b.operationTime <= ? ",con);
	}
	Object[] con={operationType , new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
	return this.getAdminDao().findObjects("from BorrowInfo b where b.operationType = ? and b.operationTime >= ? and b.operationTime <= ? ",con );
}
@Override
public List<BorrowOperation> findBorrowOperation(Date[] date,int pageStart,Boolean operationType) throws IOException{
	int pageSize = PropertiesUtils.getSetting_page_result();
	if(operationType==null){
		
		Object[] con={new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
		return adminDao.findPageObject("from BorrowOperation b where b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageStart, pageSize, con);
	}
	Object[] con={operationType , new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
	return adminDao.findPageObject("from BorrowOperation b where b.operationType = ? and b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageStart, pageSize, con);

	
	
}
public int countBorrowOperation(Date[] date,Boolean operationType) throws Exception{
	int pageSize = PropertiesUtils.getSetting_page_result();

	if(operationType==null){
		
		Object[] con={new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
			return adminDao.countPage("select count(*) from BorrowOperation b where b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageSize, con);
	}else{
		Object[] con={operationType , new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
		return adminDao.countPage("select count(*) from BorrowOperation b where  b.operationType = ? and  b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC",  pageSize, con);
		
	}
	
}


@Override
public void txSaveBorrowOperation(BorrowOperation borrowOperation) {
	// TODO Auto-generated method stub
	adminDao.save(borrowOperation);
	
}

@Override
public List<BorrowInfo> findPageBorrowInfo(int page, String readID, Integer status) throws Exception {
	int maxResult = PropertiesUtils.getSetting_page_result();
	// TODO Auto-generated method stub
	if(status!=0){
		Object[] condition={readID,status};
		return adminDao.findPageObject("from BorrowInfo b where b.readerId = ? and b.borrowStatus = ? ", page, maxResult, condition);	
	}else{
		Object[] condition={readID};
		return adminDao.findPageObject("from BorrowInfo b where b.readerId = ?  ", page, maxResult, condition);
	}
	
}

@Override
public int countPageBorrowInfo( String readID, Integer status) throws Exception {
	int maxResult = PropertiesUtils.getSetting_page_result();
	// TODO Auto-generated method stub
	if(status!=0){
		Object[] condition={readID,status};
		return adminDao.countPage("select count(*) from BorrowInfo b where b.readerId = ? and b.borrowStatus = ? ", maxResult, condition);	
	}else{
		Object[] condition={readID};
		return adminDao.countPage("select count(*) from BorrowInfo b where b.readerId = ?  ", maxResult, condition);
	}
	
}





}
