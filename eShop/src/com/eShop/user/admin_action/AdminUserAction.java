package com.eShop.user.admin_action;

import com.eShop.user.entities.User;
import com.eShop.user.service.UserService;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;

	//模型驱动封装用户信息
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//接收请求的当前页
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//注入userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//findAll.action根据请求查询用户list
	public String findAll(){
		PageHelper<User> pageHelper = userService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		return "findUser";
	}

}
