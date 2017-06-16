package com.eShop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.categorysecond.dao.CategorySecondDao;
import com.eShop.categorysecond.entities.CategorySecond;
import com.eShop.utils.PageHelper;

/**
 * 后台二级分类管理，service类
 * @author Lenovo
 *
 */
@Transactional
public class CategorySecondService {

	//注入CategorySecondDao 对象
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	/**
	 * 后台分页
	 * @param page
	 * @return pageHelper
	 */
	public PageHelper<CategorySecond> findByPage(Integer page) {
		
		//0.创建分页对象,对象有5个属性
		PageHelper<CategorySecond> pageHelper = new PageHelper<CategorySecond>();
		
		//1.设置当前页
		pageHelper.setCurrentPage(page);
		
		//2.设置每页显示几条记录
		int items = 10;
		pageHelper.setItemsPage(items);
		
		//3.设置总记录数---总计多少条二级分类? 需要从数据库中查询--调用DAO
		int recordCount = categorySecondDao.findCount();
		pageHelper.setRecordCount(recordCount);
		
		//4.设置总页数 = 总记录数 /每页显示几条记录  -有余 多加一页
		int totalPages = 0;
		if(recordCount % items == 0){
			totalPages = recordCount / items;
		}else{
			totalPages = recordCount / items + 1;
		}
		pageHelper.setTotalPages(totalPages); //将计算结果设置进总页数
		
		//5.设置每页显示数据集合  :  每页的数据集合:从第0条数据开始  x 每页显示几条
		int begin = (page-1) * items;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,items);
		pageHelper.setListPage(list);
		
		return pageHelper;//返回二级分类分页对象
	}

	/**
	 * service:保存二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	/**
	 * service : 根据二级分类的csid 查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * service ： 根据二级分类删除
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * service : 更新二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	/**
	 * 后台：业务层：商品添加 查询所有二级分类
	 * @return
	 */
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}
}
