package com.li.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import com.li.common.dto.LibraryInfo;


/**
 * 获得图书馆信息工具类
 * @author cjy
 *
 */
public class PropertiesUtils {
	static{
		
	}
	/**
	 * 获取图书馆信息
	 * @return
	 * @throws IOException
	 */
	public static LibraryInfo getLibraryInfo() throws IOException{
		

		Properties p=new Properties();
		InputStream in= PropertiesUtils.class.getResourceAsStream("/library_info.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		
		LibraryInfo inf=new LibraryInfo();
		inf.setLibrarian_address(p.getProperty("librarian_address"));
		inf.setLibrarian_email(p.getProperty("librarian_email"));
		inf.setLibrarian_name(p.getProperty("librarian_name"));
		inf.setLibrarian_phone(p.getProperty("librarian_phone"));
		inf.setLibrary_name(p.getProperty("library_name"));
		inf.setLibrary_intro(p.getProperty("library_intro"));
		inf.setLibrary_time(p.getProperty("library_time"));
		inf.setModify_time(p.getProperty("modify_time"));
	
		
		return inf;
		
	}

	/**
	 * 获得图书馆名字
	 * @return
	 * @throws IOException
	 */
	public static String getLibraryName() throws IOException{

		Properties p=new Properties();
		InputStream in= PropertiesUtils.class.getResourceAsStream("/library_info.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		
		
		
	return 	p.getProperty("library_name");
			
		
	}
	/**
	 * 更改properties的内容
	 * @param l 所需的properties类对象
	 * @throws IOException
	 */
	public static void  modifyLibraryInfoProperties(LibraryInfo l) throws IOException{
		Properties p=new Properties();
		InputStream in= PropertiesUtils.class.getResourceAsStream("/library_info.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		p.put("librarian_name",l.getLibrarian_name());
		p.put("librarian_phone",l.getLibrarian_phone());
		p.put("librarian_address",l.getLibrarian_address());
		p.put("librarian_email",l.getLibrarian_email());
		p.put("library_intro",l.getLibrary_intro());
		p.put("library_time",l.getLibrary_time());
		p.put("library_name",l.getLibrary_name());
		String url	=PropertiesUtils.class.getClassLoader().getResource("/library_info.properties").getPath();
		FileOutputStream f=new FileOutputStream(url);
		p.store(f, "modify");
	}
	
	/***
	 * 修改properties文件中的某一项
	 * @param key 
	 * @param value
	 * @throws IOException
	 */
	public static void  modifyLibraryProperties(String key,String value) throws IOException{
		Properties p=new Properties();
		InputStream in= PropertiesUtils.class.getResourceAsStream("/library_info.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		p.put(key,value);
		
	String url	=PropertiesUtils.class.getClassLoader().getResource("/library_info.properties").getPath();
		FileOutputStream f=new FileOutputStream(url);
		p.store(f, "modify");
	}
	
	/**
	 * 根据key获取对应的value
	 * @param key
	 * @return 有则返回，没有则为null
	 * @throws IOException
	 */
	public static String  getAnProperties(String key) throws IOException{
		Properties p=new Properties();
		InputStream in= PropertiesUtils.class.getResourceAsStream("/library_info.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		String value=p.getProperty(key);
		return value;
		
	}
	
	/**
	 * 获得分页查询最大页数
	 * @return
	 * @throws IOException 
	 */
	public static int getSetting_page_result() throws IOException{
		Properties p=new Properties();
		InputStream in = null;
		try{
			in= PropertiesUtils.class.getClassLoader().getResourceAsStream("setting.properties");
			p.load(in);
			
			
		}
		catch(IOException e){
			e.printStackTrace();
			throw new IOException();

		}finally {
			if(in!=null){
				in.close();
				}
		}
		return Integer.parseInt(p.getProperty("maxResult"));
		
	}
	
	
	/**
	 * 获得setting文件中的某一项
	 * @return
	 * @throws IOException 
	 */
	public static <T> T getSettingProperties(String key) throws IOException{
		Properties p=new Properties();
		InputStream in = null;
		try{
			in= PropertiesUtils.class.getClassLoader().getResourceAsStream("setting.properties");
			
			p.load(in);
			
			
		}
		catch(IOException e){
			e.printStackTrace();
			throw new IOException();

		}finally {
			if(in!=null){
				in.close();
				}
		}
		return (T) p.getProperty(key);
		
	}
	/**
	 * 修改setting中的某一项
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public static void modifySettingProperties(String key,String value) throws IOException{
		Properties p=new Properties();
//		InputStream in= PropertiesUtils.class.getResourceAsStream("/setting.properties");
		InputStream in= PropertiesUtils.class.getClassLoader().getResourceAsStream("setting.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}
		finally {
			if(in!=null)
			in.close();
		}
		p.put(key,value);
		
	String url	=PropertiesUtils.class.getClassLoader().getResource("setting.properties").getPath();
		FileOutputStream f=new FileOutputStream(url);
		p.store(f, "modify");

	}
	
//@Test
//	public void asfd() throws IOException{
//		
//	this.modifySettingProperties("adminNumber", 10010+"");
////	System.out.println(getSetting_page_result());
//	}

	
}
