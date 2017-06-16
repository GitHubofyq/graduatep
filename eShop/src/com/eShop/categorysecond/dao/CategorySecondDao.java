package com.eShop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eShop.categorysecond.entities.CategorySecond;
import com.eShop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findByPage(int begin, int items) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,items));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//-------------------------后台---------------
	/**
	 * DAO:保存二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * DAO:根据二级分类csid 查询
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * DAO:根据二级分类删除
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		getHibernateTemplate().delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		getHibernateTemplate().update(categorySecond);
	}

	//----------后台商品-----------------
	/**
	 * 
	 * @return
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
