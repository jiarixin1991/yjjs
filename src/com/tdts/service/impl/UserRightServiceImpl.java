package com.tdts.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.UserManageDAO;
import com.tdts.dao.impl.UserManageDAOImpl;
import com.tdts.service.UserRightService;

public class UserRightServiceImpl implements UserRightService {

	private UserManageDAO userManage = new UserManageDAOImpl();
	@Override
	//获取用户信息
	public List<Map<String, Object>> getUserInfo(String userName) {
		try {
			return userManage.getLoginIngo(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//获取菜单信息
	public List<Map<String, Object>> getMenuInfo(String userID) {
		try {
			return userManage.getUserRight(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//获取用户功能权限
	public List<Map<String, Object>> getBtnRight(String userID) {
		try {
			return userManage.getUserBtnRight(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//获取用户所有权限
	public List<Map<String, Object>> getAllRightByUserID(String userID) {
		try {
			return userManage.getAllUserRight(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//获取菜单下功能权限
	public List<Map<String, Object>> getBtnRightOfMenu(String userID,
			String menuID) {
		try {
			return userManage.getUserBtnRightByMenuID(userID, menuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//检查用户权限
	public boolean checkUserRight(String userID, String menuID) {
		try {
			return userManage.checkUserRight(userID, menuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	//获取所有权限信息
	public List<Map<String, Object>> getRightTree() {
		try {
			return userManage.getRightTree();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据用户ID获取权限信息
	public List<Map<String, Object>> getRightTreeByUserID(String userID) {
		try {
			return userManage.getRightTreeByUserID(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
