package com.eShop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eShop.order.vo.Order;
import com.eShop.user.entities.User;
import com.eShop.utils.PageHibernateCallback;

/**
 * 用户模块访问数据库层
 * @author Lenovo
 *
 */
public class UserDao extends HibernateDaoSupport{

	//根据username 查询是否有用户user
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = getHibernateTemplate().find(hql, username);
		if(list != null && list.size() >0){
			return list.get(0);
		}
		return null;
	}

	public void saveUser(User user) {
		getHibernateTemplate().save(user);
	}

	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 后台分页查询用户
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User order by uid desc";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

}
