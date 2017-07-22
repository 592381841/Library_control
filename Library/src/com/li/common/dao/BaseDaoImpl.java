package com.li.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

public class BaseDaoImpl extends BaseHibernateDaoSuppprt implements IBaseDao {

	/**
	 * 查找某持久类的所有
	 * @return
	 */
	@Override
	public <T> List<T> findObjectsList(Class<T> clazz) {
		return super.findObjects(clazz);
	}
	/**
	 * 通过hql查询单个对象
	 * @param hql
	 * @param conditions
	 * @return
	 * 查询单个对象，如果没有返回null
	 */
	public <T> T findByObject(String hql,Object...conditions){
		return super.findByObject(hql, conditions);
	}
	
	/**
	 * 传入hql和condition以获得多个多个持久类对象
	 */
	public <T> List<T> findObjects(String hql,Object... conditions){
		return super.findObjects(hql, conditions);
	}
	/**
	 * 保存数据
	 * @param obj
	 * 
	 */
	public void save(Object obj){
		this.getHibernateTemplate().save(obj);
	}
	/**
	 * 通过某字段查询某pojo对象的数据
	 * 
	 * @param clazz
	 *            查询对象的类对象
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            对应字段名的value
	 * @return 
	 * 返回查询到对象集合（list），如果没有返回null
	 * 
	 */
	public <T> List<T> findObjectWithField(Class<T> clazz, String fieldName, Object value){
		return super.findObjectWithField(clazz, fieldName, value);
	}
	/**
	 * 	 通过某字段查询某字段的数据
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
	 * 返回查询到对象集合（list）
	 */
		public <T> List<T> findObjectWithFieldOrderByField(Class<T> clazz, String fieldName, Object value,String orderField,Object orderFieldValue){
			
			return super.findObjectWithFieldOrderByField(clazz, fieldName, value, orderField, orderFieldValue);
		}
		
		
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
		 * @throws Exception 
		 */
		@Override
		public <T> List<T> findObjectWithFields(Class<T> clazz, String[] fieldNames, Object[] values) throws Exception{
			return super.findObjectWithFields(clazz, fieldNames, values);
		}
		
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
		 * 返回查询到对象集合（list）
		 * @throws Exception 
		 */
			public <T> List<T> findObjectWithFieldOrderByFields(Class<T> clazz, String[] fieldNames, Object[] values,String orderField,Object orderFieldValue) throws Exception{
				return super.findObjectWithFieldsOrderByFields(clazz, fieldNames, values, orderField, orderFieldValue);
			}
		
		
		
		
		
		
		
		/**
		 * 更新数据
		 * @param o
		 * 持久类对象
		 */
		public void updateObject(Object o) {
			this.getHibernateTemplate().update(o);
			// TODO Auto-generated method stub
			
		}
		public void deleteObject(Object o){
			this.getHibernateTemplate().delete(o);
		}
	
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
		public  <T> List<T> findPageObject( Class<T> clazz,final int page,final int maxResult,final Map<String,Object> map,String sortField,String sortWay ){
			final StringBuilder strb=new StringBuilder("FROM "+clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+" obj where 1=1");
			final List<String> keyList = new ArrayList<String>();
			if(map!=null){
				keyList.addAll(map.keySet());
				for(String key :keyList){
					strb.append("  and obj."+key+"= :"+key.replace(".", ""));
				}
				
			}
			if(sortField!=null){
				if(sortWay==null){
					strb.append(" order by obj."+sortField.replace(".", "")+" asc");
				}
				else{
					strb.append(" order by obj."+sortField+" "+sortWay);
				}
			}
			
			
			return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
				
				@Override
				public List<T> doInHibernate(Session session) throws HibernateException {
					Query q=session.createQuery(strb.toString());
					for(String key : keyList){
						q.setParameter(key.replace(".", ""), map.get(key));
						
					}
					q.setMaxResults(maxResult);
					q.setFirstResult(maxResult*(page-1));
					
					return q.list();
				}

			
			});
		}
		
		
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
		public <T> List<T> findPageObject(final String hql,final int pageStart,final int PageSize,final Object ...condition){
			return super.findPageObjects(hql, pageStart, PageSize, condition);
		}
		@Override
		public <T> int countPage(Class<T> clazz, int maxResult,final Map<String,Object> map) {
			// TODO Auto-generated method stub
			StringBuilder s=new StringBuilder("select  COUNT(*)  from ");
			s.append(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1));
			s.append(" obj where 1=1");
			final List<String> keyList = new ArrayList<String>();
			if(map!=null){
				keyList.addAll(map.keySet());
				for(String key :keyList){
					s.append("  and obj."+key+"= :"+key.replace(".", ""));
				}
				
			}
			
	return  this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
				
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					Query q=session.createQuery(s.toString());
					for(String key : keyList){
						q.setParameter(key.replace(".", ""), map.get(key));
						
					}
				
					
					int count =((Long)q.list().get(0)).intValue() ;
					if (count % maxResult != 0) {
						return (count / maxResult + 1);
					} else {
						return count / maxResult;
					}
				}

			
			});

		}
		
		@Override
		public int countPage(String sql, int maxResult,Object...keyList) {
		
			
	return  this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
				
				@Override
				public Integer doInHibernate(Session session) throws HibernateException {
					Query q=session.createQuery(sql.toString());
					for(int i=0;i<keyList.length;i++){
						q.setParameter(i, keyList[i]);
					}
				
					
					int count =((Long)q.list().get(0)).intValue() ;
					if (count % maxResult != 0) {
						return (count / maxResult + 1);
					} else {
						return count / maxResult;
					}
				}

			
			});

		}
		
		
		
		
		
		@Override
		public boolean delUpdateObjects(String hql, Object... objects) {
			// TODO Auto-generated method stub
			return this.updateObjects(hql, objects);
		}
		
	
}
