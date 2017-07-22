package com.li.service.admin;

import java.awt.print.Book;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public interface IAdminService {
	/**
	 * 查询单个admin的信息
	 * @param account  admin的account
	 * @return  没有则返回null
	 */
	public LibraryAdmin findAnLibararyAdminInfo(String account);

	/**
	 * 停用/激活账号
	 * @param id
	 * @param b  开启为true  关闭为false
	 * @param userType  用户类型  true为admin false为reader
	 */
	public void txmodify_status(String id,boolean b,boolean userType);
	/**
	 * 批量停用激活用户
	 * @param account  account集合
	 * @param myaccount 自己的account
	 * @param b  开启为true  关闭为false
	 * @param userType  用户类型  true为admin false为reader
	 */
	public  Map<String,Object> txMoremodify_status(String[] account,String myaccount,boolean b,boolean userType);
	
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
	 * 分页查询管理员信息
	 * @param page  页码
	 * @param maxResult  每个最大行
	 * @param map  条件
	 * @param sortField 排序字段
	 * @param sortWay  排序方式
	 * @return
	 */
	public List<LibraryAdmin> findpageManagerList(int page,int maxResult,Map<String,Object> map,String sortField,String sortWay);
	/**
	 * 计算分页查询管理员总页数
	 * @param maxResult  每个最大行
	 * @param map  条件
	 * @return
	 */
	public int countPageManagerList(int maxResult, Map<String,Object> map);
	/***
	 * 删除用户（包括管理员和读者）
	 * @param userType true为admin false 为reader
	 * @param account  账号
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> txdelete_user(boolean userType, String account) throws Exception;

	/**
	 *  批量删除用户，自动忽略自己和root
	 * @param userType  类型  true为管理员  false为读者
	 * @param account   account集合
	 * @param myaccount	自己的account(读者直接给null)
	 * @return
	 */
	public Map<String,Object> txMoreDelete_user(boolean userType, String[] account,String myaccount);
	
	/**
	 * 修改admin信息
	 * @param dto
	 * @return
	 */
	public boolean txModify_admin_info(LibraryAdminDto dto);
	/**
	 * 添加admin信息
	 * @param l
	 * @return
	 */
	public void txAdd_admin_info(LibraryAdmin l);
	/**
	 * 分页查询书库
	 * @return
	 */
	public List<StackRoom> findpageStackRoomList(int page,int maxResult,Map<String,Object> map,String sortField,String sortWay);
	public int countPageStackRoomList(int maxResult, Map<String,Object> map);
	/**
	 * 保存书库
	 * @param s
	 */
	public void txSaveStackRoom(StackRoom s);
	/**
	 * 修改书库
	 * @param s
	 * @return
	 */
	public Map<String,Object> txModifyStackRoom(StackRoom s);
	/**
	 * 删除书库
	 * @param id
	 * @return
	 */
	public Map<String,Object> txDeleteStackRoom(int id);
	/**
	 * 查找书库
	 * @param id
	 * @return
	 */
	public StackRoom findStackRoom(int id);
	/**
	 * 分页查找书架
	 * @return
	 */
	public List<Bookrack> findPageBookrack(int id,int maxResult, int pageStart);
	/**
	 * 根据stackroom的id查看书架（不分页）
	 * @param id
	 * @return
	 */
	public List<Bookrack> findBookrackByStackRoomID(int id);
	/**
	 * 查看分页查询书架的总页数
	 * @param id
	 * @param maxResult
	 * @return
	 */
	public int countPageBookrack(int id,int maxResult);

	/**
	 * 修改书架
	 * @param id bookrack的id
	 * @param name  bookrack的name
	 * @return
	 */
	public  Map<String,Object> txmodifyBookrack(int id,String name);
	/**
	 * 删除书架
	 * @param id
	 * @return
	 */
	public Map<String,Object> txDeleteBookrack(int id);
	/**
	 * 添加书架
	 * @param dto
	 */
	public boolean txSaveBookRack(BookrackDto dto,int id);

	/**
	 * 保存borrowOperation
	 * @param borrowOperation
	 */
	public void txSaveBorrowOperation(BorrowOperation borrowOperation);
	/**
	 * 查看单个书架信息
	 * @param bookrackId
	 * @return
	 */
	public Bookrack findAnBookrack(int bookrackId);
	/**
	 * 分页查找书籍 根据书架号
	 * @param bookrackId 书架号
	 * @param maxResult 每页最大
	 * @param pageStart 开始页面
	 * @return
	 */
	public List<BookInfo> findPageBookInfo(int bookrackId,int maxResult, int pageStart);
	
	/**
	 * 计算分页查询书籍的总页数 根据书架号
	 * @param bookrackId
	 * @param maxResult
	 * @return
	 */
	public int countPageBookInfo(int bookrackId,int maxResult);
	
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
	 * 计算分页查询书籍的总页数 根据分类号
	 * @param bookTypeID
	 * @param maxResult
	 * @return
	 */
	
	public int countPageBookInfo(String bookTypeID, int maxResult);
	/**
	 * 下架书籍
	 * @param id
	 * @return
	 */
	public boolean txDeleteBookInfo(int id);
	/**
	 * 闲置书籍
	 * @param id
	 * @return
	 */
	public boolean txunuseBookInfo(int id);
	/**
	 * 根据id或者bookID查看某个/某种图书
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public List<BookInfo> findAnBookInfo(Object obj) throws Exception;
	/**
	 * 不加条件查看某个domain的所有对象
	 * @param <T>
	 * @return
	 * @throws Exception 
	 */
	public <T> List<T> findALLDomain(Class clazz) throws Exception;
	/**
	 * 通过id查询单个分类
	 * @param botyId
	 * @return
	 */
	public BookType findAnBookType( String botyId);
	/**
	 * 保存图书信息
	 * @param b
	 */
	public void txSaveBookInfo(BookInfo b);
	/**
	 * 删除图书分类
	 * @param btypeId
	 * @return
	 */
	public Map<String,Object> txDeleteBookType(String btypeId);
	/**
	 * 修改图书分类信息
	 * @param bookTypeDto
	 * @return
	 */
	public boolean txModifyBookType(BookTypeDto bookTypeDto);
	/**
	 * 添加新的图书类型
	 * @param name 类型名
	 */
	public void txSaveBookType(String name);
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
	public <T> List<T> findKeyDomain(Class clazz,Class<T> fieldclazz,String fieldName,Object fieldValue,Map<String,Object> map) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException;
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
	 * 分页查询读者
	 * @param maxResult
	 * @param pageStart
	 * @param map
	 * @return
	 */
	public List<Reader> findPageReader(Map<String,Object> map,int maxResult, int pageStart);
	/**
	 * 计算分页数目
	 * @param maxResult
	 * @param map
	 * @return
	 */
	public int countPageReader(int maxResult,Map<String,Object> map);
	/**
	 *  * 查找单个reader
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Reader findAnReader(String fieldName,Object fieldValue);
	/**
	 * 保存
	 * @param r
	 * @throws Exception 
	 */
	public void txAddReader(Reader r) throws Exception;
	
	/**
	 * 查看单个读者类型
	 * @param id
	 * @return
	 */
	public ReaderType findAnReaderType(int id);
	/**
	 * 添加读者类型
	 * @param r
	 */
	public void txAddReaderType(ReaderType r);
	/**
	 * 修改读者类型
	 * @param r
	 */
	public Map<String, Object> txModifyReaderType(ReaderTypeDto r);
	/**
	 * 删除读者类型
	 * @param id
	 */
	public Map<String, Object> txDeleteReaderType(int id);
	

	/**
	 * 关键字分页搜索
	 * @param fieldName
	 * @param fieldValue
	 * @param page
	 * @param maxResult
	 * @return
	 */
	public List<Reader> findReaderInfoBykey(String fieldName,Object fieldValue,int page,int maxResult);
		/**
		 * 计算关键字分页搜索读者的页数
		 * @param fieldName
		 * @param fieldValue
		 * @param maxResult
		 * @return
		 */
		public int countReaderInfoBykey(String fieldName,Object fieldValue,int maxResult);
		/**
		 * 修改读者
		 * @param account   读者account
		 * @param typeId	读者类型id
		 * @return
		 */
		public boolean txModifyReader(ReaderDto readerDto);
		/**
		 * 重置读者密码
		 * @param account 读者account
		 * @return   
		 * @throws Exception 
		 */
		public boolean txModifyReaderPasswd(String account) throws Exception;
		
		/**
		 * 添加读者类型
		 * @param r
		 * @return
		 */
		public void txSaveReaderType(ReaderTypeDto r);
		
		
		

		
		/**
		 * 查询单个borrow【指定borrow的id】
		 * @param id borrow的id
		 * @return
		 * @throws Exception
		 */
		public BorrowInfo findAnBorrowInfoById(String id) throws Exception;

		/***
		 * 保存borrowinfo
		 * @param b
		 */
		public void txSaveBorrowInfo(BorrowInfo b);
		
		/**
		 * 查询多个borrowinfo
		 * @param fieldNames
		 * @param values
		 * @param orderField
		 * @param orderFieldValue
		 * @return
		 * @throws Exception 
		 */
		public List<BorrowInfo> findBorrowInfo(String[] fieldNames,Object[] values,String orderField,Object orderFieldValue) throws Exception;
		/***
		 * 根据操作查看borrowinfo
		 * @param date 时间段
		 * @param operationType 类型 true 为借书  flase为还书
		 * @return
		 */
		public List<BorrowInfo> findBorrowInfo(Date[] date,Boolean operationType);
		
		/***
		 * 根据操作分页borrowOpertation
		 * @param date
		 * @param operationType
		 * @return
		 * @throws IOException 
		 */
		public List<BorrowOperation> findBorrowOperation(Date[] date,int pageStart,Boolean operationType) throws IOException;
		/**
		 *  根据操作分页borrowOpertation
		 * @param date
		 * @param operationType
		 * @return
		 * @throws Exception
		 */
		public int countBorrowOperation(Date[] date,Boolean operationType) throws Exception;
	
		/**
		 * 计算当前用户未归还图书数目
		 * @param reader
		 * @return
		 */
		public Long countBorrowInfoByStatusOneORthreeAndByReader(String reader);
		/**
		 * 根据状态和用户account，分页查找borrowinfo
		 * @param page
		 * @param maxResult
		 * @param readID
		 * @param status
		 * @return
		 * @throws Exception 
		 */
		public List<BorrowInfo> findPageBorrowInfo(int page ,String readID,Integer status) throws Exception;

		/**
		 * 计算【根据状态和用户account，分页查找borrowinfo】
		 * @param maxResult
		 * @param readID
		 * @param status
		 * @return
		 * @throws Exception 
		 */
		public int countPageBorrowInfo(String readID,Integer status) throws Exception;
	
}
