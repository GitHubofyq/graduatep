package com.eShop.categorysecond.admin_action;

import java.util.List;

import com.eShop.category.entities.Category;
import com.eShop.category.service.CategoryService;
import com.eShop.categorysecond.entities.CategorySecond;
import com.eShop.categorysecond.service.CategorySecondService;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台:二级分类管理 Action
 * @author Lenovo
 *
 */
public class AdminSubCategoryAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private static final long serialVersionUID = 1L;
	private CategorySecond categorySecond = new CategorySecond();
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond; //模型驱动使用的对象
		}
	
	
	
	
	//注入CategorySecondService对象
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	//--------后台二级分类的分页查询-------
	//left.jsp-->findAll.action？page=1
	//接收参数page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//-----后台添加二级分类需要查询一级分类，所以注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//-----------------------------------------------------------------------------
	/**
	 * 后台分页查询二级分类
	 * @return
	 */
	public String findAll(){
		PageHelper<CategorySecond> pageHelper = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		return "findAll";
	}
	
	//跳转到 添加 二级分类 的页面 addPage ---需要先查询一级分类--注入一级分类的service
	public String addPage(){
		//在编辑添加二级分类之前  ，查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//将结果显示到页面下拉列表中，带到页面需要通过值栈保存
		ActionContext.getContext().getValueStack().set("cList", cList);
		//页面跳转
		return "addPage"; //只是跳转到添加二级分类的页面
		
	}
	
	/**
	 * Action 根据编辑页面后 表单提交的数据 保存二级分类
	 * @return
	 * /eShop/WebContent/admin/subCategory/add.jsp
	 * adminSubCategory_save.action--> 提交编辑好的表单：csname、<seclect name="category.cid"><options>c.cname</options></select>
	 */
	public String save(){
		//数据在上面查询到了,使用模型驱动
		categorySecondService.save(categorySecond);
		//跳转页面，保存完毕，在重新查询数据局响应回list.jsp
		return "save";
		
	}
	
	/**
	 * Action；subcategory/list.jsp delete.action?csid= 请求删除二级分类
	 * @return
	 */
	public String delete(){
		//如果有级联删除，先查询再删除，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "delete";
		
	}
	
	/**
	 * Action: 编辑二级分类
	 * @return
	 */
	public String edit(){
		//1.查询所有的二级分类（根据模型驱动接收csid），得到二级分类对象
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//2.查询二级分类关联的一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		//跳转到编辑页面
		return "edit";
		
	}
	
	/**
	 * 更新二级分类
	 * @return
	 */
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
		
	}

}
