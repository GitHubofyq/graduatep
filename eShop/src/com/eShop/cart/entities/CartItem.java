package com.eShop.cart.entities;

import com.eShop.product.entities.Product;

/**
 * 购物项类
 * @author Lenovo
 *
 */
public class CartItem {

	private Product  product;	//商品信息(价格、名字、id……)
	private int count;			//购买某种商品的数量
	private double subtotal;	//购买某种商品的小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//计算某种商品的小计
	public double getSubtotal() {
		return count * product.getEshop_price();
	}
	
}
