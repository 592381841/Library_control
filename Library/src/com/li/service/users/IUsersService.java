package com.li.service.users;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.li.common.domain.BookInfo;
import com.li.common.domain.BorrowInfo;
import com.li.common.domain.BorrowOperation;

public interface IUsersService {
	/**
	 * 停用账号
	 * @param id
	 */
	public void txmodify_status(String id);
	
	/**
	 * 修改密码
	 * @param id用户id
	 * @param passwd新密码
	 * @param oldPasswd老密码
	 * @return 修改成功为true  失败为false
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean txmodify_passwd(String id,String passwd,String oldPasswd) throws NoSuchAlgorithmException;
	/**
	 * 查看某个实体类的所有东西
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findALLDomain(Class clazz) throws Exception;

	/**
	 * 计算根据分类号分页查询图书的页数
	 * @param bookTypeID
	 * @param maxResult
	 * @return
	 */
	public int countPageBookInfo(String bookTypeID, int maxResult);

	/**
	 * 
	 * 分页查找书籍 根据分类号
	 * @param bookTypeID 书架号
	 * @param maxResult 每页最大
	 * @param pageStart 开始页面
	 * @return
	 */
	public List<BookInfo> findPageBookInfo(String bookTypeID, int maxResult, int pageStart);
	
	/**
	 * 获得联想搜索框所需要的东西（模糊）
	 * @param clazz  查询的实体类
	 * @param fieldclazz 所要得到的数据的泛型
	 * @param map  条件[规定]
	 * @param fieldName 所要得到数据的字段名
	 * @param fieldValue 所要得到数据的字段条件值
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public <T> List<T> findKeyBookInfo(Class clazz,Class<T> fieldclazz,String fieldName,Object fieldValue,Map<String,Object> map) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 * 根据字段查询图书
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public List<BookInfo> findBookInfoBykey(String fieldName,Object fieldValue,int page,int maxResult);
	/**
	 * 计算根据字段查询图书的总页数
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public int countBookInfoBykey(String fieldName,Object fieldValue,int maxResult);

	/**
	 * 根据id或者bookID查看某个/某种图书
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public List<BookInfo> findAnBookInfo(Object obj) throws Exception;
	
	/**
	 * 查询单个borrowinfo
	 * @param fieldName 字段名
	 * @param fieldValue 值
	 * @return
	 * @throws Exception 
	 */
	public BorrowInfo findAnBorrowInfo(String[] fieldName,Object[] fieldValue) throws Exception;
	/***
	 * 根据操作分页borrowOpertation
	 * @param date
	 * @param operationType
	 * @return
	 * @throws IOException 
	 */
	public List<BorrowOperation> findBorrowOperation(String readerId,Date[] date,int pageStart,Boolean operationType) throws IOException;
	/**
	 *  根据操作分页borrowOpertation
	 * @param date
	 * @param operationType
	 * @return
	 * @throws Exception
	 */
	public int countBorrowOperation(String readerId,Date[] date,Boolean operationType) throws Exception;
	/**
	 * 查询未归还借阅
	 * @param readId
	 * @param borrowStatus
	 * @return
	 */
	public List<BorrowInfo> findBorrowInfoByStatus(String readId,int borrowStatus);
	
	/**
	 * 续借
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public boolean txRenewBook(String id) throws Exception;
}
