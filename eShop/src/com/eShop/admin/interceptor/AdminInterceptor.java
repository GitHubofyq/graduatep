package com.eShop.admin.interceptor;

import org.apache.struts2.ServletActionContext;

import com.eShop.admin.vo.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验拦截
 * 管理员用户未登录不能访问后台
 * @author Lenovo
 *
 */
public class AdminInterceptor extends MethodFilterInterceptor {
	
	private static final long serialVersionUID = 1L;

	//执行拦截的方法
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//查看session中是否有后台用户的信息
		Admin admin = (Admin) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdmin");
		//判断管理员是否有登录
		if(admin == null){
			//未登录进行访问操作
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("亲，请用管理员账户登录");
			return "loginFail";
		}else{
			//已登录
			return actionInvocation.invoke();
		}
	}

}
