package com.eShop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.eShop.user.entities.User;
import com.eShop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块action类
 * @author Lenovo
 *
 */

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	
	User user = new User();
	//实现模型驱动获得用户对象
	@Override
	public User getModel() {
		return user;
	}
	
	// 接收验证码:
	private String captcha;
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	//注入 UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//用户访问注册页面的方法
	public String registPage(){
		return "registPage";
	}
	
	//ajax校验的用户名的方法
	public String checkUserName() throws IOException{
		 User regUser = userService.findByUsername(user.getUsername());
		 //该方法不用跳转页面，直接响应回页面，需要获得响应对象
		 HttpServletResponse response = ServletActionContext.getResponse();
		 response.setContentType("text/html;charset=UTF-8");//响应中文要设置字符编码
		 //判断用户名是否已被注册
		 if(regUser != null){
			 response.getWriter().println("<font color='red'>该用户名已存在</font>");
		 }else{
			 response.getWriter().println("<font color='green'>该用户名可以使用</font>");
		 }
		 return NONE;
	}
	
	//用户注册方法
	public String register(){
		userService.saveUser(user);
		// 判断验证码程序:
		// 从session中获得验证码的随机值:
		String imgcaptcha = (String)ServletActionContext.getRequest()
				.getSession().getAttribute("captcha");
		if(!captcha.equalsIgnoreCase(imgcaptcha)){
			this.addActionError("验证码输入错误!");
			return "captchaFail";
		}
		return "message";
	}

	//跳转到登录页面的方法
	public String loginPage(){
		return "loginPage";
	}
	
	//登录方法
	public String login() {
		User existUser = userService.login(user);
		// 判断
		if (existUser == null) {
			// 登录失败
			this.addActionError("登录失败:用户名或密码错误或用户未激活!");
			return LOGIN;
		} else {
			// 登录成功
			// 将用户的信息存入到session中
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// 页面跳转
			return "loginSuccess";
		}
	}
	
	/**
	 * 用户退出的方法
	 */
	public String quit(){
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
