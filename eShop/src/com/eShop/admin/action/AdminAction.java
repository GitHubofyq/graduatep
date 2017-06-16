package com.eShop.admin.action;

import org.apache.struts2.ServletActionContext;

import com.eShop.admin.service.AdminService;
import com.eShop.admin.vo.Admin;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>{

	private static final long serialVersionUID = 1L;
	
	//新建一个admin对象交给模型驱动
	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}
	
	//注入adminService
	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	//后台首页登录表单请求，请求表单提交过来的用户名、密码已存在于模型驱动中
	public String login(){
		System.out.println("------------admin------admin:"+admin.getAdminname());
		System.out.println("------------admin------password:"+admin.getPassword());
		Admin existAdmin = adminService.login(admin);
		if(existAdmin != null){
			//将用户信息存到session 登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdmin",existAdmin);
			return "loginSuccess";
		}else{
			//登录失败
			this.addActionError("您的用户名或密码错误");
			return "loginFail";
		}
	}
	

}
