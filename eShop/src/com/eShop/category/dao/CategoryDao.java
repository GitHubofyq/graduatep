package com.eShop.category.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.eShop.category.entities.Category;



public class CategoryDao extends HibernateDaoSupport{

	// DAO 调用 sessionFactory 工厂
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 后台一级分类管理：添加一级分类到数据库
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	/**
	 * DAO层：（后台）根据cid查询一级分类
	 * @param cid
	 * @return category
	 */
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * DAO：删除一级分类（已经查询）
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * DAO层：正式处理后台请求：更新编辑后的一级分类
	 * @param category
	 */
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}

}
