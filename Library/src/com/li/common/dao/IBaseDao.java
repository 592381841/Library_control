package com.li.common.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {
	/**
	 * 查找某持久类的所有
	 * @return
	 * 如果没有返回null
	 */
	public <T> List<T> findObjectsList(Class<T> clazz);
	
	/**
	 * 通过hql查询单个对象
	 * @param hql
	 * @param conditions
	 * @return
	 * 查询单个对象，如果没有返回null
	 */
	public <T> T findByObject(String hql,Object...conditions);

	/**
	 * 传入hql和condition以获得多个持久类对象
	 * @param hql
	 * hql语句
	 * @param conditions
	 * 条件
	 * @return
	 * 如果没有返回null
	 */
	public <T> List<T> findObjects(String hql,Object... conditions);
	/**
	 * 保存数据
	 * @param obj
	 * 
	 */
	public void save(Object obj);
	
	
	
	/**
	 * 通过某字段查询某pojo对象的数据[单个，无序]
	 * 
	 * @param clazz
	 *            查询对象的类对象
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            对应字段名的value
	 * @return 返回查询到对象集合（list）如果没有，返回null
	 */
	public <T> List<T> findObjectWithField(Class<T> clazz, String fieldName, Object value);
	
	/**
	 * 	 通过某字段查询某字段的数据[单个条件 有序]
	 * @param clazz
	 * 查询对象的类对象
	 * @param fieldName
	 * 条件字段名
	 * @param value
	 * 条件字段名对应的value
	 * @param orderField
	 * 排序字段名
	 * @param orderFieldValue
	 * 排序字段名对应的value
	 * @return
	 * 返回查询到对象集合（list）如果没有，返回null
	 */
		public <T> List<T> findObjectWithFieldOrderByField(Class<T> clazz, String fieldName, Object value,String orderField,Object orderFieldValue);
	
		
		
		/**
		 * 通过某字段查询某pojo对象的数据[多个，无序]
		 * 
		 * @param clazz
		 *            查询对象的类对象
		 * @param fieldName
		 *            字段名数组
		 * @param value
		 *            对应字段名的value数组
		 * @return 返回查询到对象集合（list）
		 */
		public <T> List<T> findObjectWithFields(Class<T> clazz, String[] fieldNames, Object[] values)throws Exception;
		
		/**
		 * 	 通过某字段查询某字段的数据[多个条件 有序]
		 * @param clazz
		 * 查询对象的类对象
		 * @param fieldName
		 * 条件字段名数组
		 * @param value
		 * 条件字段名对应的value数组
		 * @param orderField
		 * 排序字段名
		 * @param orderFieldValue
		 * 排序字段名对应的value
		 * @return
		 * 返回查询到对象集合（list）如果没有，返回null
		 */
			public <T> List<T> findObjectWithFieldOrderByFields(Class<T> clazz, String[] fieldNames, Object[] values,String orderField,Object orderFieldValue)throws Exception;
		
		
		
		
		
		/**
		 * 更新数据
		 * @param o
		 * 持久类对象
		 */
		public void updateObject(Object o);
		/**
		 * 删除数据
		 * @param o
		 */
		public void deleteObject(Object o);
		/**	
		 * 分页查找对象
		 * @param clazz
		 * 泛型
		 * @param page
		 * 页数
		 * @param maxResult
		 * 每页个数
		 * @param map
		 * 参数和参数名的键值对
		 * @param sortField
		 * 排序的字段[为null的时候自然排序]
		 * @param sortWay
		 * 排序方式[为null 默认为asc]
		 * 
		 * @return
		 */
		public  <T> List<T> findPageObject(Class<T> clazz,int page,int maxResult,Map<String,Object> map,String sortField,String sortWay );
		
		/**
		 * 
		 * @param <T>
		 * @param clazz 查找对象的clazz
		 * @param maxResult  每页个数
		 * @param condition  条件
		 * @return 总页数
		 */
		public <T> int countPage(Class<T> clazz,int maxResult,final Map<String,Object> map );
		
		/**
		 * 通过hql进行分页查找
		 * @param hql
		 * 语句
		 * @param pageStart
		 * 开始页面
		 * @param PageSize
		 * 每页几个
		 * @param condition
		 * 条件
		 * @return
		 */
		public <T> List<T> findPageObject(final String hql,final int pageStart,final int PageSize,final Object ...condition);
		/**
		 *更新/删除实体类
		 * @param hql
		 * @param objects
		 * @return
		 */
		public boolean delUpdateObjects(String hql,Object...objects);
		
		/**
		 * 
		 * @param sql 语句
		 * @param maxResult 最大值
		 * @param map 
		 * @return
		 */
		public int countPage(String sql, int maxResult,Object...keyList);
		
		
}
