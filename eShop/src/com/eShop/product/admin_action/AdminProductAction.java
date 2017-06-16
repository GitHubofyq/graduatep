package com.eShop.product.admin_action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.eShop.categorysecond.entities.CategorySecond;
import com.eShop.categorysecond.service.CategorySecondService;
import com.eShop.product.entities.Product;
import com.eShop.product.service.ProductService;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author Lenovo
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	private static final long serialVersionUID = 1L;
	
	private Product product = new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//注入categorySecondService;
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	//接收left.jsp 商品的请求 page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//-------文件上传接收的参数----
	private File upload;  //上传的文件  ，参数名与表单提交的相同
	private String uploadFileName; //接收上传文件的名称
	private String uploadConetxtType;//接收上传的文件的类型
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadConetxtType(String uploadConetxtType) {
		this.uploadConetxtType = uploadConetxtType;
	}

	/**
	 * 分页查询所有商品
	 * @return
	 */
	public String findAll(){
		PageHelper<Product> pageHelper = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		return "findAll";
	}
	
	/**
	 * 跳转到添加商品页面的方法:
	 * @return
	 */
	public String addPage() {
		 
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPage";
	}
	
	/**
	 * 后台商品保存；文件上传
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException{
		product.setPdate(new Date());
		
		if(upload != null){
			// 获得文件上传磁盘的绝对路径.
			String realPath = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(realPath + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		//页面跳转
		return "save";
	}
	
	public String delete(){
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = product.getImage();
		if(path != null){
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/"+path);
			File file = new File(realpath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		//跳转页面
		return "delete";
	}

	/**
	 * 跳转编辑商品页面
	 * @return
	 */
	public String edit(){
		// 根据商品id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "edit";
	}
	
	public String update() throws IOException{
		//修改商品时间
		product.setPdate(new Date());
		//文件上传
		if(upload != null){
			//文件上传前删除之前的图片
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			//上传
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "update";
	}
}
