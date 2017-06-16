package com.eShop.category.admin_action;

import java.util.List;

import com.eShop.category.entities.Category;
import com.eShop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理action
 * @author Lenovo
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private static final long serialVersionUID = 1L;
	
	//将 category 对象存入模型驱动
	private Category category = new Category();
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	//注入 categoryService 对象
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * ---在后台查询一级分类---
	 * @return
	 * eShop/WebContent/admin/left.jsp/-->/adminCategory_findAll.action
	 * 后台一级分类管理:查询一级分类，调用一级分类业务层的查询方法，业务层的查询方法再调用DAO层的查询方法
	 */
	public String findAll(){
		List<Category> clist = categoryService.findAll();
		System.out.println("--------后台一级分类管理查询====================："+clist);
		//将查询的结构放入值栈中
		ActionContext.getContext().getValueStack().set("clist",clist);
		return "findAll";
	}
	
	/**
	 * ---在后台保存一级分类到数据库---
	 * @return
	 * eShop/WebContent/admin/category/add.jsp-->adminCategory_save.action
	 * 根据admin/category/add.jsp 的请求，save.action 添加   一级分类数据    到数据库
	 */
	public String save(){
		categoryService.save(category);
		return "save";
	}
	
	/**
	 *  --在后台删除 一级分类 ---list.jsp-->delete.action?cid -->category
	 * @return
	 * 模型驱动(category)接收请求过来的cid
	 */
	public String delete(){
		//一级分类与二级分类关联，删除一级分类的同时删除二级分类，要根据id查询关联,查询之后再进行删除
		category = categoryService.findByCid(category.getCid());//查询得到category对象
		//删除
		categoryService.delete(category);
		//删除完毕，页面跳转
		return "delete";
		
	}
	
	/**
	 * action：后台编辑一级分类，请求cid传入模型驱动中
	 * @return
	 */
	public String edit(){
		//先查询到，才能编辑
		category = categoryService.findByCid(category.getCid());//该方法在上一步已完成
		//页面跳转
		return "edit";
	}
	
	/**
	 * action:后台->更新(修改)编辑后的一级分类
	 * @return
	 * 
	 */
	public String update(){
		categoryService.update(category);
		//更新数据库完毕，页面跳转
		return "update";
	}

}
