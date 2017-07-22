package com.li.dao.users;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.li.common.dao.BaseDaoImpl;
import com.li.common.domain.BookInfo;

public class UsersDaoImpl extends BaseDaoImpl implements IUsersDao {

	@Override
	public List<BookInfo> findPageBookInfoByCondition( int page, int maxResult,Map<String,Object> map,String sortField,String sortWay) {
		Class clazz=BookInfo.class;
		final StringBuilder strb=new StringBuilder("FROM "+clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+" obj where 1=1");
		final List<String> keyList = new ArrayList<String>();
		if(map!=null){
			keyList.addAll(map.keySet());
			for(String key :keyList){
				strb.append("  and obj."+key+"= :"+key.replace(".", ""));
			}
			
		}
		strb.append(" and obj.bookrack.bookrackId  NOT IN (1,2)");
		if(sortField!=null){
			if(sortWay==null){
				strb.append(" order by obj."+sortField.replace(".", "")+" asc");
			}
			else{
				strb.append(" order by obj."+sortField+" "+sortWay);
			}
		}
		
		
		return this.getHibernateTemplate().execute(new HibernateCallback<List<BookInfo>>() {
			
			@Override
			public List<BookInfo> doInHibernate(Session session) throws HibernateException {
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

	@Override
	public int countPageBookInfoByCondition(int maxResult, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Class clazz=BookInfo.class;
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
					s.append(" and obj.bookrack.bookrackId  NOT IN (1,2)");
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

	
	
}
