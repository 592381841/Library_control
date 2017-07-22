package com.text;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.li.common.domain.Bookrack;
import com.li.common.domain.StackRoom;
import com.li.common.service.ICommonService;
import com.li.service.admin.IAdminService;
import com.li.service.users.IUsersService;

/**

1、需要junit4和spring-test库（配置运行方式是：使用spring单元测试的方式）
*/
@RunWith(SpringJUnit4ClassRunner.class)
/**

转载一个spring的配置文件
路径是以classpath开始的（Src下）
*/
@ContextConfiguration("/spring-config.xml")
public class UserInfoBoTest {
/**
注入测试的对象
使用@Resource（该便签在struts中使用是失败的）
就可以不需要写get set方法（属性名要与spring配置文件一致）

*/
	@Resource
	private IAdminService userInfoBo;
	
	@Test
	public void queryByAgeTest() throws NoSuchAlgorithmException{

//	StackRoom s=new StackRoom();
//	s.setSrName("211");
//	s.setSrAddress("书库三");
////	s.setId(5);
////	s.setBookracks(new HashSet<Bookrack>());
//	try{s
		 System.out.println(userInfoBo.countPageStackRoomList(10, null));
		 System.out.println(userInfoBo.countPageStackRoomList(10, null));
		 System.out.println(userInfoBo.countPageStackRoomList(10, null));
//	}
//	catch(Exception e){
//		e.printStackTrace();
//	}
	}
	
}
