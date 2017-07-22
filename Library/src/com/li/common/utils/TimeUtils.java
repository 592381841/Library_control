package com.li.common.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	 /**
	  * 根据时间获取当天的0到24小时
	  * @param d
	  * @return
	  */
	 public static Date[] getWholeDaybyToday(Date d){
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			Date[] dates =new Date[2];
			
			 c.set(Calendar.HOUR_OF_DAY, 0);
			  c.set(Calendar.SECOND, 0); 
			  c.set(Calendar.MINUTE, 0); 
			  c.set(Calendar.MILLISECOND, 0); 
			  dates[0]=c.getTime();
			  
			  c.set(Calendar.HOUR_OF_DAY, 23);
			  c.set(Calendar.SECOND, 59); 
			  c.set(Calendar.MINUTE, 59); 
			  c.set(Calendar.MILLISECOND, 0); 
			  dates[1]=c.getTime();
			  return dates;
	 }

}
