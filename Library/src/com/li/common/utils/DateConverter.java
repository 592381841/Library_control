package com.li.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * 完成String到Date类型的转换功能（用于BeanUtilsEx扩展类）
 * 
 */
public class DateConverter implements Converter {
	public final static int YEAR_MONTH_DAY = 1;
	public final static int FULL_DATE = 2;	
	public final static int HOUR_MINUTE_SECOND = 3;	

	@Override
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		} else {
			String stringValue = value.toString().trim();
			SimpleDateFormat df;
			if (stringValue.length() == 0) {
				return null;
			}
			try {
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.parse(stringValue);
			} catch (ParseException e) {
				try {
					df = new SimpleDateFormat("yyyy-MM-dd");
					return df.parse(stringValue);
				} catch (ParseException ex) {
					//ex.printStackTrace();
					return null;
				}
			}
		}
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @param type
	 * @return
	 */
	public static String convertToString(Date date, int type) {
		SimpleDateFormat df;
		if (type == YEAR_MONTH_DAY) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		} else if (type == FULL_DATE) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		} else if (type == HOUR_MINUTE_SECOND) {
			df = new SimpleDateFormat("HH:mm:ss");
			return df.format(date);
		}else {
			return date.toString();
		}
	}
}
