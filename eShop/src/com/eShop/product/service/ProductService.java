package com.eShop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eShop.product.dao.ProductDao;
import com.eShop.product.entities.Product;
import com.eShop.utils.PageHelper;

@Transactional
public class ProductService {
	//注入 ProductDao
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		
		return productDao.findNew();
	}

	//根据商品id查询商品
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
	
	
	//分页查询商品,使用工具包的分页类，引入工具类PageHelper
	public PageHelper<Product> findByPageCid(Integer cid, int cPage) {
		
		// 新建分类类的对象，给对象设置相关属性
		PageHelper<Product> pageHelper = new PageHelper<Product>();
		
		//1.设置当前页数
		pageHelper.setCurrentPage(cPage);
		
		//2.设置每页显示几条记录
		int items = 8;
		pageHelper.setItemsPage(items);
		
		//3.设置总记录数，从数据库中查询
		int recordCount = 0;
		recordCount = productDao.findCountCid(cid);
		pageHelper.setRecordCount(recordCount);
		System.out.println("-----------------------------ProductService 的pageHelper.getRecordCount(): "+pageHelper.getRecordCount());
		
		//4.设置总页数，根据总记录数取余
		//Math.ceil(recordCount / items);向上取整，有余会加1
		int totalPages = 0;
		if(recordCount % items == 0){
			totalPages = recordCount / items;
		}else{
			totalPages = (recordCount / items) + 1;
		}
		pageHelper.setTotalPages(totalPages);
		
		//每页显示数据集合,事先从从数据库查询到数据，才能设置商品在每页的集合
		//begin;第一页从0开始，第二页从8开始
		int begin = (cPage-1) * items;
		List<Product> list = productDao.findByPageCid(cid,begin,items);
		
		//5.设置分页显示记录数的集合(一开始忘记设置，导致出现500bug)
		pageHelper.setListPage(list);
		
		System.out.println("------------------------ProductService 的pageHelper.getListPage(): "+pageHelper.getListPage());
		
		return pageHelper;//到这一步分页对象的-5个属性-设置完成
	}
	

	// 二级分类分页查询的方法----------分页配置---------------------
	public PageHelper<Product> findByPageCsid(Integer csid, int cPage) {
		// 新建分类类的对象，给对象设置相关属性
		PageHelper<Product> pageHelper = new PageHelper<Product>();
		
		//1.设置当前页数
		pageHelper.setCurrentPage(cPage);
		
		//2.设置每页显示几条记录
		int items = 8;
		pageHelper.setItemsPage(items);
		
		//3.设置总记录数，从数据库中查询
		int recordCount = 0;
		recordCount = productDao.findCountCsid(csid);
		pageHelper.setRecordCount(recordCount);
		
		//4.设置总页数，根据总记录数取余
		//Math.ceil(recordCount / items);向上取整，有余会加1
		int totalPages = 0;
		if(recordCount % items == 0){
			totalPages = recordCount / items;
		}else{
			totalPages = (recordCount / items) + 1;
		}
		pageHelper.setTotalPages(totalPages);
		
		//每页显示数据集合,事先从从数据库查询到数据，才能设置商品在每页的集合
		//begin;第一页从0开始，第二页从8开始
		int begin = (cPage-1) * items;
		List<Product> list = productDao.findByPageCsid(csid,begin,items);
		
		//5.设置分页显示记录数的集合(一开始忘记设置，导致出现500bug)
		pageHelper.setListPage(list);
		
		return pageHelper;
	}

	/**
	 * 后台业务层 
	 * @param page
	 * @return
	 */
	public PageHelper<Product> findByPage(Integer page) {
		//0.创建分对象
		PageHelper<Product> pageHelper = new PageHelper<Product>();
		//1.设置当前页数
		pageHelper.setCurrentPage(page);
		
		//2.设置每页显示的记录数
		int items = 10;
		pageHelper.setItemsPage(items);
		
		//3.设置总记录数
		int recordCount = productDao.findCount();
		pageHelper.setRecordCount(recordCount);
		
		//4.设置总页数
		int totalPages = 0;
		if(recordCount % items == 0){
			totalPages = recordCount / items;
		}else{
			totalPages = (recordCount / items) + 1;
		}
		pageHelper.setTotalPages(totalPages);
		
		//5.设置每页显示数据集合,事先从从数据库查询到数据，才能设置商品在每页的集合
		//begin;第一页从0开始，第二页从8开始
		int begin = (page-1) * items;
		List<Product> list = productDao.findByPage(begin,items);
		pageHelper.setListPage(list);
		
		return pageHelper;
	}

	/**
	 * service : 文件保存
	 * @param product
	 */
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	/**
	 * 
	 * @param product
	 */
	public void delete(Product product) {
		productDao.delete(product);
	}

	public void update(Product product) {
		productDao.update(product);
		
	}

	

}
