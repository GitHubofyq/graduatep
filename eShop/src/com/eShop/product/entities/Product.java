package com.eShop.product.entities;

import java.io.Serializable;
import java.util.Date;

import com.eShop.categorysecond.entities.CategorySecond;

/**
 * 商品类
 * @author Lenovo
 * `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `eshop_price` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `csid` int(11) DEFAULT NULL,
 */
public class Product implements Serializable{

	private Integer pid;
	private String pname;
	private Double market_price;
	private Double eshop_price;
	private String image;
	private String pdesc;
	private Integer is_hot;
	private Date pdate;
	
	// 二级分类的外键；商品:二级分类 = n:1  关联关系；使用二级分类的对象.要双向关联，在二级分类配商品的集合
	private CategorySecond categorySecond;
		
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	
	public Double getEshop_price() {
		return eshop_price;
	}
	public void setEshop_price(Double eshop_price) {
		this.eshop_price = eshop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

}
