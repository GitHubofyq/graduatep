package com.eShop.categorysecond.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.eShop.category.entities.Category;
import com.eShop.product.entities.Product;

/**
 * categorysecond
 * @author Lenovo
 *`csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
 */
public class CategorySecond implements Serializable{
	
	private Integer csid;
	private String csname;
	
	//二级分类:一级分类-->双向关联 n:1
	private Category category;
	
	//配置商品集合；二级分类:商品 = 1:n （双向关联）
	private Set<Product> products = new HashSet<Product>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public CategorySecond(){
		
	}
}
