package com.eShop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eShop.product.entities.Product;
import com.eShop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	// 首页热门商品的查询
	public List<Product> findHot() {
		// 使用离线条件查询.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品,条件就是is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序排序输出:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		@SuppressWarnings("unchecked")
		List<Product> list = this.getHibernateTemplate()
					.findByCriteria(criteria, 0, 10);
		return list;
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		// 使用离线条件查询.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//按日期倒序排列，最后上传的是最新商品
		criteria.addOrder(Order.desc("pdate"));
		@SuppressWarnings("unchecked")
		List<Product> list = getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * dao层根据商品id查询商品
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		 Product product = this.getHibernateTemplate().get(Product.class, pid);
		return product;
		 
	}

	//返回查询到的商品的总记录数
	//根据请求过来的一级分类的cid,查询数据库的商品的总记录数。事先配置好二级分类和商品的关联，才能通过商品找到二级分类再找到一级分类
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		@SuppressWarnings("unchecked")
		List<Long> list = getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();//转成int类型
		}
		System.out.println("-------------------------------在DAO层--数据库查询-------------ProductDao--- list--int : "+list);
		return 0;
	}

	//根据分类id查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int items) {
		// select p.* FROM category c,categorysecond cs,product p WHERE c.cid = cs.cid AND cs.csid = p.csid AND c.cid = 1;
		//将在数据库查询的sql语句改换成hql语句，关键字join
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		@SuppressWarnings("unchecked")
		List<Product> list = getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, items));
		
		if(list !=null && list.size() > 0){
			return list;
		}
		System.out.println("-------------------------------在DAO层--数据库查询-------------ProductDao--- list: "+list);
		return null;
	}

	// 二级分类分页查询的方法-----------------------------
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		@SuppressWarnings("unchecked")
		List<Long> list = getHibernateTemplate().find(hql, csid);
		if(list !=null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int items) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		@SuppressWarnings("unchecked")
		List<Product> list = getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, items));
		if(list !=null && list.size() > 0){
			return list;
		}
		System.out.println("-------在DAO层ProductDao----数据库查询----二级分类分页查询的服务方法------- list: "+list);
		return null;
	}

	//------------后台------
	/**
	 * 后台统计商品个数的方法
	 * @return
	 */
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 后台查询所有商品的方法
	 * @param begin
	 * @param items
	 * @return
	 */
	public List<Product> findByPage(int begin, int items) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, null, begin, items));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 后台 DAO 商品的增、删、改
	 * @param product
	 */
	public void save(Product product) {
		getHibernateTemplate().save(product);
	}
	
	public void delete(Product product) {
		getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		getHibernateTemplate().update(product);
	}


}
