package com.li.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.jdbc.object.RdbmsOperation;

import com.li.common.domain.BookInfo;
import com.li.common.domain.BookType;
import com.li.common.domain.Bookrack;
import com.li.common.domain.BorrowInfo;
import com.li.common.domain.BorrowOperation;
import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.domain.ReaderType;
import com.li.common.domain.StackRoom;
import com.li.common.dto.BookInfoDto;
import com.li.common.dto.BookTypeDto;
import com.li.common.dto.BookrackDto;
import com.li.common.dto.BorrowInfoDto;
import com.li.common.dto.BorrowOperationDto;
import com.li.common.dto.LibraryAdminDto;
import com.li.common.dto.LibraryInfo;
import com.li.common.dto.PageInfo;
import com.li.common.dto.ReaderDto;
import com.li.common.dto.ReaderTypeDto;
import com.li.common.dto.StackRoomDto;
import com.li.common.utils.BeanUtilsEx;
import com.li.common.utils.MD5Encrypt;
import com.li.common.utils.PropertiesUtils;
import com.li.common.utils.ShortUUID;
import com.li.common.utils.TimeUtils;
public class AdminAction extends BaseAdminAction {

	/**
	 * 修改密码
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String general_modify_passwd() throws NoSuchAlgorithmException{
		String oldPasswd=this.getOldPasswd();
		String newPasswd =this.getNewPasswd();
		String newPasswdA=this.getNewpasswdagain();
		String account=((LibraryAdminDto)this.getSession().get("Liadmin")).getAccount();
		Map<String,Object> jsonMap=new HashMap<String,Object>(); 
		int len=newPasswd.trim().length();
		if(newPasswd==null||oldPasswd==null){
			jsonMap.put("info", "请正确填写数据");
			jsonMap.put("result", false);
			this.setJsonDataMap(jsonMap);
			
			return SUCCESS;
		}
		if(len>=8&&len<=16){
		if(newPasswd.equals(newPasswdA)){
			if(this.getAdminService().txmodify_passwd(account, newPasswd, oldPasswd)){
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

	/**
	 * 停用/激活admin账号
	 * @return
	 */
	public String general_modify_my_status(){
//		 boolean modify_status=this.isModify_status();
		 Map<String ,Object> mapjson=new HashMap<String, Object>();
		 LibraryAdminDto admin=	((LibraryAdminDto)this.getSession().get("Liadmin"));
			String account=		admin.getAccount();
		 if(!admin.getAisRoot()){
			 this.getAdminService().txmodify_status(account,false,true);
//			 if(modify_status){
//				 mapjson.put("info", "该用户已经被激活");
//			 }else{
				 mapjson.put("info", "该用户已经被停用");
//			 }
		
			 mapjson.put("result", true);
		
					this.getSession().remove("Liadmin");
				
		 }
		 else{
			 mapjson.put("info", "root用户无法停用");
			 mapjson.put("result", false);
		 }

	this.setJsonDataMap(mapjson);

		return SUCCESS;
	}

	//*上传admin图片
	public String uploadImage() throws IOException{
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		File file=this.getUploadFile();
		String loadContentType=this.getUploadFileContentType();
		String loadFilestr=this.getUploadFileFileName();
		StringBuilder builder=new StringBuilder("/people_pic/");
//		System.out.println(loadContentType);
		if(file==null){
			jsonMap.put("info","请勿上传空文件");
			jsonMap.put("result", false);
			this.setJsonDataMap(jsonMap);
			
			return SUCCESS;
		}
	
		

	            

				if(!loadContentType.substring(0, loadContentType.indexOf("/")).equals("image")){
					jsonMap.put("info","请勿上传非图片文件");
					jsonMap.put("result", false);
					this.setJsonDataMap(jsonMap);
					
					return SUCCESS;
	            }
				FileInputStream fin = null;
				FileOutputStream out = null;
				
					try{
					
					if(this.getPic()!=null){
						if(this.getPic()!=null ||this.getPic().trim().length()>0){
							builder.append(this.getPic()) ;
						}
						else{
							jsonMap.put("info","参数有误");
							jsonMap.put("result", false);
							this.setJsonDataMap(jsonMap);
							
							return SUCCESS;
						}
			
					}
					else{
						jsonMap.put("info","参数有误");
						jsonMap.put("result", false);
						this.setJsonDataMap(jsonMap);
						
						return SUCCESS;
						
					}
					
					
					File newFile =new File(this.getServletContext().getRealPath(builder.toString()));
					if(!newFile.exists()){
						if(!newFile.getParentFile().exists()){
							newFile.getParentFile().mkdirs();
						}
						newFile.createNewFile();
					}
					fin=new FileInputStream(file);
					 out=new FileOutputStream(newFile);
					 
					byte [] b=new byte[1024];
					while(fin.read(b)>0){
						out.write(b);
					}
					}
					catch(IOException e){
						throw new IOException(e);
						
					}finally{
						if(fin!=null){
							fin.close();
						}
						if(out!=null){
							out.close();
						}
						
					}
					
					jsonMap.put("info","上传成功");
					jsonMap.put("result", true);
					jsonMap.put("pic",builder.toString());
					this.setJsonDataMap(jsonMap);
					return SUCCESS;
					
				
	            	
	            	
			
}


			
		
		
	
	//图书馆设置模块++++++++++++++++++++++++++++++
	
	/**
	 * 加载li_setting界面
	 * @return
	 * @throws IOException 
	 */
public String li_setting_li_setting() throws Exception{
	
	

	this.setLibraryInfo(PropertiesUtils.getLibraryInfo());
	

		return FORWARD;
	}
/**
 * 修改图书馆资料
 * @return
 * @throws IOException 
 */
public String li_setting_modify_library_info() throws Exception{
	 Map<String ,Object> mapjson=new HashMap<String, Object>();
//	 System.out.println("shdfkhskj");
//	if(!((LibraryAdminDto)this.getSession().get("Liadmin")).getAisRoot()){
//		 mapjson.put("info", "您无此权限");
//		 return SUCCESS;
//	}
//	
	LibraryInfo li=this.getLibraryInfo();
	
	li.setModify_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	PropertiesUtils.modifyLibraryInfoProperties(li);
	
	 mapjson.put("info", "修改成功");
	 this.setJsonDataMap(mapjson);
	
	
	return SUCCESS;
}





//管理员控制模块++++++++++++++++++++++++++++
/***
 * 加载进入manager_control页面
 * @return
 * @throws IOException
 */
public String manager_control_manager_control() throws IOException{
	
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageManagerList(maxResult,null));
	p.setPage(1);
	this.setPageInfo(p);
	List<LibraryAdminDto> lDtoList=new ArrayList<LibraryAdminDto>();
	List<LibraryAdmin> lList=null;
	lList=this.getAdminService().findpageManagerList(0, maxResult, null, null,null);
	if(lList!=null){
		for(LibraryAdmin l :lList){
			LibraryAdminDto d=new LibraryAdminDto();
			BeanUtilsEx.copyProperties(d,l);
			lDtoList.add(d);
		}
		this.setLibraryAdminDto(lDtoList);
		
	}else{
		this.setLibraryAdminDto(null);
	}
//	System.out.println(lDtoList);
	
	
		return FORWARD;
	
	
}

/**
 * 停用/激活admin账号
 * @return
 */
public String manager_control_modify_admin_is_status(){
	 boolean modify_status=this.isModify_status();
	 Map<String ,Object> mapjson=new HashMap<String, Object>();
	 String account=this.getAccount();
	 if(!this.isIs_root()){
		 this.getAdminService().txmodify_status(account,modify_status,true);
		 if(modify_status){
			 mapjson.put("info", "该用户已经被激活");
		 }else{
			 mapjson.put("info", "该用户已经被停用");
		 }
	
		 mapjson.put("result", true);
		 if(this.isIs_me()){
				this.getSession().remove("Liadmin");
			}
	 }
	 else{
		 mapjson.put("info", "root用户无法停用");
		 mapjson.put("result", false);
	 }

this.setJsonDataMap(mapjson);

	return SUCCESS;
}

public String manager_control_modify_more_admin_is_status() throws IOException{
	 boolean modify_status=this.isModify_status();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String str=this.getAccountListStr();
	if(str==null||str.trim().length()==0){
		 mapjson.put("info", "请勾选要删除的管理员");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	else{
		String[] arrayStr=str.split(",");
		mapjson=this.getAdminService().txMoremodify_status(arrayStr, ((LibraryAdminDto)this.getSession().get("Liadmin")).getAccount(), modify_status, true);
		//从新加载分页
		int maxResult =PropertiesUtils.getSetting_page_result();
		PageInfo p=new PageInfo();
		p.setCount(this.getAdminService().countPageManagerList(maxResult,null));
		int page=this.getPageInfo().getPage();
		p.setPage(page);
		mapjson.put("pageInfo",p);
		List<LibraryAdminDto> lDtoList=new ArrayList<LibraryAdminDto>();
		List<LibraryAdmin> lList=null;
		lList=this.getAdminService().findpageManagerList(page, maxResult, null, null,null);
		if(lList!=null){
			for(LibraryAdmin l :lList){
				LibraryAdminDto d=new LibraryAdminDto();
				BeanUtilsEx.copyProperties(d,l);
				lDtoList.add(d);
			}
			mapjson.put("libraryAdminDto", lDtoList);
//			this.();
			
		}else{
//			this.setLibraryAdminDto(null);
			mapjson.put("libraryAdminDto", null);
		}
		
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
		
	}
	
}
/**
 * 删除admin账号(列表删除)
 * @return
 * @throws Exception 
 */
public String manager_control_delete_admin() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	 String account=this.getAccount();
	 if(!this.isIs_root()){
		 
		 
		
		 mapjson=	this.getAdminService().txdelete_user(true, account);
		 if((boolean)mapjson.get("result")){
			 int maxResult =PropertiesUtils.getSetting_page_result();
				PageInfo p=new PageInfo();
				p.setCount(this.getAdminService().countPageManagerList(maxResult,null));
				int page=this.getPageInfo().getPage();
				if(page>p.getCount()){
					page=p.getCount();
				}
			
				p.setPage(page);
				mapjson.put("pageInfo",p);
				List<LibraryAdminDto> lDtoList=new ArrayList<LibraryAdminDto>();
				List<LibraryAdmin> lList=null;
				lList=this.getAdminService().findpageManagerList(page, maxResult, null, null,null);
				if(lList!=null){
					for(LibraryAdmin l :lList){
						LibraryAdminDto d=new LibraryAdminDto();
						BeanUtilsEx.copyProperties(d,l);
						lDtoList.add(d);
					}
					mapjson.put("libraryAdminDto", lDtoList);
//					this.();
					
				}else{
//					this.setLibraryAdminDto(null);
					mapjson.put("libraryAdminDto", null);
				}
		 }
				
		 }
	
		 else{
			 mapjson.put("info", "root用户无法删除");
			 mapjson.put("result", false);
		 }
	
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	 }
/**
 * 分页加载admin信息
 * @return
 * @throws IOException
 */
public String manager_control_page_managerInfo() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageManagerList(maxResult,null));
	int page=this.getPageInfo().getPage();
	p.setPage(page);
	mapjson.put("pageInfo",p);
	List<LibraryAdminDto> lDtoList=new ArrayList<LibraryAdminDto>();
	List<LibraryAdmin> lList=null;
	lList=this.getAdminService().findpageManagerList(page, maxResult, null, null,null);
	if(lList!=null){
		for(LibraryAdmin l :lList){
			LibraryAdminDto d=new LibraryAdminDto();
			BeanUtilsEx.copyProperties(d,l);
			lDtoList.add(d);
		}
		mapjson.put("libraryAdminDto", lDtoList);
//		this.();
		
	}else{
//		this.setLibraryAdminDto(null);
		mapjson.put("libraryAdminDto", null);
	}
	
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
}
/**
 * 进入admin_info_setting页面
 * @return
 * @throws Exception
 */
public String manager_control_admin_info_setting() throws Exception{
	String account=this.getAccount();
	LibraryAdminDto d=new LibraryAdminDto();
	LibraryAdmin admin=this.getAdminService().findAnLibararyAdminInfo(account);
	if(admin==null){
		throw new Exception("查询不到admin");
	}else{
		
		BeanUtilsEx.copyProperties(d, admin);
	}
	this.setAdminDto(d);
//	System.out.println(d);
	return FORWARD;
}
public String manager_control_more_delete_admin() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String str=this.getAccountListStr();
	if(str==null||str.trim().length()==0){
		 mapjson.put("info", "请勾选要删除的管理员");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	else{
		String[] arrayStr=str.split(",");
		 
		
		 mapjson=	this.getAdminService().txMoreDelete_user(true, arrayStr, ((LibraryAdminDto)this.getSession().get("Liadmin")).getAccount());
		 if((boolean)mapjson.get("result")){
			 int maxResult =PropertiesUtils.getSetting_page_result();
				PageInfo p=new PageInfo();
				p.setCount(this.getAdminService().countPageManagerList(maxResult,null));
				int page=this.getPageInfo().getPage();
				if(page>p.getCount()){
					page=p.getCount();
				}
			
				p.setPage(page);
				mapjson.put("pageInfo",p);
				List<LibraryAdminDto> lDtoList=new ArrayList<LibraryAdminDto>();
				List<LibraryAdmin> lList=null;
				lList=this.getAdminService().findpageManagerList(page, maxResult, null, null,null);
				if(lList!=null){
					for(LibraryAdmin l :lList){
						LibraryAdminDto d=new LibraryAdminDto();
						BeanUtilsEx.copyProperties(d,l);
						lDtoList.add(d);
					}
					mapjson.put("libraryAdminDto", lDtoList);
//					this.();
					
				}else{
//					this.setLibraryAdminDto(null);
					mapjson.put("libraryAdminDto", null);
				}
		 }
		
		
		
	}
	 this.setJsonDataMap(mapjson);
	
	return SUCCESS;
}

/***
 * admin详情页删除用户
 * @return
 * @throws Exception 
 */
public String manager_control_delete_an_admin() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	 String account=this.getAccount();
	 if(!this.isIs_root()){
		 
		 
		
		 mapjson=	this.getAdminService().txdelete_user(true, account);
				
		 }
	
		 else{
			 mapjson.put("info", "root用户无法删除");
			 mapjson.put("result", false);
		 }
	
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	 }
/***
 * 详情页修改admin
 * @return
 */
public String manager_control_modify_admin_info(){
//	System.out.println();
	LibraryAdminDto d=this.getAdminDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
//	System.out.println(d);
	if(d.getAccount()==null ||d.getAccount().trim().length()==0||d.getAdminName()==null || d.getAdminName().trim().length()==0 ||d.getIdCard()==null ||d.getAsrJu()==null||d.getReadTypeJu()==null||d.getReaderJu()==null||d.getLibJu()==null){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	int len=d.getIdCard().trim().length();
	if(len!=18){
		 mapjson.put("info", "身份证必须18位");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	if(this.getAdminService().txModify_admin_info(d)){
		 mapjson.put("info", "修改成功");
		 mapjson.put("result", true);
	}
	else{
		 mapjson.put("info", "未知原因，修改失败");
		 mapjson.put("result", false);
	}
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
}
/**
 * 进入add_admin_info.jsp
 * @return
 * @throws IOException 
 */
public String manager_control_add_admin_info() throws IOException{
		String account=PropertiesUtils.getSettingProperties("adminNumber");
		String passwd=PropertiesUtils.getSettingProperties("defaultAdminPasswd");
		this.setAccount(account);
		this.setNewPasswd(passwd);
		
		return FORWARD;
}	
/**
 * 添加admin账号
 * @return
 * @throws IOException
 * @throws NoSuchAlgorithmException
 */
public String manager_control_add_admin() throws IOException, NoSuchAlgorithmException{
	String account=PropertiesUtils.getSettingProperties("adminNumber");
	String passwd=PropertiesUtils.getSettingProperties("defaultAdminPasswd");
	LibraryAdminDto d=this.getAdminDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(d.getAisStatus()==null ||d.getAdminName()==null || d.getAdminName().trim().length()==0 ||d.getIdCard()==null ||d.getAsrJu()==null||d.getReadTypeJu()==null||d.getReaderJu()==null||d.getLibJu()==null){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	int len=d.getIdCard().trim().length();
	if(len!=18){
		 mapjson.put("info", "身份证必须18位");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
	
	LibraryAdmin l=new LibraryAdmin();
	
	
	l.setAccount(account);
	l.setAdminName(d.getAdminName());
	l.setIdCard(d.getIdCard());
	l.setAisRoot(false);
	l.setAisStatus(d.getAisStatus());
	l.setAsrJu(d.getAsrJu());
	l.setLibJu(d.getLibJu());
	l.setPasswd(MD5Encrypt.encryptByMD5(passwd));
	StringBuilder str=new StringBuilder("admin/");
	str.append(account);
	str.append(".jpg");
	l.setPic(str.toString());
	l.setReaderJu(d.getReaderJu());
	l.setReadTypeJu(d.getReadTypeJu());
	this.getAdminService().txAdd_admin_info(l);
	 mapjson.put("info", "添加成功");
	 mapjson.put("result", true);
	 mapjson.put("account", l.getAccount());
	 mapjson.put("passwd",passwd );
	 mapjson.put("adminName", l.getAdminName());
	 mapjson.put("id_card", l.getIdCard());
	 mapjson.put("pic",l.getPic());
	 this.setJsonDataMap(mapjson);
	 PropertiesUtils.modifySettingProperties("adminNumber", (Integer.parseInt(account)+1)+"");
//	System.out.println(l);
	 return SUCCESS;
	
	
}

/**
 * 重置密码
 * @return
 * @throws IOException
 * @throws NoSuchAlgorithmException
 */
public String manager_control_reset_passwd() throws IOException, NoSuchAlgorithmException{
	String passwd=PropertiesUtils.getSettingProperties("defaultAdminPasswd");
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String account=this.getAccount();
	if(account==null ||account.trim().length()==0){
		 mapjson.put("info","参数有误" );
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
	LibraryAdmin l=this.getAdminService().findAnLibararyAdminInfo(account);
	if(l==null){
		mapjson.put("info","查无此账号" );
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	l.setPasswd(MD5Encrypt.encryptByMD5(passwd));
	this.getAdminService().txAdd_admin_info(l);
	 mapjson.put("info","密码重置成功" );
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
}

//书库控制页面
/**
 * 进入stack_room_control
 * @return
 * @throws IOException 
 */
public String stack_room_control_stack_room_control() throws IOException{
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageStackRoomList(maxResult,null));
	p.setPage(1);
	this.setPageInfo(p);
	List<StackRoom>lList=this.getAdminService().findpageStackRoomList(0, maxResult, null, "id","desc");
	if(lList==null ||lList.size()==0)
		{
		this.setStackRoomDtos(null);
		return FORWARD;
		}
	List<StackRoomDto> dtoList=new ArrayList<StackRoomDto>();
	for(StackRoom r :lList){
		StackRoomDto dto=new StackRoomDto();
		BeanUtilsEx.copyProperties(dto,r );
		dtoList.add(dto);
		
	}
	this.setStackRoomDtos(dtoList);
		
	
	
	
		return FORWARD;
	
}
/**
 * 添加书库
 * @return
 * @throws IOException 
 */
public String stack_room_control_addStackRoom() throws IOException{
	
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	StackRoom s=this.getStackRoom();
	if(s.getSrAddress()==null ||s.getSrAddress().trim().length()==0||s.getSrName()==null||s.getSrName().trim().length()==0){
		mapjson.put("info", "添加失败");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	this.getAdminService().txSaveStackRoom(s);
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageStackRoomList(maxResult,null));
	p.setPage(1);
	List<StackRoom>lList=this.getAdminService().findpageStackRoomList(0, maxResult, null, "id","desc");
	List<StackRoomDto> dtoList=new ArrayList<StackRoomDto>();
	if(lList==null ||lList.size()==0)
	{
		dtoList=null;
	}
	else{
		for(StackRoom r :lList){
			StackRoomDto dto=new StackRoomDto();
			BeanUtilsEx.copyProperties(dto,r );
			dtoList.add(dto);
			
		}
	}


	mapjson.put("info", "添加成功");
mapjson.put("result", true);
mapjson.put("stackRooms", dtoList);
mapjson.put("pageInfo",p);
this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	
	
}

/**
 * 修改书库信息
 * @return
 */
public String stack_room_control_modifyStackRoom(){
	StackRoom s=this.getStackRoom();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(s.getId()==null ||s.getId()==0||s.getSrName()==null ||s.getSrName().trim().length()==0 ||s.getSrAddress()==null ||s.getSrAddress().trim().length()==0){
		 mapjson.put("info", "参数有误");
//		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	mapjson=this.getAdminService().txModifyStackRoom(s);

	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
	
}

/**
 * 删除书库
 * @return
 * @throws IOException 
 */
public String stack_room_control_deleteStackRoom() throws IOException{
	int id =this.getId();
	Map<String ,Object> mapjson=null;
		 mapjson=	this.getAdminService().txDeleteStackRoom(id);
		if((boolean) mapjson.get("result")){
			int maxResult =PropertiesUtils.getSetting_page_result();
			PageInfo p=new PageInfo();
			p.setCount(this.getAdminService().countPageStackRoomList(maxResult,null));
			
			if(this.getPageInfo().getPage()>p.getCount()){
				p.setPage(p.getCount());
			}else{
				p.setPage(this.getPageInfo().getPage());
			}
			List<StackRoom>lList=this.getAdminService().findpageStackRoomList(p.getPage(), maxResult, null, "id","desc");
			List<StackRoomDto> dtoList=new ArrayList<StackRoomDto>();
			if(lList==null ||lList.size()==0)
			{
				dtoList=null;
			}
			else{
				for(StackRoom r :lList){
					StackRoomDto dto=new StackRoomDto();
					BeanUtilsEx.copyProperties(dto,r );
					dtoList.add(dto);
					
				}
			}
			
			mapjson.put("stackRooms", dtoList);
			mapjson.put("pageInfo",p);
			
			
		}
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
}


/**
 * 分页查找书库
 * @return
 * @throws IOException
 */
public String stack_room_control_pageStackRoom() throws IOException{
	
		Map<String ,Object> mapjson=new HashMap<String ,Object>();
		int maxResult =PropertiesUtils.getSetting_page_result();
		PageInfo p=new PageInfo();
		p.setCount(this.getAdminService().countPageStackRoomList(maxResult,null));
		int page=this.getPageInfo().getPage();
		p.setPage(page);
		mapjson.put("pageInfo",p);
		List<StackRoom> lList=this.getAdminService().findpageStackRoomList(p.getPage(), maxResult, null, "id","desc");
		if(lList==null ||lList.size()==0)
		{
		this.setStackRoomDtos(null);
		return SUCCESS;
		}
	List<StackRoomDto> dtoList=new ArrayList<StackRoomDto>();
	for(StackRoom r :lList){
		StackRoomDto dto=new StackRoomDto();
		BeanUtilsEx.copyProperties(dto,r );
		dtoList.add(dto);
		
	}
		
			mapjson.put("stackRooms", dtoList);
//		
		
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	
	
	
}
/**
 * 进入bookRack_control
 * @return
 * @throws IOException
 */
public String stack_room_control_bookrack_control() throws IOException{
	int id=this.getId();
	int max=PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setPage(1);
	p.setCount(this.getAdminService().countPageBookrack(id, max));
	this.setPageInfo(p);
	List<Bookrack> l=this.getAdminService().findPageBookrack(id,max,1);
	List<BookrackDto> dto=new ArrayList<BookrackDto>();
	this.setStackRoom(this.getAdminService().findStackRoom(id));
	if(l==null||l.size()==0){
		this.setBookrackDtos(null);
	}
	else{
		for(Bookrack b :l){
			BookrackDto d=new BookrackDto();
			BeanUtilsEx.copyProperties(d, b);
			dto.add(d);
		}
		this.setBookrackDtos(dto);
	}
		
		
	return FORWARD;
}
/**
 * 修改书架信息
 * @return
 */
public String stack_room_control_modifyBookrack(){
	BookrackDto s=this.getBookrackDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(s.getBookrackId()==null ||s.getBookrackId()==0||s.getBookrName()==null ||s.getBookrName().trim().length()==0 ){
		 mapjson.put("info", "参数有误");
//		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	mapjson=this.getAdminService().txmodifyBookrack(s.getBookrackId(),s.getBookrName());

	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
}

/**
 * 删除书架
 * @return
 * @throws IOException
 */
public String stack_room_control_deleteBookrack() throws IOException{
	int id =this.getId();
	
	Map<String ,Object> mapjson=null;
		 mapjson=	this.getAdminService().txDeleteBookrack(id);
		if((boolean) mapjson.get("result")){
			int maxResult =PropertiesUtils.getSetting_page_result();
			PageInfo p=new PageInfo();
			int stackRoomId =(int) mapjson.get("stackRoomId");
			mapjson.remove("stackRoomId");
			p.setCount(this.getAdminService().countPageBookrack(stackRoomId, maxResult));
			
			if(this.getPageInfo().getPage()>p.getCount()){
				p.setPage(p.getCount());
			}else{
				p.setPage(this.getPageInfo().getPage());
			}
			List<Bookrack>lList=this.getAdminService().findPageBookrack(stackRoomId, maxResult, p.getPage());
			List<BookrackDto> dtoList=new ArrayList<BookrackDto>();
			if(lList==null ||lList.size()==0)
			{
				dtoList=null;
			}
			else{
				for(Bookrack r :lList){
					BookrackDto dto=new BookrackDto();
					BeanUtilsEx.copyProperties(dto,r );
					dtoList.add(dto);
					
				}
			}
			
			mapjson.put("bookrack", dtoList);
			mapjson.put("pageInfo",p);
			
			
		}
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
	
}

/**
 * 添加书架
 * @return
 * @throws IOException
 */
public String stack_room_control_addBookrack() throws IOException{

	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookrackDto s=this.getBookrackDto();
	if(s.getBookrName()==null ||s.getBookrName().trim().length()==0){
		mapjson.put("info", "添加失败（参数错误）");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	int id=this.getId();
	if(id==1||id==2){
		mapjson.put("info", "特殊书库无法添加书架");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
			return SUCCESS;
	}
	if(!this.getAdminService().txSaveBookRack(s, id)){
		mapjson.put("info", "添加失败");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
			return SUCCESS;
	}
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageBookrack(id, maxResult));
	p.setPage(1);
	List<Bookrack> lList=this.getAdminService().findPageBookrack(id, maxResult, 1);
	List<BookrackDto> dtoList=new ArrayList<BookrackDto>();
	if(lList==null ||lList.size()==0)
	{
		dtoList=null;
	}
	else{
		for(Bookrack r :lList){
			BookrackDto dto=new BookrackDto();
			BeanUtilsEx.copyProperties(dto,r );
			dtoList.add(dto);
			
		}
	}


	mapjson.put("info", "添加成功");
mapjson.put("result", true);
mapjson.put("bookrack", dtoList);
mapjson.put("pageInfo",p);
this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}

/**
 * 分页查询bookrack列表
 * @return
 * @throws IOException
 */
public String stack_room_control_pageBookrack() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int maxResult =PropertiesUtils.getSetting_page_result();
	int id=this.getStackRoom().getId();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageBookrack(id, maxResult));
	p.setPage(this.getPageInfo().getPage());
	List<Bookrack> lList=this.getAdminService().findPageBookrack(id, maxResult, p.getPage());
	List<BookrackDto> dtoList=new ArrayList<BookrackDto>();
	if(lList==null ||lList.size()==0)
	{
		dtoList=null;
	}
	else{
		for(Bookrack r :lList){
			BookrackDto dto=new BookrackDto();
			BeanUtilsEx.copyProperties(dto,r );
			dtoList.add(dto);
			
		}
	}


mapjson.put("bookrack", dtoList);
mapjson.put("pageInfo",p);
this.setJsonDataMap(mapjson);
	return SUCCESS;
}

/**
 * 进入bookinfo页面
 * @return
 * @throws IOException
 */
public String stack_room_control_bookrack_info() throws IOException{
	int bookrack_id=this.getId();
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	
	
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	p.setPage(1);
	this.setPageInfo(p);
	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, 1);
	StackRoom s=this.getAdminService().findStackRoom(this.getStackRoom().getId());
	Bookrack bookrack=this.getAdminService().findAnBookrack(bookrack_id);
	BookrackDto bdto=new BookrackDto();
	BeanUtilsEx.copyProperties(bdto, bookrack);
	bdto.setBookNumber(bookrack.getBookInfos().size());
	this.setStackRoom(s);
	this.setBookrackDto(bdto);
	if(lList==null ||lList.size()==0)
		{
		this.setBookInfoDtos(null);
		
		return FORWARD;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}

	this.setBookInfoDtos(dtoList);
	return FORWARD;
	
}
/**
 * 分页查看bookRAck——info
 * @return
 * @throws IOException 
 */
public String stack_room_control_page_bookrack_info() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int bookrack_id=this.getBookrackDto().getBookrackId();
	int maxResult =PropertiesUtils.getSetting_page_result();
	
	PageInfo p=new PageInfo();
	
	
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	p.setPage(this.getPageInfo().getPage());
	mapjson.put("pageInfo", p);

	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, p.getPage());
	if(lList==null ||lList.size()==0)
		{
		mapjson.put("bookInfoDtos", null);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	

		
}
/**
 * 下架书籍
 * @return
 * @throws IOException
 */
public String book_control_deleteBookInfo() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int id=this.getBookInfoDto().getId();
	if(!this.getAdminService().txDeleteBookInfo(id)){
		mapjson.put("info", "参数有误或该书正在外借中");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	
	
	//分页加载
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	
	int bookrack_id=this.getBookrackDto().getBookrackId();
	if(bookrack_id==0){
		mapjson.put("result", true);
		mapjson.put("info", "下架成功");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	Bookrack bookrack=this.getAdminService().findAnBookrack(bookrack_id);
	if(bookrack==null){
		mapjson.put("info", "参数有误");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	if(this.getPageInfo().getPage()>p.getCount()){
		p.setPage(p.getCount());
	}else{
		p.setPage(this.getPageInfo().getPage());
	}
	mapjson.put("result", true);
	mapjson.put("info", "下架成功");
	mapjson.put("pageInfo", p);
	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, p.getPage());
	mapjson.put("bookNumber", bookrack.getBookInfos().size());
	if(lList==null ||lList.size()==0)
		{
		mapjson.put("bookInfoDtos", null);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
}


/**
 * 闲置书籍
 * @return
 * @throws IOException
 */
public String book_control_unuseBookInfo() throws IOException{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int id=this.getBookInfoDto().getId();
	if(!this.getAdminService().txunuseBookInfo(id)){
		mapjson.put("info", "参数有误或该书正在外借中");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	
	int bookrack_id=this.getBookrackDto().getBookrackId();
	//当并非通过书库加载的时候，不分页加载，分页加载交由自身分页函数处理
	if(bookrack_id==0){
		mapjson.put("result", true);
		mapjson.put("info", "闲置成功");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	//分页加载
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	
	
	Bookrack bookrack=this.getAdminService().findAnBookrack(bookrack_id);
	if(bookrack==null){
		mapjson.put("info", "参数有误");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	if(this.getPageInfo().getPage()>p.getCount()){
		p.setPage(p.getCount());
	}else{
		p.setPage(this.getPageInfo().getPage());
	}
	mapjson.put("result", true);
	mapjson.put("info", "闲置成功");
	mapjson.put("pageInfo", p);
	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, p.getPage());
	mapjson.put("bookNumber", bookrack.getBookInfos().size());
	if(lList==null ||lList.size()==0)
		{
		mapjson.put("bookInfoDtos", null);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
}
/**
 * 进入addOrModify_book.jsp页面
 * 当AddOrmodify参数为true则添加书，当参数为false为修改书
 * @return
 * @throws Exception 
 */
public String book_control_addOrModify_book() throws Exception{
	String bookID=null;
	BookInfo bookInfo=null;
	this.setAddOrmodify(this.getAddOrmodify());
	if(!this.getAddOrmodify()){
		bookID=this.getBookInfoDto().getBookId();
		if(bookID==null){
			throw new Exception("前台界面传参有误");
		}
		
		List<BookInfo> bookInfoList=this.getAdminService().findAnBookInfo(bookID);
		if(bookInfoList==null){
			throw new Exception("前台界面传参有误");
		}
		bookInfo=bookInfoList.get(0);
		BookInfoDto bookInfoDto=new BookInfoDto();
		BeanUtilsEx.copyProperties(bookInfoDto,bookInfo);
		int sum=0;
		for(BookInfo info : bookInfoList){
			if(info.getBookrack().getBookrackId()!=2){
				sum++;
			}
		}
		this.setBookSum(sum);
		bookInfoDto.setCollectInfo(bookInfo.getBookrack().getStackRoom().getSrName()+"/"+bookInfo.getBookrack().getBookrName()+"/"+bookInfo.getBookrackNumber());
		if(bookInfo.getBookrack().getBookrackId()==1){
			bookInfoDto.setBookUnuse(true);
		}
		else{
			bookInfoDto.setBookUnuse(false);
		}
		this.setBookInfoDto(bookInfoDto);

		List<StackRoom> sl=this.getAdminService().findALLDomain(StackRoom.class);
		List<StackRoomDto> sdtoList=new ArrayList<StackRoomDto>();
		if(sl!=null){
			for(StackRoom s :sl){
				
				StackRoomDto sdto=new StackRoomDto();
				if(s.getId()!=2 && s.getId()!=1){
					
					BeanUtilsEx.copyProperties(sdto, s);
					sdtoList.add(sdto);
				}
				
				
			}
			this.setStackRoomDtos(sdtoList);
			
		}
		
		//书类型列表
		List<BookType> bl=this.getAdminService().findALLDomain(BookType.class);
		List<BookTypeDto> dtoList=new ArrayList<BookTypeDto>();
		if(bl!=null){
			for(BookType b:bl){
				BookTypeDto dto=new BookTypeDto();
				BeanUtilsEx.copyProperties(dto, b);
				if(bookInfo.getBookType().getBotyId().equals(dto.getBotyId())){
				dtoList.add(0, dto);	
				}else{
				dtoList.add(dto);
				}
			}
			this.setBookTypeDtos(dtoList);
			
		}
		
	}else{
		//书类型列表
		List<BookType> bl=this.getAdminService().findALLDomain(BookType.class);
		List<BookTypeDto> dtoList=new ArrayList<BookTypeDto>();
		if(bl!=null){
			for(BookType b:bl){
				BookTypeDto dto=new BookTypeDto();
				BeanUtilsEx.copyProperties(dto, b);
				dtoList.add(dto);
			}
			this.setBookTypeDtos(dtoList);
			
		}
		//书库列表
		List<StackRoom> sl=this.getAdminService().findALLDomain(StackRoom.class);
		List<StackRoomDto> sdtoList=new ArrayList<StackRoomDto>();
		BookrackDto brdto=new BookrackDto();
		if(sl!=null){
			for(StackRoom s :sl){
				
				StackRoomDto sdto=new StackRoomDto();
				if(s.getId()!=2){
					
					BeanUtilsEx.copyProperties(sdto, s);
					sdtoList.add(sdto);
				}
				if(s.getId()==1){
					//闲置书库的书架信息
					Bookrack br=(Bookrack) s.getBookracks().toArray()[0];
					BeanUtilsEx.copyProperties(brdto, br);
					this.setBookrackDto(brdto);
				}
				
			}
			this.setStackRoomDtos(sdtoList);
			
		}
	}


return FORWARD;
}
/**
 * 根据选择的书库，联动加载书架
 * @return
 * @throws Exception 
 */
public String book_control_modifyBookrackCheck() throws Exception{
	
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookInfo book=null;
	if(this.getBookInfoDto()!=null){
		if(this.getBookInfoDto().getId()!=null){
			List<BookInfo> li=this.getAdminService().findAnBookInfo(this.getBookInfoDto().getId());
			if(li.size()!=0)book=li.get(0);
		}
		
	}
	int id=this.getId();
	if(id==0){
		mapjson.put("bookrack", null);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	List<Bookrack> lList=this.getAdminService().findBookrackByStackRoomID(id);
	List<BookrackDto> dtoList=new ArrayList<BookrackDto>();
	if(lList==null ||lList.size()==0)
	{
		dtoList=null;
	}
	else{
		for(Bookrack r :lList){
			if(book!=null){
				if(book.getBookrack().getBookrackId()==r.getBookrackId()){
					continue;
				}
			}
			BookrackDto dto=new BookrackDto();
			BeanUtilsEx.copyProperties(dto,r );
			dtoList.add(dto);
			
		}
	}
	
	mapjson.put("bookrack", dtoList);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
}

/**
 * 添加书籍
 * @return
 */
public String book_control_addBookinfo(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();

	BookInfoDto dto=this.getBookInfoDto();
	int bookRackId=this.getBookrackDto().getBookrackId();
	int sum=this.getBookSum();
	String bookTypeDtoID=this.getBookTypeDto().getBotyId();
	Bookrack bookrack=this.getAdminService().findAnBookrack(bookRackId);
	BookType bookType=this.getAdminService().findAnBookType(bookTypeDtoID);
	if(bookrack==null ||bookType==null){
		mapjson.put("info", "参数有误，添加失败");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	BookInfo b=new BookInfo();
	b.setBookId(ShortUUID.generateShortUuid());
	b.setBookIntroduce(dto.getBookIntroduce());
	b.setBookIsBorrow(false);
	b.setBookIsDelete(false);
	b.setBookName(dto.getBookName());
	b.setBookPress(dto.getBookPress().trim());
	b.setBookrack(bookrack);
	if(bookrack.getBookrackId()==1){
		b.setBookrackNumber(0);
	}else{
		b.setBookrackNumber(bookrack.getBrAddBookNumber()+1);
		bookrack.setBrAddBookNumber(bookrack.getBrAddBookNumber()+1);
	}

	b.setBookType(bookType);
	b.setBookWriter(dto.getBookWriter());
	bookrack.getBookInfos().add(b);
	bookType.getBookInfos().add(b);
	this.getAdminService().txSaveBookInfo(b);
	for(int vs=0;vs<(sum-1);vs++){
		
		BookInfo bvs=new BookInfo(bookrack, bookType, b.getBookId(), b.getBookName(), b.getBookPress(), b.getBookrackNumber(), b.getBookWriter(), b.getBookIsBorrow(), b.getBookIntroduce(), b.getBookIsDelete());
		
		this.getAdminService().txSaveBookInfo(bvs);
	}
	mapjson.put("info", "添加成功");
	mapjson.put("result", true);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}
/**
 * 修改书籍
 * @return
 * @throws Exception
 */
public String book_control_modifyBookinfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookInfoDto bookInfoDto=this.getBookInfoDto();
	String bookId=bookInfoDto.getBookId();
	
	
	BookType bookType=this.getAdminService().findAnBookType(this.getBookTypeDto().getBotyId());
	BookrackDto rackdto=this.getBookrackDto();
	Bookrack bookrack =null;
	if(rackdto!=null){
		if(rackdto.getBookrackId()!=null){
			bookrack=this.getAdminService().findAnBookrack(rackdto.getBookrackId());
			bookrack.setBrAddBookNumber(bookrack.getBrAddBookNumber()+1);
		}
	}
	
	List<BookInfo> bookInfoList=this.getAdminService().findAnBookInfo(bookId);
	if(bookInfoList==null||bookInfoList.size()==0){
		mapjson.put("info", "参数有误，修改失败");
		mapjson.put("result", false);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	for(BookInfo b :bookInfoList){
		b.setBookIntroduce(bookInfoDto.getBookIntroduce());
		b.setBookName(bookInfoDto.getBookName());
		b.setBookPress(bookInfoDto.getBookPress());
		b.setBookType(bookType);
		if(bookrack!=null){
			b.setBookrack(bookrack);
			b.setBookrackNumber(bookrack.getBrAddBookNumber());
			
		}
		b.setBookWriter(bookInfoDto.getBookWriter());
		this.getAdminService().txSaveBookInfo(b);
		
	}
	
	mapjson.put("info", "修改成功");
	mapjson.put("result", true);
	this.setJsonDataMap(mapjson);
	return SUCCESS;

}
/**
 * 进入delete_book_list
 * @return
 * @throws IOException
 */
public String book_control_delete_book_list() throws IOException{
	int bookrack_id=2;
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	
	
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	p.setPage(1);
	this.setPageInfo(p);
	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, 1);
	Bookrack bookrack=this.getAdminService().findAnBookrack(bookrack_id);
	this.setBookSum(bookrack.getBookInfos().size());
	if(lList==null ||lList.size()==0)
		{
		this.setBookInfoDtos(null);
		
		return FORWARD;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}

	this.setBookInfoDtos(dtoList);
	return FORWARD;
}
/**
 * 分页加载下架图书列表
 * @return
 * @throws IOException 
 */
public String book_control_page_delete_book_list() throws IOException{
	
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int bookrack_id=2;
	int maxResult =PropertiesUtils.getSetting_page_result();
	
	PageInfo p=new PageInfo();
	
	
	p.setCount(this.getAdminService().countPageBookInfo(bookrack_id, maxResult));
	p.setPage(this.getPageInfo().getPage());
	mapjson.put("pageInfo", p);

	List<BookInfo>lList=this.getAdminService().findPageBookInfo(bookrack_id, maxResult, p.getPage());
	if(lList==null ||lList.size()==0)
		{
		mapjson.put("bookInfoDtos", null);
		this.setJsonDataMap(mapjson);
		return SUCCESS;
		}
	
	List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
	for(BookInfo r :lList){
		BookInfoDto dto=new BookInfoDto();
		BookTypeDto b=new BookTypeDto();
		BeanUtilsEx.copyProperties(b, r.getBookType());
		BeanUtilsEx.copyProperties(dto,r );
		dto.setBookTypeDto(b);
		dtoList.add(dto);
		
	}
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}
/**
 * 进入book_type_control 的jsp
 * @return
 * @throws Exception 
 */
public String book_control_book_type_control() throws Exception{
	List<BookType> l=this.getAdminService().findALLDomain(BookType.class);
	if(l!=null){
		List<BookTypeDto> dtos=new ArrayList<BookTypeDto>();
		for(BookType b: l ){
			BookTypeDto dto =new BookTypeDto();
			BeanUtilsEx.copyProperties(dto, b);
			if(b.getBookInfos()!=null){
			dto.setSize(b.getBookInfos().size());
			}
			else{
				dto.setSize(0);
			}
			dtos.add(dto);
		}
		this.setBookTypeDtos(dtos);
	}
	return FORWARD;
}
/**
 * 删除图书类型
 * @return
 */
public String book_control_delete_book_type(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookTypeDto bdto=this.getBookTypeDto();
	if(bdto==null ||bdto.getBotyId()==null ||bdto.getBotyId().trim().length()==0){
		mapjson.put("result", false);
		mapjson.put("info", "参数有误");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	
	this.setJsonDataMap(this.getAdminService().txDeleteBookType(bdto.getBotyId()));
	return SUCCESS;
}
/**
 * 
 * 修改图书分类
 * @return
 */
public String book_control_modify_book_type(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookTypeDto bdto=this.getBookTypeDto();
	if(bdto==null ||bdto.getBotyId()==null ||bdto.getBotyId().trim().length()==0 ||bdto.getBookTypeName()==null ||bdto.getBookTypeName().trim().length()==0){
		mapjson.put("result", false);
		mapjson.put("info", "参数有误");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	if(this.getAdminService().txModifyBookType(bdto)){
		mapjson.put("result", true);
		mapjson.put("info", "修改成功");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	mapjson.put("result", false);
	mapjson.put("info", "参数有误");
	this.setJsonDataMap(mapjson);
	return SUCCESS;
}
/**
 * 添加图书类型
 * @return
 */
public  String book_control_add_book_type(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	BookTypeDto bdto=this.getBookTypeDto();
	if(bdto==null  ||bdto.getBookTypeName()==null ||bdto.getBookTypeName().trim().length()==0){
		mapjson.put("result", false);
		mapjson.put("info", "参数有误");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	this.getAdminService().txSaveBookType(bdto.getBookTypeName());
	mapjson.put("result", true);
	mapjson.put("info", "添加成功");
	this.setJsonDataMap(mapjson);
	return SUCCESS;
}
/**
 * 进入目录查询图书页面
 * @return
 */
public String book_control_keyword_book_search(){
	this.getSession().remove("keySearchBookInfoCondition");
	return FORWARD;
}
/**
 * 进入type_book_search页面
 * @return
 * @throws Exception 
 */
public String book_control_type_book_search() throws Exception{
	List<BookType> l=this.getAdminService().findALLDomain(BookType.class);
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
public String book_control_findBookInfoByBookType() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int page=this.getPageInfo().getPage();
	String id=this.getBookTypeDto().getBotyId();
	if(id==null ||id.trim().length()==0 ){
		throw new Exception("参数出错");
	}
	int maxResult =PropertiesUtils.getSetting_page_result();
	PageInfo p=new PageInfo();
	p.setCount(this.getAdminService().countPageBookInfo(id, maxResult));
	if(page>p.getCount()){
		p.setPage(p.getCount());
	}
	else{
		p.setPage(page);
	}

	mapjson.put("pageInfo",p);
List<BookInfo> lList=	this.getAdminService().findPageBookInfo(id, maxResult, p.getPage());
	if(lList==null ||lList.size()==0)
	{
	mapjson.put("bookInfoDtos", null);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	}

List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
for(BookInfo r :lList){
	BookInfoDto dto=new BookInfoDto();
	
	if(r.getBookrack().getBookrackId()==1){
		dto.setBookUnuse(true);
	}
	else{
		dto.setBookUnuse(false);
	}
	BeanUtilsEx.copyProperties(dto,r );
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
public String book_control_findKeyBookInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<String> jsonList=null;
	List<Integer> jsonList1=null;
	Map<String,Object> map =new HashMap<String,Object>();
	map.put("bookIsDelete", false);
	if(this.getBookInfoDto()==null){
		throw new Exception("参数错误");
	}
	BookInfoDto bdto=this.getBookInfoDto();
	if(bdto.getBookId()!=null &&bdto.getBookId().trim().length()!=0){
		jsonList =this.getAdminService().findKeyDomain(BookInfo.class, String.class, "bookId",bdto.getBookId(),map);
		
	}
	else if(bdto.getBookName()!=null &&bdto.getBookName().trim().length()!=0){
		jsonList =this.getAdminService().findKeyDomain(BookInfo.class, String.class, "bookName",bdto.getBookName(),map);
	}
	else if(bdto.getId()!=null &&bdto.getId()!=0){
		jsonList1 =this.getAdminService().findKeyDomain(BookInfo.class, Integer.class, "id",bdto.getId(),map);
	}
	else if(bdto.getBookWriter()!=null &&bdto.getBookWriter().trim().length()!=0){
		jsonList =this.getAdminService().findKeyDomain(BookInfo.class, String.class, "bookWriter",bdto.getBookWriter(),map);
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
public String book_control_search_page_keyboardBookInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<BookInfo> bookInfos=null;
	PageInfo pageInfo=null;
	BookInfoDto bdto=null;
	int count=0;
	int maxResult=PropertiesUtils.getSetting_page_result();
	if(this.getBookInfoDto()==null){
		bdto=(BookInfoDto) this.getSession().get("keySearchBookInfoCondition");
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
		bookInfos =this.getAdminService().findBookInfoBykey( "bookId",bdto.getBookId(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countBookInfoBykey("bookId",bdto.getBookId(), maxResult);
	}
	else if(bdto.getBookName()!=null &&bdto.getBookName().trim().length()!=0){
		bookInfos =this.getAdminService().findBookInfoBykey("bookName",bdto.getBookName(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countBookInfoBykey("bookName",bdto.getBookName(), maxResult);
	}
	else if(bdto.getId()!=null &&bdto.getId()!=0){
		bookInfos =this.getAdminService().findBookInfoBykey("id",bdto.getId(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countBookInfoBykey("id",bdto.getId(), maxResult);
	}
	else if(bdto.getBookWriter()!=null &&bdto.getBookWriter().trim().length()!=0){
		bookInfos =this.getAdminService().findBookInfoBykey("bookWriter",bdto.getBookWriter(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countBookInfoBykey("bookWriter",bdto.getBookWriter(), maxResult);
	}
	pageInfo.setCount(count);
	
	mapjson.put("pageInfo", pageInfo);
	

List<BookInfoDto> dtoList=new ArrayList<BookInfoDto>();
for(BookInfo r :bookInfos){
	BookInfoDto dto=new BookInfoDto();
	
	if(r.getBookrack().getBookrackId()==1){
		dto.setBookUnuse(true);
	}
	else{
		dto.setBookUnuse(false);
	}
	BeanUtilsEx.copyProperties(dto,r );
	BookTypeDto t=new BookTypeDto();
	BeanUtilsEx.copyProperties(t, r.getBookType());
	dto.setBookTypeDto(t);
	dtoList.add(dto);
	
}
	
	
	mapjson.put("bookInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	
	
	
	return SUCCESS;
	
}
/*进入reader_control*/
public String reader_control_reader_control() throws IOException{
	int maxResult=PropertiesUtils.getSetting_page_result();
	PageInfo page=new PageInfo();
	page.setCount(this.getAdminService().countPageReader(maxResult, null));
	page.setPage(1);
	this.setPageInfo(page);
	List<Reader> li=this.getAdminService().findPageReader(null, maxResult, 1);
	List<ReaderDto> readerDtos=new ArrayList<ReaderDto>();
	if(li!=null)
		{
		for(Reader r :li){
		
		ReaderDto rdto=new ReaderDto();
		BeanUtilsEx.copyProperties(rdto, r);
		ReaderTypeDto readerTypeDto=new ReaderTypeDto();
		ReaderType ret=r.getReaderType();
		BeanUtilsEx.copyProperties(readerTypeDto, ret);
		rdto.setReaderTypeDto(readerTypeDto);
		readerDtos.add(rdto);
		
	}
		}
	this.setReaderDtos(readerDtos);
	
	return FORWARD;
	
}
//分页加载读者列表
public String reader_control_pageReaderInfo() throws IOException{
	 Map<String ,Object> mapjson=new HashMap<String, Object>();
	int maxResult=PropertiesUtils.getSetting_page_result();
	PageInfo page=this.getPageInfo();
	if(page==null){
		page=new PageInfo();
		page.setPage(1);
				
	}
	int count =this.getAdminService().countPageReader(maxResult, null); 
	page.setCount(count);
	if(page.getPage()>count){
		page.setPage(count);
	}
	this.setPageInfo(page);
	mapjson.put("pageInfo", page);
	List<Reader> li=this.getAdminService().findPageReader(null, maxResult, page.getPage());
	List<ReaderDto> readerDtos=new ArrayList<ReaderDto>();
	if(li!=null){
		for(Reader r :li){
		
		ReaderDto rdto=new ReaderDto();
		BeanUtilsEx.copyProperties(rdto, r);
		ReaderTypeDto readerTypeDto=new ReaderTypeDto();
		ReaderType ret=r.getReaderType();
		BeanUtilsEx.copyProperties(readerTypeDto, ret);
		rdto.setReaderTypeDto(readerTypeDto);
		readerDtos.add(rdto);
		
	}
		}
	mapjson.put("readerInfoDtos", readerDtos);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	
}
//禁用激活读者
public String reader_control_modify_reader_is_status(){
	 boolean modify_status=this.isModify_status();
	 Map<String ,Object> mapjson=new HashMap<String, Object>();
	 String account=this.getAccount();
	 this.getAdminService().txmodify_status(account,modify_status,false);
	 if(modify_status){
		 mapjson.put("info", "该用户已经被激活");
	 }else{
		 mapjson.put("info", "该用户已经被停用");
	 }
mapjson.put("result", true);
this.setJsonDataMap(mapjson);

	return SUCCESS;
}
//批量禁用激活读者
public String reader_control_modify_more_reader_is_status(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String str=this.getAccountListStr();
	 boolean modify_status=this.isModify_status();
	 String[] arrayStr=str.split(",");
	 mapjson=this.getAdminService().txMoremodify_status(arrayStr, ((LibraryAdminDto)this.getSession().get("Liadmin")).getAccount(), modify_status, false);
	
mapjson.put("result", true);
this.setJsonDataMap(mapjson);

	return SUCCESS;
}

//删除读者
public String reader_control_delete_reader() throws Exception{
	
	
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	 String account=this.getAccount();
		 
		 mapjson=	this.getAdminService().txdelete_user(false, account);
		 this.setJsonDataMap(mapjson);
		 
		return SUCCESS;
		 
}
//批量删除
public String reader_control_more_delete_reader(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String str=this.getAccountListStr();
	if(str==null||str.trim().length()==0){
		 mapjson.put("info", "请勾选要删除的管理员");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	else{
		String[] arrayStr=str.split(",");
		 
		
		 mapjson=	this.getAdminService().txMoreDelete_user(false, arrayStr,null);
	}
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}
//进入user_info_setting
public String reader_control_reader_info_setting() throws Exception{
		String account = this.getAccount();
		if(account==null||account.trim().length()==0){
			throw new Exception("参数错误");
		}
		Reader r=this.getAdminService().findAnReader("account",account);
		if(r==null){
			throw new Exception("参数错误");
		}
		else{
			ReaderDto rdto=new ReaderDto();
			BeanUtilsEx.copyProperties(rdto, r);
			ReaderTypeDto readerTypeDto=new ReaderTypeDto();
			ReaderType ret=r.getReaderType();
			BeanUtilsEx.copyProperties(readerTypeDto, ret);
			rdto.setReaderTypeDto(readerTypeDto);
			this.setReaderDto(rdto);
			List<ReaderType> li=this.getAdminService().findALLDomain(ReaderType.class);
			List<ReaderTypeDto> rtdto=new ArrayList<ReaderTypeDto>();
			for(ReaderType rt :li){
				ReaderTypeDto dto=new ReaderTypeDto();
				BeanUtilsEx.copyProperties(dto, rt);
				rtdto.add(dto);
			}
			this.setReaderTypeDtos(rtdto);
			
		}
		return FORWARD;
	}
public String reader_control_add_reader_info() throws Exception{
	
	String account=PropertiesUtils.getSettingProperties("readerNumber");
	
	List<ReaderType> li=this.getAdminService().findALLDomain(ReaderType.class);
	List<ReaderTypeDto> rtdto=new ArrayList<ReaderTypeDto>();
	for(ReaderType rt :li){
		ReaderTypeDto dto=new ReaderTypeDto();
		BeanUtilsEx.copyProperties(dto, rt);
		rtdto.add(dto);
	}
	this.setAccount(account);
	this.setReaderTypeDtos(rtdto);
	return FORWARD;
	
	
}
//添加reader
public String reader_control_addReader() throws Exception{
	
	String account=PropertiesUtils.getSettingProperties("readerNumber");
	String passwd=PropertiesUtils.getSettingProperties("defaultReaderPasswd");
	ReaderDto rdto=this.getReaderDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(rdto.getUsersName()==null || rdto.getUsersName().trim().length()==0 ){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	if(rdto.getIdCard()==null){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	int len=rdto.getIdCard().trim().length();
	if(len!=18){
		 mapjson.put("info", "身份证必须18位");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
	
	Reader r =new Reader();
	r.setAccount(account);
	r.setIdCard(rdto.getIdCard());
	r.setPasswd(MD5Encrypt.encryptByMD5(passwd));
	if(this.getReaderTypeDto()==null ||this.getReaderTypeDto().getId()==0){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	ReaderType readerType=this.getAdminService().findAnReaderType(this.getReaderTypeDto().getId());
	if(readerType==null){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	r.setReaderType(readerType);
	r.setRisStatus(true);
	r.setUsersName(rdto.getUsersName());
	
	
	StringBuilder str=new StringBuilder("reader/");
	str.append(account);
	str.append(".jpg");
	r.setPic(str.toString());
	
	this.getAdminService().txAddReader(r);
	 mapjson.put("info", "添加成功");
	 mapjson.put("result", true);
	 mapjson.put("account", r.getAccount());
	 mapjson.put("passwd",passwd );
	 mapjson.put("userName", r.getUsersName());
	 mapjson.put("id_card", r.getIdCard());
	 mapjson.put("pic",r.getPic());
	 this.setJsonDataMap(mapjson);
	 
	 PropertiesUtils.modifySettingProperties("readerNumber", (Integer.parseInt(account)+1)+"");
	 return SUCCESS;
	
	
	
	
}
/**
 * 进入keyword_user_search
 * @return
 */
public String reader_control_keyword_user_search(){
	this.getSession().remove("keySearchReaderInfoCondition");
	return FORWARD;
}


public String reader_control_search_page_keyboardReaderInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<Reader> readers=null;
	PageInfo pageInfo=null;
	ReaderDto bdto=null;
	int count=0;
	int maxResult=PropertiesUtils.getSetting_page_result();
	if(this.getReaderDto()==null){
		bdto=(ReaderDto) this.getSession().get("keySearchReaderInfoCondition");
		if(bdto==null){
			throw new Exception("参数错误");
		}
	}else{
		bdto=this.getReaderDto();
		this.getSession().put("keySearchReaderInfoCondition", bdto);
	}
	if(this.getPageInfo()==null){
		pageInfo=new PageInfo();
		pageInfo.setPage(1);
	}else{
		pageInfo=this.getPageInfo();
	}
	
	
	if(bdto.getUsersName()!=null &&bdto.getUsersName().trim().length()!=0){
		readers =this.getAdminService().findReaderInfoBykey("usersName", bdto.getUsersName(),pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countReaderInfoBykey("usersName",bdto.getUsersName(), maxResult);
	}
	else if(bdto.getIdCard()!=null &&bdto.getIdCard().trim().length()!=0){
		readers =this.getAdminService().findReaderInfoBykey("idCard",bdto.getIdCard(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countReaderInfoBykey("idCard",bdto.getIdCard(), maxResult);
	}
	else if(bdto.getAccount()!=null &&bdto.getAccount().trim().length()!=0){
		readers =this.getAdminService().findReaderInfoBykey("account",bdto.getAccount(), pageInfo.getPage(), maxResult);
		 count=this.getAdminService().countReaderInfoBykey("account",bdto.getAccount(), maxResult);
	}
	pageInfo.setCount(count);
	
	mapjson.put("pageInfo", pageInfo);
	

List<ReaderDto> dtoList=new ArrayList<ReaderDto>();
if(readers!=null ||readers.size()>0){
	

for(Reader r :readers){

	ReaderDto rdto=new ReaderDto();
	BeanUtilsEx.copyProperties(rdto, r);
	ReaderTypeDto readerTypeDto=new ReaderTypeDto();
	ReaderType ret=r.getReaderType();
	BeanUtilsEx.copyProperties(readerTypeDto, ret);
	rdto.setReaderTypeDto(readerTypeDto);
	dtoList.add(rdto);
	
}
	
}	
	mapjson.put("readerInfoDtos", dtoList);
	this.setJsonDataMap(mapjson);
	
	
	
	return SUCCESS;
	
}
/**
 * 加载关联词汇
 * @return
 * @throws Exception 
 */
public String reader_control_control_findKeyReaderInfo() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	List<String> jsonList=null;
	Map<String,Object> map =new HashMap<String,Object>();
	if(this.getReaderDto()==null){
		throw new Exception("参数错误");
	}
	ReaderDto rdto=this.getReaderDto();
	
	
	if(rdto.getUsersName()!=null &&rdto.getUsersName().trim().length()!=0){
		 jsonList =this.getAdminService().findKeyDomain(Reader.class, String.class, "usersName",rdto.getUsersName(),map);
	}
	else if(rdto.getIdCard()!=null &&rdto.getIdCard().trim().length()!=0){
		 jsonList =this.getAdminService().findKeyDomain(Reader.class, String.class, "idCard",rdto.getIdCard(),map);
	}
	else if(rdto.getAccount()!=null &&rdto.getAccount().trim().length()!=0){
		 jsonList =this.getAdminService().findKeyDomain(Reader.class, String.class, "account",rdto.getAccount(),map);
	}
	
	
	
	
	if(jsonList!=null){
		HashSet<String> h=new HashSet<String>(jsonList);
		jsonList.clear();
		jsonList.addAll(h);
		mapjson.put("resultList", jsonList);
	}
	
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	
}
//修改读者信息
public String reader_control_modify_reader(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	ReaderDto rdto=this.getReaderDto();
	if(rdto==null){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	if(rdto.getUsersName()==null || rdto.getUsersName().trim().length()==0 ||rdto.getIdCard()==null ||rdto.getReaderTypeDto()==null ||rdto.getReaderTypeDto().getId()==0){
		
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	int len=rdto.getIdCard().trim().length();
	if(len!=18){
		 mapjson.put("info", "身份证必须18位");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
	
	ReaderType readerType=this.getAdminService().findAnReaderType(rdto.getReaderTypeDto().getId());
	if(readerType==null){
		 mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	if(!this.getAdminService().txModifyReader(rdto)){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	else{
		mapjson.put("info", "修改成功");
		 mapjson.put("result", true);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
}
//重置读者·密码
public String reader_control_reset_reader_passwd() throws Exception{
	
	Map<String ,Object> mapjson=new HashMap<String ,Object>();

	String account =this.getAccount();
	if(account.trim().length()!=0){
		this.getAdminService().txModifyReaderPasswd(account);
		mapjson.put("info", "重置成功");
		 mapjson.put("result", true);
		 this.setJsonDataMap(mapjson);
	}
	else{
		
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 
	}
	
	return SUCCESS;
}

/**
 * 进入borrow_number_about_user_type.jsp
 * @return
 * @throws Exception 
 */
public String readType_control_borrow_number_about_user_type() throws Exception{
	List<ReaderType> li=this.getAdminService().findALLDomain(ReaderType.class);
	List<ReaderTypeDto> lidto=new ArrayList<ReaderTypeDto>();
	if(li!=null){
		for(ReaderType rt :li){
			ReaderTypeDto tdto=new ReaderTypeDto();
			BeanUtilsEx.copyProperties(tdto, rt);
			lidto.add(tdto);
		}
	}
	
this.setReaderTypeDtos(lidto);
return  FORWARD;

}
/**
 * 删除指定的读者类型
 * @return
 */
public String readType_control_deleteReaderType(){
	ReaderTypeDto rdto=this.getReaderTypeDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(rdto==null||rdto.getId()==0){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	mapjson=this.getAdminService().txDeleteReaderType(rdto.getId());
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}

/**
 * 修改读者类型
 * @return
 */
public String readType_control_modifyReaderType(){
	ReaderTypeDto rdto=this.getReaderTypeDto();
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(rdto==null||rdto.getId()==0 ||rdto.getBorrow()==null||rdto.getBorrow()<0||rdto.getTypeName()==null||rdto.getTypeName().trim().length()<=0){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	mapjson=this.getAdminService().txModifyReaderType(rdto);
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
}
/**
 * 添加读者类型
 * @return
 */
public String readType_control_addReaderType(){
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	ReaderTypeDto rdto=this.getReaderTypeDto();
	
	if(rdto==null || rdto.getBorrow()==null||rdto.getBorrow()<0||rdto.getTypeName()==null||rdto.getTypeName().trim().length()<=0){
		mapjson.put("info", "参数有误");
		 mapjson.put("result", false);
		 this.setJsonDataMap(mapjson);
		 return SUCCESS;
	}
	
	this.getAdminService().txSaveReaderType(rdto);
	mapjson.put("info", "添加成功");
	 mapjson.put("result", true);
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
}
/**
 * 进入all_borrow_history.jsp
 * @return
 * @throws Exception 
 */
public String borrow_control_all_borrow_history() throws Exception{
	
	Date[] dates=TimeUtils.getWholeDaybyToday(new Date());
	this.getSession().put("adminBeginTime",new Timestamp(dates[0].getTime()) );
	this.getSession().put("adminEndTime",new Timestamp(dates[1].getTime() ));
	this.getSession().put("operationType", null);
	List<BorrowOperation> borrowOperations =this.getAdminService().findBorrowOperation(dates,1, null);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	PageInfo pageInfo = new PageInfo();
	pageInfo.setCount(this.getAdminService().countBorrowOperation(dates, null));
	pageInfo.setPage(1);
	this.setPageInfo(pageInfo);
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			Reader r= this.getAdminService().findAnReader("account", b.getReaderId());
			if(r!=null){
				ReaderDto readerDto=new ReaderDto();
				BeanUtilsEx.copyProperties(readerDto, r);
				dto.setReaderDto(readerDto);
			}
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
public String borrow_control_page_borrow_history() throws Exception{
	Map<String,Object> map=new HashMap<String,Object>();
	Timestamp begin=(Timestamp) this.getSession().get("adminBeginTime");
	Timestamp end =(Timestamp) this.getSession().get("adminEndTime");
	Boolean flag=(Boolean) this.getSession().get("operationType");
	PageInfo pageInfo = this.getPageInfo();
	
	if(begin == null ||end ==null||pageInfo ==null){
		throw new Exception("参数错误");
	}
	
	Date[] dates={begin,end};
	pageInfo.setCount(this.getAdminService().countBorrowOperation(dates, flag));
	
	List<BorrowOperation> borrowOperations =this.getAdminService().findBorrowOperation(dates,pageInfo.getPage(), flag);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			Reader r= this.getAdminService().findAnReader("account", b.getReaderId());
			if(r!=null){
				ReaderDto readerDto=new ReaderDto();
				BeanUtilsEx.copyProperties(readerDto, r);
				dto.setReaderDto(readerDto);
			}
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
public String borrow_contol_borrow_history_by_time() throws Exception{
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
	pageInfo.setCount(this.getAdminService().countBorrowOperation(dates, bool));
	pageInfo.setPage(1);
	map.put("pageInfo", pageInfo);
	List<BorrowOperation> borrowOperations =this.getAdminService().findBorrowOperation(dates,1,bool);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			Reader r= this.getAdminService().findAnReader("account", b.getReaderId());
			if(r!=null){
				ReaderDto readerDto=new ReaderDto();
				BeanUtilsEx.copyProperties(readerDto, r);
				dto.setReaderDto(readerDto);
			}
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
public String borrow_control_borrow_history_by_type() throws Exception{
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
	List<BorrowOperation> borrowOperations =this.getAdminService().findBorrowOperation(dates,1,bool);
	List<BorrowOperationDto> borrowOperationDtos=new ArrayList<BorrowOperationDto>();
	if(borrowOperations!=null){
		for(BorrowOperation bo :borrowOperations ){
			BorrowOperationDto borrowOperationDto=new BorrowOperationDto();
			BeanUtilsEx.copyProperties(borrowOperationDto, bo);
			BorrowInfo b=bo.getBorrowInfo();
			BorrowInfoDto dto=new BorrowInfoDto();
			BeanUtilsEx.copyProperties(dto, b);
			List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
			if(bookInfos!=null){
				BookInfoDto bookInfoDto =new BookInfoDto();
				BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
				dto.setBookInfoDto(bookInfoDto);
			}
			Reader r= this.getAdminService().findAnReader("account", b.getReaderId());
			if(r!=null){
				ReaderDto readerDto=new ReaderDto();
				BeanUtilsEx.copyProperties(readerDto, r);
				dto.setReaderDto(readerDto);
			}
			borrowOperationDto.setBorrowInfoDto(dto);
			borrowOperationDtos.add(borrowOperationDto);
			
		}
	}
	PageInfo pageInfo = new PageInfo();
	pageInfo.setCount(this.getAdminService().countBorrowOperation(dates, bool));
	pageInfo.setPage(1);
	map.put("pageInfo", pageInfo);
		map.put("borrowOperationDtos", borrowOperationDtos);
		this.setJsonDataMap(map);
	
	return SUCCESS;
	
	
}
/**
 * 进入reader_borrow_history.jsp
 * @return
 * @throws Exception 
 */
public String borrow_control_reader_borrow_history() throws Exception{
	String account = this.getAccount();
	int status =0;
	if("".equals(account)){
		throw new Exception("参数错误");
	}
	this.getSession().put("adminBorrowInfoByReaderConReaderId", account);
	this.getSession().put("adminBorrowInfoByReaderConStatus", status);
	PageInfo pageInfo =new PageInfo();
	pageInfo.setCount(this.getAdminService().countPageBorrowInfo(account, status));
	pageInfo.setPage(1);
	this.setPageInfo(pageInfo);
	Reader r=	this.getAdminService().findAnReader("account", account);
	if(r==null){
		throw new Exception("参数有误");
	}
	
		ReaderDto rdto=new ReaderDto();
		BeanUtilsEx.copyProperties(rdto, r);
		this.setReaderDto(rdto);
		
	List<BorrowInfo> borrowInfos=this.getAdminService().findPageBorrowInfo(1, account, status);
	List<BorrowInfoDto> borrowInfoDtos=new ArrayList<BorrowInfoDto>();
	for(BorrowInfo b : borrowInfos){
		BorrowInfoDto dto=new BorrowInfoDto();
		BeanUtilsEx.copyProperties(dto, b);
		List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
		if(bookInfos!=null){
			BookInfoDto bookInfoDto =new BookInfoDto();
			BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
			dto.setBookInfoDto(bookInfoDto);
		}
		borrowInfoDtos.add(dto);
	}
	this.setBorrowInfoDtos(borrowInfoDtos);
	
	
	return FORWARD;
}
/**
 * 分页加载个人borrowinfo
 * @return
 * @throws Exception
 */
public String borrow_control_reader_page_borrow_history() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String account = (String) this.getSession().get("adminBorrowInfoByReaderConReaderId" );
	int status = (int) this.getSession().get("adminBorrowInfoByReaderConStatus");
	PageInfo pageInfo =getPageInfo();
	pageInfo.setCount(this.getAdminService().countPageBorrowInfo(account, status));
	pageInfo.setPage(1);
	Reader r=	this.getAdminService().findAnReader("account", account);
	if(r==null){
		throw new Exception("参数有误");
	}	
	List<BorrowInfo> borrowInfos=this.getAdminService().findPageBorrowInfo(pageInfo.getPage(), account, status);
	List<BorrowInfoDto> borrowInfoDtos=new ArrayList<BorrowInfoDto>();
	for(BorrowInfo b : borrowInfos){
		BorrowInfoDto dto=new BorrowInfoDto();
		BeanUtilsEx.copyProperties(dto, b);
		List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
		if(bookInfos!=null){
			BookInfoDto bookInfoDto =new BookInfoDto();
			BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
			dto.setBookInfoDto(bookInfoDto);
		}
		borrowInfoDtos.add(dto);
	}
	mapjson.put("pageInfo", pageInfo);
	mapjson.put("borrowInfoDtos", borrowInfoDtos);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}
/**
 * 变化条件
 * @return
 * @throws Exception
 */
public String borrow_control_modfiy_con_borrowInfo_status() throws Exception{
	int status =this.getBorrowStatus();
	if(status!=0&&status!=1 &&status !=2){
		throw new Exception("参数错误");
	}
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	String account = (String) this.getSession().get("adminBorrowInfoByReaderConReaderId" );
	this.getSession().put("adminBorrowInfoByReaderConStatus", status);
	PageInfo pageInfo =new PageInfo();
	pageInfo.setCount(this.getAdminService().countPageBorrowInfo(account, status));
	pageInfo.setPage(1);
	Reader r=	this.getAdminService().findAnReader("account", account);
	if(r==null){
		throw new Exception("参数有误");
	}
	
	
		
	List<BorrowInfo> borrowInfos=this.getAdminService().findPageBorrowInfo(1, account, status);
	List<BorrowInfoDto> borrowInfoDtos=new ArrayList<BorrowInfoDto>();
	for(BorrowInfo b : borrowInfos){
		BorrowInfoDto dto=new BorrowInfoDto();
		BeanUtilsEx.copyProperties(dto, b);
		List<BookInfo> bookInfos =this.getAdminService().findAnBookInfo(b.getBookId());
		if(bookInfos!=null){
			BookInfoDto bookInfoDto =new BookInfoDto();
			BeanUtilsEx.copyProperties(bookInfoDto, bookInfos.get(0));
			dto.setBookInfoDto(bookInfoDto);
		}
		borrowInfoDtos.add(dto);
		
	}
	mapjson.put("pageInfo", pageInfo);
	mapjson.put("borrowInfoDtos", borrowInfoDtos);
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
	
}
/**
 * 进入borrow_control.jsp
 * @return
 */
public String borrow_control_borrow_control(){
	return FORWARD;
}

public String borrow_control_searchBookAndReader() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	int id =0;
	String account = null;
	
	if(this.getId()!=null && this.getId()>0){
		//输入了书id
		id =this.getId();
		 List<BookInfo> li=this.getAdminService().findAnBookInfo(id);
		 if(li!=null)
		 {
			 BookInfo r=li.get(0);
			 BookInfoDto dto=new BookInfoDto();
				BookTypeDto b=new BookTypeDto();
				BeanUtilsEx.copyProperties(b, r.getBookType());
				BeanUtilsEx.copyProperties(dto,r );
				dto.setBookTypeDto(b);
			mapjson.put("bookInfoDto", dto);
			mapjson.put("bookRackName", r.getBookrack().getBookrName());
			mapjson.put("stackRoom", r.getBookrack().getStackRoom().getSrName());
			
			 
		 }
		 
	}
	if( this.getAccount()!=null && this.getAccount().trim().length()!=0){
		//输入了读者account
		account =this.getAccount();
		Reader r= this.getAdminService().findAnReader("account", account);
		if(r!=null){
		ReaderDto rdto=new ReaderDto();
		BeanUtilsEx.copyProperties(rdto, r);
		ReaderTypeDto readerTypeDto=new ReaderTypeDto();
		ReaderType ret=r.getReaderType();
		BeanUtilsEx.copyProperties(readerTypeDto, ret);
		rdto.setReaderTypeDto(readerTypeDto);
		mapjson.put("readerDto", rdto);
		}
				
	}
	if((!mapjson.containsKey("readerDto")) && (!mapjson.containsKey("bookInfoDto"))){
		mapjson.put("info", "查询不到任何东西");
		 mapjson.put("result", false);
		
		
		 
		
	}
	else if(mapjson.containsKey("readerDto") && mapjson.containsKey("bookInfoDto")){
		String[] fieldNames ={"bookId","readerId","borrowStatus"};
		Object[] values={id,account,1};
		
		List<BorrowInfo> borrows=this.getAdminService().findBorrowInfo(fieldNames, values, null, null);
		if(borrows ==null){
			mapjson.put("borrowInfoID", null);
		}else{
			mapjson.put("borrowInfoID", borrows.get(0).getId());
		}
		mapjson.put("result", true);
		
		
		
		
	}else{
		mapjson.put("result", true);
	}
	 this.setJsonDataMap(mapjson);
	 return SUCCESS;
	
	
}

/**
 * 还书
 * @return
 * @throws Exception 
 */
public String borrow_control_return_book() throws Exception{
	Map<String,Object> map=new HashMap<String,Object>();
	if(this.getBorrowInfo()==null ||this.getBorrowInfo().getId()==null ||this.getBorrowInfo().getId().trim().length()<=0){
		map.put("result", false);
		map.put("info", "参数有误");
		this.setJsonDataMap(map);
		return SUCCESS;
	}
	BorrowInfo borrowInfo=this.getAdminService().findAnBorrowInfoById(this.getBorrowInfo().getId());
	if(borrowInfo==null){
		map.put("result", false);
		map.put("info", "参数有误");
		this.setJsonDataMap(map);
		return SUCCESS;
	}
	BookInfo bookInfo=this.getAdminService().findAnBookInfo(borrowInfo.getBookId()).get(0);
	bookInfo.setBookIsBorrow(false);
	borrowInfo.setEndTime(new Timestamp(System.currentTimeMillis()));
	borrowInfo.setBorrowStatus(2);
	
	BorrowOperation borrowOperation =new BorrowOperation();
	borrowOperation.setBorrowInfo(borrowInfo);
	Set<BorrowOperation> borrowOperations=borrowInfo.getBorrowOperations();
	borrowOperation.setId(UUID.randomUUID().toString().replace("-", ""));
	borrowOperation.setOperationTime(borrowInfo.getEndTime());
	borrowOperation.setOperationType(false);
	borrowOperations.add(borrowOperation);
	borrowInfo.setBorrowOperations(borrowOperations);
	this.getAdminService().txSaveBookInfo(bookInfo);
//	this.getAdminService().txSaveBorrowOperation(borrowOperation);
	this.getAdminService().txSaveBorrowInfo(borrowInfo);
	
	map.put("result", true);
	map.put("info", "还书成功");
	this.setJsonDataMap(map);
	return SUCCESS;
	
	
}
/**
 * 借书
 * @return
 * @throws Exception
 */
public String borrow_control_borrow_book() throws Exception{
	Map<String ,Object> mapjson=new HashMap<String ,Object>();
	if(this.getId()==null ||this.getId()<=0||this.getAccount()==null ||this.getAccount().trim().length()<=0){
		mapjson.put("result", false);
		mapjson.put("info", "参数有误");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	Reader r=this.getAdminService().findAnReader( "account", this.getAccount());
	Long count =this.getAdminService().countBorrowInfoByStatusOneORthreeAndByReader(r.getAccount());
	if(r.getReaderType().getBorrow()<=count){
		mapjson.put("result", false);
		mapjson.put("info", "借书数目上限，请先归还书籍");
		this.setJsonDataMap(mapjson);
		return SUCCESS;
	}
	
	
			List<BookInfo> bli= this.getAdminService().findAnBookInfo(this.getId());
			if(bli==null){
				mapjson.put("result", false);
				mapjson.put("info", "参数有误");
				this.setJsonDataMap(mapjson);
				return SUCCESS;
			}
			
			BookInfo bookInfo=bli.get(0);
			if(bookInfo.getBookIsDelete()||bookInfo.getBookrack().getBookrackId()==1){
				mapjson.put("result", false);
				mapjson.put("info", "图书已下架或者闲置，无法借出");
				this.setJsonDataMap(mapjson);
				return SUCCESS;
			}
			bookInfo.setBookIsBorrow(true);
		
	BorrowInfo borrowInfo=new BorrowInfo();
	

	Date d1=new Date(System.currentTimeMillis());

	
	borrowInfo.setBeginTime(new Timestamp( d1.getTime()));
	d1=TimeUtils.getWholeDaybyToday(d1)[0];
	borrowInfo.setBookId(bookInfo.getId());
	borrowInfo.setBoorowIsRenew(false);
	borrowInfo.setBorrowStatus(1);
	borrowInfo.setReaderId(r.getAccount());
	int day=Integer.valueOf(PropertiesUtils.getSettingProperties("borrowTime"));
	Calendar c=Calendar.getInstance();
	c.setTimeInMillis(d1.getTime() );
	c.add(Calendar.DATE, day);
	borrowInfo.setEndTime(new Timestamp(TimeUtils.getWholeDaybyToday(new Date(c.getTimeInMillis()))[1].getTime()));
	borrowInfo.setId(UUID.randomUUID().toString().replace("-", ""));
	BorrowOperation borrowOperation=new BorrowOperation();
	borrowOperation.setId(UUID.randomUUID().toString().replace("-", ""));
	borrowOperation.setBorrowInfo(borrowInfo);
	borrowOperation.setOperationTime(borrowInfo.getBeginTime());
	borrowOperation.setOperationType(true);
	Set<BorrowOperation> borrowOperations=new HashSet<BorrowOperation>();
	borrowOperations.add(borrowOperation);
	borrowInfo.setBorrowOperations(borrowOperations);
//	this.getAdminService().txSaveBorrowOperation(borrowOperation);
	this.getAdminService().txSaveBorrowInfo(borrowInfo);
	this.getAdminService().txSaveBookInfo(bookInfo);
	mapjson.put("result", true);
	mapjson.put("info", "借书成功");
	this.setJsonDataMap(mapjson);
	return SUCCESS;
	
}

//public String borrow_control_

}
