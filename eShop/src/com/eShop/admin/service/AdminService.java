package com.eShop.admin.service;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.admin.dao.AdminDao;
import com.eShop.admin.vo.Admin;

/**
 * admin业务类
 * @author Lenovo
 *
 */
@Transactional
public class AdminService {
	
	//注入adminDao
	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.login(admin);
	}
}
