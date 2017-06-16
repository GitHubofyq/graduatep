package com.eShop.utils;

import java.util.UUID;

/**
 * 工具类：生成随机字符串
 * @author Lenovo
 *
 */
public class UUIDUtils {
	//获取随机字符串
	public static String getUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
