package com.eShop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.order.dao.OrderDao;
import com.eShop.order.vo.Order;
import com.eShop.order.vo.OrderItem;
import com.eShop.utils.PageHelper;

/**
 * 订单模块业务层
 * @author Lenovo
 *
 */
@Transactional
public class OrderService {
	
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//业务层保存订单，调用DAO
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	//service 层 "我的订单" 分页查询---总记录数、每页显示的数据集合 （页数x每页条数）
	public PageHelper<Order> findPageByUid(Integer uid, Integer page) {
		PageHelper<Order> pageHelper = new PageHelper<Order>();
		// 设置当前页数:
		pageHelper.setCurrentPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageHelper.setItemsPage(limit);
		
		// 设置总记录数:
		Integer recordCount = null;
		recordCount = orderDao.findCountByUid(uid);
		pageHelper.setRecordCount(recordCount);
		
		// 设置总页数
		int totalPage = 0;
		if(recordCount % limit == 0){
			totalPage = recordCount / limit;
		}else{
			totalPage = recordCount / limit + 1;
		}
		pageHelper.setTotalPages(totalPage);
		
		// 设置每页显示数据集合:
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findPageByUid(uid,begin,limit);
		pageHelper.setListPage(list);
		
		return pageHelper;
	}

	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	//更新订单数据
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

	/**
	 * 业务层：分页查询订单
	 * @param page
	 * @return
	 */
	public PageHelper<Order> findAll(Integer page) {
		PageHelper<Order> pageHelper = new PageHelper<Order>();
		// 设置参数
		pageHelper.setCurrentPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageHelper.setItemsPage(limit);
		// 设置总记录数
		int recordCount = orderDao.findCount();
		pageHelper.setRecordCount(recordCount);
		// 设置总页数
		int totalPage = 0;
		if(recordCount % limit == 0){
			totalPage = recordCount / limit;
		}else{
			totalPage = recordCount / limit + 1;
		}
		pageHelper.setTotalPages(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageHelper.setListPage(list);
		return pageHelper;
	}

	/**
	 * 业务层：根据订单id查询订单项
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findorderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	
}
