package com.eShop.index.action;

import java.util.List;

import com.eShop.category.entities.Category;
import com.eShop.category.service.CategoryService;
import com.eShop.product.entities.Product;
import com.eShop.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问首页Action
 * @author Lenovo
 *
 */
public class IndexAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	//注入一级分类的service
	private CategoryService categoryService;
	
	//注入商品
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//访问首页的方法
	public String execute(){
		
		//调用 service查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 将一级分类存入到Session的范围:
		ActionContext.getContext().getSession().put("cList", cList);
		
		// 查询热门商品:
		List<Product> hList = productService.findHot();
		// 保存到值栈中:
		ActionContext.getContext().getValueStack().set("hList", hList);
		// 查询最新商品:
		List<Product> nList = productService.findNew();
		// 保存到值栈中:
		ActionContext.getContext().getValueStack().set("nList", nList);		
		return "index";
	}

}
