package com.tdts.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tdts.dao.BasicDao;
import com.tdts.dao.UserManageDAO;
import com.tdts.util.SqlserJdbcUtil2;

public class UserManageDAOImpl implements UserManageDAO {

	BasicDao basicDao = new BasicDaoImpl();
	@Override
	//查询用户信息
	public List<Map<String, Object>> getLoginIngo(String userName)
			throws SQLException {
		String sql = "SELECT * FROM REGUSERINFO WHERE USERNAME='"+userName+"'";
		return SqlserJdbcUtil2.execSQL(sql);
	}
	
	@Override
	//获取用户菜单权限         没有子菜单的并且menuType=A的时移交的菜单
	public List<Map<String, Object>> getUserRight(String userID)
			throws SQLException {
		String sql = "SELECT USERRIGHT.USERID, MENUINFO.* FROM MENUINFO LEFT JOIN USERRIGHT "
				+ "ON USERRIGHT.MENUID=MENUINFO.MENUID WHERE USERRIGHT.USERID='"+userID+"' "
						+ "AND MENUINFO.SUBMENU IS NULL OR MENUINFO.SUBMENU='' AND MENUINFO.MENUTYPE = 'A'";
		System.out.println(sql);
		return SqlserJdbcUtil2.execSQL(sql);
	}

	@Override
	//获取用户模块权限     按钮权限    子菜单不为空，并且menuType=A   
	public List<Map<String, Object>> getUserBtnRight(String userID)
			throws SQLException {
		String sql = "SELECT USERRIGHT.USERID, MENUINFO.* FROM MENUINFO LEFT JOIN USERRIGHT "
				+ "ON USERRIGHT.MENUID = MENUINFO.MENUID WHERE USERRIGHT.USERID='"+userID+"' "
						+ "AND MENUINFO.SUBMENU IS NOT NULL AND MENUINFO.SUBMENU<>'' AND MENUINFO.MENUTYPE = 'A'";
		return SqlserJdbcUtil2.execSQL(sql);
	}
	
	//获取用户所有权限
	public List<Map<String, Object>> getAllUserRight(String userID) throws SQLException {
		String sql = "SELECT USERRIGHT.USERID, MENUINFO.* FROM MENUINFO LEFT JOIN USERRIGHT "
				+ "ON USERRIGHT.MENUID = MENUINFO.MENUID WHERE USERRIGHT.USERID='"+userID+"' AND MENUINFO.MENUTYPE = 'A'";
		System.out.println(sql);
		return SqlserJdbcUtil2.execSQL(sql);
	}

	@Override
	//根据菜单名获取用户的功能权限    根据用户获取当前菜单下所有按钮权限
	public List<Map<String, Object>> getUserBtnRightByMenuID(String userID,
			String menuID) throws SQLException {
		String sql = "SELECT USERRIGHT.USERID, MENUINFO.* FROM MENUINFO LEFT JOIN USERRIGHT ON USERRIGHT.MENUID = MENUINFO.MENUID "
				+ "WHERE USERRIGHT.USERID = '"+userID+"' AND MENUINFO.MAINMENU = (SELECT MAINMENU FROM MENUINFO WHERE MENUID = '"+menuID+"') "
				+ "AND SUBMENU IS NOT NULL AND SUBMENU<>'' and MENUTYPE = 'A'";
		System.out.println(sql);
		return SqlserJdbcUtil2.execSQL(sql);
	}

	@Override
	//判断用户权限
	public boolean checkUserRight(String userID, String menuID) throws SQLException {
		String sql = "SELECT * FROM USERRIGHT WHERE USERID='"+userID+"' AND MENUID='"+menuID+"'";
		return SqlserJdbcUtil2.execSQL(sql).size() > 0 ? true : false;
	}

	@Override
	//为按钮添加权限
	public int addBtnRight(Map<String, Object> btnMenuInfo) throws SQLException {
		return basicDao.saveTable("MENUINFO", btnMenuInfo);
	}

	@Override
	//给用户分配权限
	public int saveUserRight(Connection conn, List<String> fields,
			List<Map<String, Object>> rightInfo) throws SQLException {
		String sql = "INSERT INTO USERRIGHT (USERID, MENUID) VALUES (?, ?)";
		return SqlserJdbcUtil2.execSQLWithTrans(conn, sql, fields, rightInfo);
	}

	@Override
	//删除用户权限
	public int delUserRight(Connection conn, String userID) throws SQLException {
		String sql = "DELETE FROM USERRIGHT WHERE USERID = '"+userID+"'";
		return SqlserJdbcUtil2.execSQLWithTrans(conn, sql);
	}

	@Override
	//获取所有权限
	public List<Map<String, Object>> getRightTree()
			throws SQLException {
		String sql = "SELECT *, CASE WHEN middle.p is not null "
				+ "THEN (SELECT MENUINFO.MENUID FROM MENUINFO WHERE middle.MAINMENU = MENUINFO.MAINMENU AND MENUINFO.SUBMENU is null) "
				+ "ELSE '0' END pId FROM "
				+ "(SELECT MENUID id, rtrim(ISNULL(SUBMENU, MAINMENU)) name, rtrim(MAINMENU) MAINMENU, rtrim(ISNULL(SUBMENU, NULL)) p FROM MENUINFO WHERE MENUINFO.MENUTYPE='A') middle";
		return SqlserJdbcUtil2.execSQL(sql);
	}

	@Override
	//根据用户ID获取权限树
	public List<Map<String, Object>> getRightTreeByUserID(String userID)
			throws SQLException {
		String sql = "SELECT *, CASE WHEN middle.p is not null "
				+ "THEN (SELECT MENUINFO.MENUID FROM MENUINFO WHERE middle.MAINMENU = MENUINFO.MAINMENU AND MENUINFO.SUBMENU is null) "
				+ "ELSE '0' END pId FROM "
				+ "(SELECT MENUID id, rtrim(ISNULL(SUBMENU, MAINMENU)) name, rtrim(MAINMENU) MAINMENU, rtrim(ISNULL(SUBMENU, NULL)) p FROM MENUINFO WHERE MENUINFO.MENUTYPE='A') middle "
				+ "LEFT JOIN USERRIGHT on middle.id = USERRIGHT.MENUID WHERE USERRIGHT.USERID='"+userID+"'";
		return SqlserJdbcUtil2.execSQL(sql);
	}

	
	public static void main(String[] args) {
		UserManageDAO userManage = new UserManageDAOImpl();
		try {
			List<Map<String, Object>> list = userManage.getRightTree();
			String res = JSON.toJSONString(list);     //.replace("\"", "").replace("name:", "name:\"").replace(",pid", "\",pid")
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

	
}
