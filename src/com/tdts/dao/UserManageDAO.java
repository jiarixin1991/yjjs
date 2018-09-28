package com.tdts.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserManageDAO {

	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getLoginIngo(String userName) throws SQLException;
	
	/**
	 * 查询用户菜单权限
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getUserRight(String userID) throws SQLException;
	
	/**
	 * 查询用户功能权限
	 */
	List<Map<String, Object>> getUserBtnRight(String userID) throws SQLException; 
	
	/**
	 * 查询用户所有权限
	 */
	List<Map<String, Object>> getAllUserRight(String userID) throws SQLException;
	
	/**
	 * 根据菜单ID获取用户的功能权限
	 * @param userID
	 * @param menuID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getUserBtnRightByMenuID(String userID, String menuID) throws SQLException; 
	
	/**
	 * 验证用户权限
	 */
	boolean checkUserRight(String userID, String menuID) throws SQLException;
	
	/**
	 * 获取菜单功能树
	 */
	List<Map<String, Object>> getRightTree() throws SQLException;
	
	/**
	 * 获取用户权限树
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getRightTreeByUserID(String userID) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	/**
	 * 为按钮添加权限
	 */
	int addBtnRight(Map<String, Object> btnMenuInfo) throws SQLException;
	
	/**
	 * 删除用户所有权限
	 */
	int delUserRight(Connection conn, String userID) throws SQLException;
	
	/**
	 * 给用户分配权限
	 */
	int saveUserRight(Connection conn, List<String> fields, List<Map<String, Object>> rightInfo) throws SQLException;
}
