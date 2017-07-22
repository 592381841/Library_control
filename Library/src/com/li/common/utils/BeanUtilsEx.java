package com.li.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * 该BeanUtils子类用来注册DateConverter，增強BeanUtils类的转换能力
 *解决字符串和date之间转换复制的问题
 */
public class BeanUtilsEx extends BeanUtils {
	static {
		ConvertUtils.register(new DateConverter(), Date.class);
	}

	public static void copyProperties(Object dest,
			Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}
	
	public static <T> T copyBean(Class<T> dest,Object orig){
		Object destObject = null;
		try {
			destObject = dest.newInstance();
			BeanUtilsEx.copyProperties(destObject, orig);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T)destObject;
	}

}
