package com.eShop.admin.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eShop.admin.vo.Admin;

public class AdminDao extends HibernateDaoSupport {

	/**
	 * DAO层查询数据库的admin
	 * 
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		String hql = "from Admin where adminname = ? and password = ?";
		List<Admin> list = getHibernateTemplate().find(hql, 
				admin.getAdminname(), admin.getPassword());
		if (list != null & list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
