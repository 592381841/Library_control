package com.li.common.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseHibernateDaoSuppprt extends HibernateDaoSupport {


		/***
		 * 无条件查看pojo对象集合的所有数据(不分页)[无序]
		 * @param clazz
		 * pojo的类对象
		 * @return
		 */
		public <T> List<T> findObjects(Class<T> clazz){
			String className = clazz.getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			List<T> li=(List<T>) this.getHibernateTemplate().find("from "+className+" obj");
			if(li.size()==0)
			{
				return null;
			}
			else{
				return li;
			}
			
		
		}
		
		/***
		 * 无条件查看pojo对象的所有数据（不分页）[有序]
		 * @param clazz
		 * 该pojo类对象
		 * @return
		 */
		public <T> List<T> findObjectsOrderByField(Class<T> clazz,String orderField,Object orderFieldValue){
			String className = clazz.getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			List<T> li=(List<T>) this.getHibernateTemplate().find("from "+className+" obj order by obj."+orderField+" "+orderFieldValue);
			if(li.size()==0){
				return null;
			}else{
				return li;
			}
		}
		
		
	/**
	 * 通过某字段（单）查询某pojo对象的数据[有一个条件][无序]
	 * 
	 * @param clazz
	 *            查询对象的类对象
	 * @param fieldName
	 *            字段名
	 * @param value
	 *            对应字段名的value
	 * @return 返回查询到对象集合（list）
	 */
	public <T> List<T> findObjectWithField(Class<T> clazz, String fieldName, Object value) {
		String className = clazz.getName();
		className = className.substring(className.lastIndexOf(".") + 1);
		String hql = "from " + className + " obj where obj." + fieldName + "=:" + fieldName.replace(".", "");
		List<T> li = (List<T>) this.getHibernateTemplate().findByNamedParam(hql, fieldName.replace(".", ""), value);
		if (li.size() == 0)
			return null;
		else
			return li;

	}
	
	
/**
 * 	 通过某字段查询某pojo对象的数据[有一个条件][有序]
 * @param clazz
 * 查询对象的类对象
 * @param fieldName
 * 条件字段名
 * @param value
 * 条件字段名对应的value
 * @param orderField
 * 排序字段名(必须)
 * @param orderFieldValue
 * 排序字段名对应的value（必须）
 * @return
 * 返回查询到对象集合（list）
 */
	public <T> List<T> findObjectWithFieldOrderByField(Class<T> clazz, String fieldName, Object value,String orderField,Object orderFieldValue) {
		String className = clazz.getName();
		className = className.substring(className.lastIndexOf(".") + 1);
		String hql = "from " + className + " obj where obj." + fieldName + "=:" + fieldName.replace(".", "")+" order By obj."+orderField+" "+orderFieldValue;
		List<T> li = (List<T>) this.getHibernateTemplate().findByNamedParam(hql, fieldName.replace(".", ""), value);
		if (li.size() == 0)
			return null;
		else
			return li;

	}
	
	
	
	
	
	/**
	 * 	 通过某些字段查询某pojo对象的数据[多个条件][有序]
	 * @param clazz
	 * 查询对象的类对象
	 * @param fieldNames
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
		public <T> List<T> findObjectWithFieldsOrderByFields(Class<T> clazz, String[] fieldNames, Object[] values,Object orderField,Object orderFieldValue) throws Exception {
			String className = clazz.getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			StringBuilder strb =new StringBuilder();
			if(fieldNames==null||fieldNames.length==0||values==null||values.length==0||fieldNames.length!=values.length){
			
					throw new Exception("传递参数失败,参数不允许为空，或者数组.length==0");
				
				}
			strb.append("from " + className + " obj  where 1=1");
			String[] keyName=new String[fieldNames.length];
			for(int i=0;i<fieldNames.length;i++){
				String f=fieldNames[i];
				strb.append(" and obj."+f+"= :"+f.replace(".", ""));
				keyName[i]=f.replace(".", "");
			}
			if(orderField!=null||orderFieldValue!=null){
				strb.append(" order by obj."+orderField+" "+orderFieldValue);
			}
			
			List<T> li = (List<T>) this.getHibernateTemplate().findByNamedParam(strb.toString(),fieldNames,values );
			if (li.size() == 0)
				return null;
			else
				return li;

		}
		
		/**
		 * 	 通过某些字段查询某pojo对象的数据[多个条件][无序]
		 * @param clazz
		 * 查询对象的类对象
		 * @param fieldNames
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
			public <T> List<T> findObjectWithFields(Class<T> clazz, String[] fieldNames, Object[] values) throws Exception {
				String className = clazz.getName();
				className = className.substring(className.lastIndexOf(".") + 1);
				StringBuilder strb =new StringBuilder();
				if(fieldNames==null||fieldNames.length==0||values==null||values.length==0||fieldNames.length!=values.length){
				
						throw new Exception("传递参数失败,参数不允许为空，或者数组.length==0");
					
					}
				strb.append("from " + className + " obj  where 1=1");
				String[] keyName=new String[fieldNames.length];
				for(int i=0;i<fieldNames.length;i++){
					String f=fieldNames[i];
					strb.append(" and obj."+f+"= :"+f.replace(".", ""));
					keyName[i]=f.replace(".", "");
				}
				List<T> li = (List<T>) this.getHibernateTemplate().findByNamedParam(strb.toString(),fieldNames,values );
				if (li.size() == 0)
					return null;
				else
					return li;

			}
			
	
	
	
	
	
	
	
	
	
	
	
		
		
		//-----------------------------------需要提供hql参数的
			
			/**
			 * 
			 * @param hql	hql语句
			 * @param maxResult  每页行数
			 * @return
			 */
			public int countPage(String hql,int maxResult,Object ...condition){

				
				 int count = ((Long) this.getHibernateTemplate().find(hql,condition).get(0)).intValue();
				if (count % maxResult != 0) {
					return (count / maxResult + 1);
				} else {
					return count / maxResult;
				}
			}
		
			
		/**
		 * 
		 * @param hql  hql语句
		 * @param pageStart  起始页码
		 * @param PageSize   每页显示行数
		 * @param condition  条件
		 * @return
		 */
		public <T> List<T> findPageObjects(final String hql,final int pageStart,final int PageSize,final Object ...condition){
		return	 this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {

				@Override
				public List<T> doInHibernate(Session session) throws HibernateException {
				Query query=	session.createQuery(hql);
				for (int i = 0; i < condition.length; i++) {
					query.setParameter(i, condition[i]);
				}
				query.setMaxResults(PageSize);
				query.setFirstResult(PageSize*(pageStart-1));
				
				return query.list();
				}
			});
			
		}
	
		

		/**
		 * 通过hql查询单个对象
		 * @param hql
		 * @param conditions
		 * @return
		 * 查询单个对象
		 */
			public <T> T findByObject(String hql,Object...conditions){
		List li=this.getHibernateTemplate().find(hql, conditions);
				if(li!=null&&li.size()>0){
					return (T) li.get(0);
				}
				else{
					return null;
				}	
			}
			
		
	
		/**
		 * 查询多个持久类对象
		 */
		public <T> List<T> findObjects(String hql,Object... conditions) {
			List<T> li=(List<T>)this.getHibernateTemplate().find(hql, conditions);
			
			 if (li.size() == 0)
				return null;
			else
				return li;
		}
		
		/**
		 * 更新或者删除数据
		 * @param hql   hql语句
		 * @param conditions  条件
		 * @return
		 */
		public boolean updateObjects( String hql, Object... conditions) {	
			Session s=this.getSessionFactory().getCurrentSession();
			Query query=s.createQuery(hql);
			for(int i=0;i<conditions.length;i++){
				query.setParameter(i, conditions[i]);
			}	
			if(query.executeUpdate()==0){
				return false;
			}
	return true;
		}
//		
//		/**
//		 * 批量删除记录
//		 * @param hql
//		 * @param conditions
//		 * @return
//		 */
//		public int updateObjects(final String hql, final Object... conditions) {
//			return  this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {
//				@Override
//				public Integer doInHibernate(Session session) throws HibernateException {
//					try{
//						Query query = session.createQuery(hql);
//						for(int i=0;i<conditions.length;i++){
//							query.setParameter(i, conditions[i]);
//						}				
//						return query.executeUpdate();
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//					return 0;
//				}
//			});		
//		}
		
}
