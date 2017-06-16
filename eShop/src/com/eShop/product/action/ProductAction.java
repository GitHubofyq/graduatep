package com.eShop.product.action;

import com.eShop.category.service.CategoryService;
import com.eShop.product.entities.Product;
import com.eShop.product.service.ProductService;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private static final long serialVersionUID = 1L;

	//创建一个私有商品对象
	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}	
	//注入pruductService对象
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//自定义一个cid接收请求过来的cid
	private Integer cid;
	
	//接收二级分类请求的this.csid=csid;
	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}

	//注入categoryService对象 用于查询所有product的category的cid
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//接收请求传来的当前页数
	private int currentPage;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	//根据页面请求的商品id查询商品：执行方法，响应页面的action
	public String findByPid(){
		//调用service执行
		//用模型驱动的方法，存到栈点，否则，要存到值栈
		product = productService.findByPid(product.getPid());
		System.out.println("-------------读取数据库--商品的--------pid-------"+product+product.getPid());
		return "findByPid";
	}
	
	//根据页面请求的category.cid请求查询商品；发送到name="product_*"的过滤器。执行方法获取商品cid
	public String findByCid(){
		
		System.out.println("---------------ProductAction.findByCid()----一级分类---cPage---"+getCurrentPage());
		//List<Category> clist = categoryService.findAll();这里可以不用查询，在页面的session里也有#category.cid
		//根据一级分类的cid做分页查询商品;返回类型是分页类
		System.out.println("----------ProductAction.findByCid()------cid---"+getCid());
		PageHelper<Product> pageHelper = productService.findByPageCid(cid,currentPage);
		
		//将分页结果存入值栈
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		System.out.println("------------------------数据库响应的---商品的分页数据---pageHelper: "+pageHelper);
		return "findByCid";
	}
	
	
	//请求页面:商品分类页面的的分页界面，二级分类的的子类的链接<商品名：如当季热卖>findByCsid.action 请求参数：csid= cspage=1
	public String findByCsid(){
		System.out.println("---------------ProductAction.findByCid()-----二级分类-----"+getCurrentPage());
		//根据二级分类的csid查询商品Product,product要使用productService对象的服务,返回product的分页集合
		PageHelper<Product> pageHelper= productService.findByPageCsid(csid,currentPage);
		//将分页结果存入值栈
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		return "findByCsid";
	}
}
