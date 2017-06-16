package com.eShop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.eShop.order.vo.Order;
import com.eShop.order.vo.OrderItem;
import com.eShop.utils.PageHibernateCallback;
/**
 * 订单模块DAO层
 * @author Lenovo
 *
 */

public class OrderDao extends HibernateDaoSupport{

	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// Dao层查询我的订单分页查询:统计个数
	public Integer findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() >0){
			return list.get(0).intValue();
		}
		return null;
	}
	

	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.user.uid = ? Order by o.ordertime desc";
		List<Order> list = getHibernateTemplate().execute(
				new PageHibernateCallback<>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() >0){
			return list;
		}
		return null;
	}

	//“付款”链接DAO层的处理，获取订单id
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Order.class, oid);
	}

	//支付成功更新当前订单
	public void update(Order currOrder) {
		getHibernateTemplate().update(currOrder);
		
	}

	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	/**
	 * dao层，根据订单id查询订单项
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
