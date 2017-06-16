package com.eShop.cart.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


//购物车类
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	//购物车里购物项的集合，使用Map,key:product.pid->唯一标识每项购物项 ;value:购物项
	private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>(); //购物项,LinkedHashMap有序

	// Cart对象中有一个叫cartItems属性.-->cartItems:购物车项
	public Collection<CartItem> getCartItems(){
		return items.values();
	}
	
	//购物总计
	private double total;	
	public double getTotal() {
		return total;
	}
	
	// 购物车的功能:
	// 1.将购物项添加到购物车,传入购物项 cartItem
	public void addCart(CartItem cartItem) {
		// 判断购物车中是否已经存在该购物项:根据购物项的key里面有没有商品的pid
		// 事先获得商品id.
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项:
		if(items.containsKey(pid)){
			// 存在
			CartItem _cartItem = items.get(pid);// 获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());//加上传入的购物项，重设一下当前购物项数量
		}else{
			// 不存在
			items.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();//最后不管购物项里有没有商品，添加商品，购物车的total都要增加
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = items.remove(pid);
		// 总计 = 总计 -移除的购物项小计:
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		items.clear();
		// 将总计设置为0
		total = 0;
	}
	
}
