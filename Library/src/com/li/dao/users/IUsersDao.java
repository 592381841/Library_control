package com.li.dao.users;

import java.util.List;
import java.util.Map;

import com.li.common.dao.IBaseDao;
import com.li.common.domain.BookInfo;

public interface IUsersDao extends IBaseDao {

	/**
	 * 分页查看图书
	 * @param fieldName
	 * @param fieldValue
	 * @param page
	 * @param maxResult
	 * @param map
	 * @param sortField
	 * @param sortWay
	 * @return
	 */
	public List<BookInfo> findPageBookInfoByCondition( int page, int maxResult,
			Map<String, Object> map, String sortField, String sortWay);
	
	public int countPageBookInfoByCondition(int maxResult,Map<String, Object> map);

}
