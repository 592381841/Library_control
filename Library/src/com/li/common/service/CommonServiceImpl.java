package com.li.common.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.li.common.dao.ICommonDao;
import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.domain.ReaderType;
import com.li.common.utils.MD5Encrypt;
import com.li.common.utils.VerifyCode;

public class CommonServiceImpl implements ICommonService {
	private ICommonDao commonDao;
	private VerifyCode  code=null;
	
	@Override
	public InputStream getImage() throws IOException  {
		
		 code=new VerifyCode();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		
			try {
				code.output(code.getImage(), out);
				in=new ByteArrayInputStream(out.toByteArray());
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 throw  new IOException();
			}
			finally{
				out.close();
				
			}
		
			

	
		
		// TODO Auto-generated method stub
		return in;
	}
	public ICommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}
	@Override
	public String getText() {
		if(code==null) return null;
		// TODO Auto-generated method stub
		return code.getText();
	}
	@Override
	public LibraryAdmin adminLogin(String id, String passwd) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		LibraryAdmin l=this.getCommonDao().findByObject("from LibraryAdmin al where al.account = ?", id);
		if(l!=null){
			if(MD5Encrypt.validatePassword(passwd, l.getPasswd())){
				return l;
			}
		}
		return null;
	}
	@Override
	public Reader readerLogin(String id, String passwd) throws NoSuchAlgorithmException {
		Reader r=this.getCommonDao().findByObject("from Reader r where r.account = ?", id);
		if(r!=null){
			if(MD5Encrypt.validatePassword(passwd, r.getPasswd())){
				return r;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public void txaddReaderAndLiAdmin() throws NoSuchAlgorithmException {
//		// TODO Auto-generated method stub
//		Random r=new Random();
//		LibraryAdmin li=new LibraryAdmin();
//		
//		String s=(r.nextInt(999999999)+1)+"";
//		while(s.equals("10000")){
//			 s=r.nextInt(999999999)+"";
//		}
//		li.setAdminName(s);
//		li.setAccount(s);
//	
//		li.setIdCard("----");
//			li.setAisRoot(false);
//	
//		if(r.nextInt(2)==0){
//			li.setAisStatus(false);
//		}else{
//			li.setAisStatus(true);
//		}
//		if(r.nextInt(2)==0){
//			li.setLibJu(false);
//		}else{
//			li.setLibJu(true);
//		}
//		if(r.nextInt(2)==0){
//			li.setReaderJu(false);
//		}else{
//			li.setReaderJu(true);
//		}
//		li.setPasswd(MD5Encrypt.encryptByMD5("1"));
//		if(r.nextInt(2)==0){
//			li.setReadTypeJu(false);
//		}else{
//			li.setReadTypeJu(true);
//		}
//		if(r.nextInt(2)==0){
//			li.setAsrJu(false);
//		}else{
//			li.setAsrJu(true);
//		}
//		this.getCommonDao().save(li);
//	}
	
	
}
