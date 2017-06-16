package com.eShop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.order.vo.Order;
import com.eShop.user.dao.UserDao;
import com.eShop.user.entities.User;
import com.eShop.utils.PageHelper;
import com.eShop.utils.UUIDUtils;

/**
 * 用户模块业务处理
 * @author Lenovo
 *
 */
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//根据用户名查询，具体有UserDao层实现
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	public void saveUser(User user) {
		user.setState(1);//0:用户未激活，1:用户已激活
		String code = UUIDUtils.getUID()+UUIDUtils.getUID();
		user.setCode(code);
		userDao.saveUser(user);
		
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	/**
	 * 后台用户管理，分页处理
	 * @param page
	 * @return
	 */
	public PageHelper<User> findAll(Integer page) {
		// TODO Auto-generated method stub
		PageHelper<User> pageHelper = new PageHelper<User>();
		// 设置当前页数
		pageHelper.setCurrentPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageHelper.setItemsPage(limit);
		// 设置总记录数
		int recordCount = userDao.findCount();
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
		List<User> list = userDao.findByPage(begin,limit);
		pageHelper.setListPage(list);
		return pageHelper;

	}

	
}
