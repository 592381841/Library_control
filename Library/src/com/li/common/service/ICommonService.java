package com.li.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import com.li.common.dao.ICommonDao;
import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.utils.VerifyCode;

public interface ICommonService {
	public InputStream getImage() throws IOException;
	public String getText();
	/**
	 * 管理员登录
	 * @param id
	 * @param passwd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public LibraryAdmin adminLogin(String id,String passwd) throws NoSuchAlgorithmException;
	
	/**
	 * 用户登录
	 * @param id
	 * @param passwd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public Reader readerLogin(String id,String passwd) throws NoSuchAlgorithmException;
//	/**
//	 * 测试 添加用户
//	 * @throws NoSuchAlgorithmException 
//	 */
//	public void txaddReaderAndLiAdmin() throws NoSuchAlgorithmException;
//	

}
