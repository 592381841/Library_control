package com.li.common.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import com.li.common.domain.LibraryAdmin;
import com.li.common.domain.Reader;
import com.li.common.domain.ReaderType;
import com.li.common.dto.DownloadInformation;
import com.li.common.dto.LibraryAdminDto;
import com.li.common.dto.ReaderDto;
import com.li.common.dto.ReaderTypeDto;
import com.li.common.utils.BeanUtilsEx;
import com.li.common.utils.LibrarySystemHardCodeUtils;

public class CommonAction extends BaseCommonAction {
	

	//验证码
	public String verifyPic() throws IOException{
		this.setDownloadInformation(new DownloadInformation());	
		this.getDownloadInformation().setContentType("image/jpeg");
		this.getDownloadInformation().setInputStream(this.getCommonService().getImage());
		this.getSession().put("vertifyText",this.getCommonService().getText());
	
		
		return "success";
	}
	/**
	 * 登录
	 * userType 0为用户 1为管理员
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		String usersType=this.getUsersType();
		String	usersName=this.getUsersName();
		String 	passwd=this.getPasswd();
		String vertifyCode =this.getVertiyCode();
		
	boolean vertifybool=	this.getSession().get("vertifyText").toString().equalsIgnoreCase(vertifyCode);
	if(!vertifybool){
		this.getSession().put("information", "3");
		return "fail1";
	}
		if(usersName==null||passwd==null||usersName.trim().isEmpty()||passwd.trim().isEmpty()){
			this.getSession().put("information", "2");
			return "fail1";
		}
		
		if(usersType.equals("0")){
		
		Reader r=	this.getCommonService().readerLogin(usersName,passwd);

		if(r!=null)
		{
			if(!r.getRisStatus()){
				this.getSession().put("information", "4");
				this.getSession().put("usersName", usersName);
				return "fail1";
			}
			ReaderDto rdto=new ReaderDto();
			BeanUtilsEx.copyProperties(rdto, r);
			rdto.setRisStatus(r.getRisStatus());
			ReaderTypeDto rt=new ReaderTypeDto();
			ReaderType ret=r.getReaderType();
			BeanUtilsEx.copyProperties(rt, ret);
			rdto.setReaderTypeDto(rt);
			this.getSession().put("reader", rdto);
			
			return "success1";
		}
		else{
			this.getSession().put("information", "1");
			this.getSession().put("usersName", usersName);
			return "fail1";
		}
		}else if(usersType.equals("1")){
			LibraryAdmin a=this.getCommonService().adminLogin(usersName, passwd);
			if(a!=null){
				if(!a.getAisStatus()){
					this.getSession().put("information", "4");
					this.getSession().put("usersName", usersName);
					return "fail1";
				}
				LibraryAdminDto adt=new LibraryAdminDto();
				BeanUtilsEx.copyProperties(adt, a);
				this.getSession().put("Liadmin", adt);
				return "success2";
			}else{
				this.getSession().put("information", "1");
				this.getSession().put("usersName", usersName);
				return "fail1";
			}
			
		}
		else{
			throw new Exception("参数错误");
		}
	}
	/**
	 * 注销
	 * @return
	 */
	public String logout(){
		this.getSession().remove("Liadmin");
		this.getSession().remove("reader");
		this.getSession().put("information", "5 ");
		return "success";
	}

}
