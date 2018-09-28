package com.tdts.service;

import java.util.List;
import java.util.Map;

public interface UserRightService {

	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	List<Map<String, Object>> getUserInfo(String userName);
	
	/**
	 * 根据用户ID获取菜单
	 * @param userID
	 * @return
	 */
	List<Map<String, Object>> getMenuInfo(String userID);
	
	/**
	 * 根据用户id获取功能权限
	 */
	List<Map<String, Object>> getBtnRight(String userID);
	
	/**
	 * 查询用户所有权限（菜单+按钮）
	 */
	List<Map<String, Object>> getAllRightByUserID(String userID);
	
	/**
	 * 根据菜单ID和用户ID获取该用户在此菜单下的按钮权限
	 */
	List<Map<String, Object>> getBtnRightOfMenu(String userID, String menuID);
	
	/**
	 * 验证用户权限
	 */
	boolean checkUserRight(String userID, String menuID);
	
	/**
	 * 获取所有权限
	 */
	List<Map<String, Object>> getRightTree();
	
	/**
	 * 根据用户ID获取拥有权限
	 */
	List<Map<String, Object>> getRightTreeByUserID(String userID);
}
