package com.eShop.utils;

import java.util.List;


/**
 * 分页类
 * @author Lenovo
 *
 */
public class PageHelper<T>{
	
	private int currentPage;//当前页
	private int recordCount;//总记录数
	private int totalPages;//总页数
	private int itemsPage;	//每页显示记录数
	private List<T> listPage;//每页显示数据集合
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getItemsPage() {
		return itemsPage;
	}
	public void setItemsPage(int itemsPage) {
		this.itemsPage = itemsPage;
	}
	public List<T> getListPage() {
		return listPage;
	}
	public void setListPage(List<T> listPage) {
		this.listPage = listPage;
	}
	
	
}
