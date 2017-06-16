package com.eShop.order.action;

import java.util.Collection;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.eShop.cart.entities.Cart;
import com.eShop.cart.entities.CartItem;
import com.eShop.order.service.OrderService;
import com.eShop.order.vo.Order;
import com.eShop.order.vo.OrderItem;
import com.eShop.user.entities.User;
import com.eShop.utils.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 订单管理Action
 * @author Lenovo
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private static final long serialVersionUID = 1L;

	//模型驱动使用的对象------对象会存入值栈（模型驱动默认在栈顶的位置）
	private Order order = new Order(); 
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;	//order对象返回给模型驱动使用
	}
	
	//接收“我的订单”链接的请求参数page
	private Integer page;
	public void setPage(int page) {
		this.page = page;
	}
	
	//注入orderService对象
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//-----------------------------------------------------
	//接收支付通道编码pd_FrpId
	/*private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	//接收支付成功后的请求参数，其他参数校验待完成
	private String r3_Amt;
	private String r6_Order;
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
*/
	//生成订单--------------------------------------------------
	public String submitOrder(){
		
	//1.保存订单的数据到数据库
		//补全订单数据
		//设置订单的总金额    --从购物车获取 -总金额total(商品金额)
		//先从session中获取购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		//得到购物车，还要判断订单是否经过购物车
		if(cart == null){
			this.addActionError("亲，你的购物车没有商品，请先去购物！");
			return "message";
		}
		order.setTotal(cart.getTotal());//购物车不为空，获取购物车的总计total
		order.setOrdertime(new Date());//用系统当前时间 ,插入的不是时间戳，待修改<<--------------
		order.setState(1);			//订单状态   1:未付款 	2:已付款，未发货		3:已发话货，未确认收货		4:交易完成

		//设置订单所属用户， 从session获取用户
		User existUser = (User) ServletActionContext.getRequest()
				.getSession().getAttribute("existUser");
		if(existUser != null){
			order.setUser(existUser);//根据登录的用户设置订单所属用户
		}else{
			this.addActionError("亲!要先登录才能购物哦!");
			return "login";
		}
		
		//没提交到第三方支付，所以事先设置好用户的相关
		order.setName(existUser.getName());
		order.setPhone(existUser.getPhone());
		order.setAddr(existUser.getAddr());
		
		//设置订单的订单项(orderitem),orderItems的(count、subtotal)在购物车的购物车项cartItems中
		//获取--购物车项cartItems，遍历
		Collection<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {
			//创建一个订单项 orderItem 对象
			OrderItem orderItem = new OrderItem();
			//对订单项进行相关设置
			orderItem.setCount(cartItem.getCount());	//从-购物车项-获取--购物项--购物数量
			orderItem.setSubtotal(cartItem.getSubtotal());//从-购物车项-获取--购物项--购物小计
			orderItem.setProduct(cartItem.getProduct()); //从-购物车项-获取--购物项--购买的商品
			//设置订单到订单项
			orderItem.setOrder(order);//外键，关联订单；"订单项" 设置完成
			//遍历订单，订单获取"订单项"
			order.getOrderItems().add(orderItem); //订单项集合 遍历添加每项订单项-->形成订单项集合 orderItems
		}
		//以上，订单设置(生成)完成
		//调用service服务，最后保存订单到数据库
		orderService.save(order);
		//提交订单后要及时清空购物车
		cart.clearCart();
		
	//2.将订单对象响应回页面
		//使用值栈:因为Order显示的对象就是模型驱动的使用的对象
		return "submitOrder";
	}
	//----------------------------------------------------------------------------

	//"我的订单"请求链接
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//分页查询，封装到分页对象中
		PageHelper<Order> pageHelper = orderService.findPageByUid(user.getUid(),page);
		//将分页数据响应回页面
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("pageHelper", pageHelper);
		return "findByUid";
	}
	
	//"付款"的请求链接，根据传入的oid查询订单,并返回;传入oid的参数会被模型驱动接收(模型驱动使用的对象是 order);
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
/*	//选择银行，"确认付款"按钮的 请求表单  支付方法
	public String payOrder() throws IOException{
		//1.继续补全传入的“当前订单”的所属的用户数据（用户名、地址、电话），这些数据在用户提交订单时并未插入数据库
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setName(order.getName());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);//更新当前订单数据
		
		//2.完成支付订单
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/eShop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());

		return NONE;
	}*/
	
	// 付款成功后跳转回来的路径:
	public String payOrder(){
		Order currOrder = orderService.findByOid(order.getOid());
		// 修改订单的状态:
		//Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);//更新当前订单数据
		this.addActionMessage("支付成功!订单编号为: "+order.getOid() +" 付款金额为: "+order.getTotal());
		return "message";
	}
	
	/**
	 * 用户更新订单状态
	 * @return
	 */
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateState";
	}
	
}
