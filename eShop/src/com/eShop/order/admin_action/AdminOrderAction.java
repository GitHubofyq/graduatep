package com.eShop.order.admin_action;

import java.util.List;

import com.eShop.order.service.OrderService;
import com.eShop.order.vo.Order;
import com.eShop.order.vo.OrderItem;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理
 * @author Lenovo
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private static final long serialVersionUID = 1L;
	
	//创建模型驱动的对象
	private Order order = new Order();
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	//注入service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//接收left.jsp 请求的page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/**
	 * 分页查询订单
	 * @return
	 */
	public String findAll(){
		// 订单的分页查询
		PageHelper<Order> pageHelper = orderService.findAll(page);
		// 将数据存入到值栈中保存到页面
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		// 页面跳转:
		return "findAll";
	}
	
	/**
	 * 根据订单oid 查找订单项
	 * @return
	 */
	public String showOrderItem(){
		List<OrderItem> list =orderService.findorderItem(order.getOid());
		//订单项的列表存入值栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "showOrderItem";
	}
	
	/**
	 * 更新订单的状态
	 * @return
	 */
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateState";
	}

}
