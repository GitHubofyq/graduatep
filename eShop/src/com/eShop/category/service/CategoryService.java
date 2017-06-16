package com.eShop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.category.dao.CategoryDao;
import com.eShop.category.entities.Category;
import com.eShop.categorysecond.entities.CategorySecond;

@Transactional
public class CategoryService {
	//注入dao对象
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// service 调用 DAO 查询所有一级分类
		return categoryDao.findAll();
	}

	/**
	 * 后台一级分类分类添加的service层方法
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 * 业务层：后台根据cid查询一级分类
	 * @param cid
	 * @return category
	 */
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	/**
	 * service:删除一级分类（已经查询）
	 * @param category
	 */
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	/**
	 * service层：更新编辑后的一级分类的数据
	 * @param category
	 */
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

		
}
