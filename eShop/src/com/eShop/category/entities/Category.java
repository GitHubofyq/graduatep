package com.eShop.category.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.eShop.categorysecond.entities.CategorySecond;

/**
 * 一级分类表的实体类
 * @author Lenovo
 *
 */
public class Category implements Serializable{
	private Integer cid;
	private String cname;
	
	public Category() {
		
	}
	//双向关联 1:n
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

}
