package com.li.common.utils;

public class LibrarySystemHardCodeUtils {
	public static String users_is_status(boolean is_status){
		if(is_status){
			return "正常";
		}else {
			return "停用";
		}
	}

}
