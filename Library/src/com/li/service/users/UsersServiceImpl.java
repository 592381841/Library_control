package com.li.service.users;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.li.common.domain.BookInfo;
import com.li.common.domain.BorrowInfo;
import com.li.common.domain.BorrowOperation;
import com.li.common.domain.Reader;
import com.li.common.dto.BorrowInfoDto;
import com.li.common.utils.MD5Encrypt;
import com.li.common.utils.PropertiesUtils;
import com.li.dao.users.IUsersDao;

public class UsersServiceImpl implements IUsersService {
private IUsersDao usersDao;

public IUsersDao getUsersDao() {
	return usersDao;
}

public void setUsersDao(IUsersDao usersDao) {
	this.usersDao = usersDao;
}

@Override
public void txmodify_status(String id) {
	Reader r=usersDao.findByObject("from Reader r where r.account = ?", id);
	r.setRisStatus(false);
	usersDao.save(r);
}
public boolean txmodify_passwd(String id,String passwd,String oldPasswd) throws NoSuchAlgorithmException{
	Reader r=usersDao.findByObject("from Reader r where r.account = ?", id);
	if(MD5Encrypt.validatePassword(oldPasswd,r.getPasswd())){
		r.setPasswd(MD5Encrypt.encryptByMD5(passwd));
		usersDao.save(r);
		return true;
	}
	return false;
}

@Override
public <T> List<T> findALLDomain(Class clazz) throws Exception {
return	usersDao.findObjectsList(clazz);
	
	
}

@Override
public List<BookInfo> findPageBookInfo(String bookTypeID, int maxResult, int pageStart) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("bookType.botyId", bookTypeID);
	map.put("bookIsDelete", false);
	return usersDao.findPageBookInfoByCondition(pageStart, maxResult, map, null,null );
}

@Override
public int countPageBookInfo(String bookTypeID, int maxResult) {
	Map<String,Object> map=new HashMap<String,Object>();
	map.put("bookType.botyId", bookTypeID);
	map.put("bookIsDelete", false);

	return usersDao.countPageBookInfoByCondition( maxResult, map);
}





@Override
public <T> List<T> findKeyBookInfo(Class clazz, Class<T> fieldclazz,String fieldName,Object fieldValue,Map<String, Object> map) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
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
	builder.append(" and obj.bookrack.bookrackId  NOT IN (1,2)");
	l= usersDao.findObjects(builder.toString(), obj.toArray());
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
	return usersDao.findPageBookInfoByCondition(page, maxResult, map, null, null);
	
}
public int countBookInfoBykey(String fieldName,Object fieldValue,int maxResult){
	Map<String,Object> map =new HashMap<String,Object>();
	map.put(fieldName, fieldValue);
	map.put("bookIsDelete", false);
	return usersDao.countPageBookInfoByCondition( maxResult, map);
}

@Override
public List<BookInfo> findAnBookInfo(Object obj) throws Exception {
	// TODO Auto-generated method stub
	List<BookInfo> li=null;
	if(obj instanceof String){
		li= usersDao.findObjectWithField(BookInfo.class, "bookId", obj);
	}else if(obj instanceof Integer){
		li= usersDao.findObjectWithField(BookInfo.class, "id", obj);
	}
	else{
		throw new Exception("findAnBookInfo方法参数有误");
	}
if(li==null)return null;
return li;
}

@Override
public BorrowInfo findAnBorrowInfo(String[] fieldName, Object[] fieldValue) throws Exception {
	// TODO Auto-generated method stub
	List<BorrowInfo> li=usersDao.findObjectWithFieldOrderByFields(BorrowInfo.class, fieldName, fieldValue, null, null);
	if(li==null||li.size()==0)return null;
	return li.get(0);
}

@Override
public List<BorrowOperation> findBorrowOperation(String readerId,Date[] date,int pageStart,Boolean operationType) throws IOException{
	int pageSize = PropertiesUtils.getSetting_page_result();
	if(operationType==null){
		
		Object[] con={readerId,new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
		return usersDao.findPageObject("from BorrowOperation b where b.borrowInfo.readerId = ? and b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageStart, pageSize, con);
	}
	Object[] con={readerId,operationType , new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
	return usersDao.findPageObject("from BorrowOperation b where  b.borrowInfo.readerId = ? and b.operationType = ? and b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageStart, pageSize, con);

	
	
}
@Override
public int countBorrowOperation(String readerId,Date[] date,Boolean operationType) throws Exception{
	int pageSize = PropertiesUtils.getSetting_page_result();

	if(operationType==null){
		
		Object[] con={readerId,new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
			return usersDao.countPage("select count(*) from BorrowOperation b where  b.borrowInfo.readerId = ? and b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC", pageSize, con);
	}else{
		Object[] con={readerId,operationType , new Timestamp(date[0].getTime()) , new Timestamp(date[1].getTime())};
		return usersDao.countPage("select count(*) from BorrowOperation b where  b.borrowInfo.readerId = ? and b.operationType = ? and  b.operationTime >= ? and b.operationTime <= ? order by operationTime DESC",  pageSize, con);
		
	}
	
}

@Override
public List<BorrowInfo> findBorrowInfoByStatus(String readId, int borrowStatus) {
	
	Object[] obj={readId,borrowStatus};
	return usersDao.findObjects("from BorrowInfo b where b.readerId = ? and b.borrowStatus = ?", obj);
	
}

@Override
public boolean txRenewBook(String id) throws Exception {
	
List<BorrowInfo> infoli=	usersDao.findObjectWithField(BorrowInfo.class, "id", id);
BorrowInfo info=null;
if(infoli!=null ||infoli.size()>0){
	info = infoli.get(0);
	if(!info.getBoorowIsRenew()){
		info.setBoorowIsRenew(true);
		
		int day=Integer.valueOf(PropertiesUtils.getSettingProperties("borrowTime"));
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(info.getEndTime().getTime() );
		c.add(Calendar.DATE, day);
		info.setEndTime(new Timestamp(c.getTimeInMillis()));
		usersDao.save(info);
		return true;
	}else{
		return false;
	}
}
else{
	return false;
}
}

}
