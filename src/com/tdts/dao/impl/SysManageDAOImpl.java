package com.tdts.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.BasicDao;
import com.tdts.dao.SysManageDAO;
import com.tdts.util.SqlserJdbcUtil;

public class SysManageDAOImpl implements SysManageDAO {

	BasicDao basicDao = new BasicDaoImpl();
	
	@Override
	/**
	 * 添加系统   
	 */
	public int addSystem(Map<String, Object> sysInfo) throws SQLException {
		String sql = "INSERT INTO SystemTable (SYSID, SYSNAME, DOCNAME, STIME) VALUES ('"+sysInfo.get("SYSID")+"', '"+sysInfo.get("SYSNAME")+"', '"+sysInfo.get("DOCNAME")+"', GETDATE())";
		return SqlserJdbcUtil.execSQLWithTrans(sql);
	}

	@Override
	/**
	 * 根据ID删除系统    删除系统+删除文件
	 */
	public int delSystem(String sysID) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = SqlserJdbcUtil.getConnection();
		conn.setAutoCommit(false);
		String sql = "";
		try{
			//删除系统
			sql = "DELETE FROM SystemTable WHERE SYSID='"+sysID+"'";
			int res = SqlserJdbcUtil.execSQLWithTrans(conn, sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			//删除系统下的所有文件
			sql = "DELETE FROM Files WHERE SYSID = '"+sysID+"'";
			res = SqlserJdbcUtil.execSQLWithTrans(conn, sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			//删除系统下的条目表
			String sql2 = "SELECT tableName FROM SystemTable WHERE sysId = '"+sysID+"'";
			String docName = SqlserJdbcUtil.execSQL(sql2).get(0).get("tableName").toString();
			sql = "delete from " + docName;
			res = SqlserJdbcUtil.execSQLWithTrans(sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			//删除对应结构表中的数据
			sql = "delete from docFrame where tableName='"+docName+"'";
			res = SqlserJdbcUtil.execSQLWithTrans(sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			conn.commit();
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
		return 0;
	}

	@Override
	/**
	 * 修改系统信息
	 */
	public int updateSystem(Map<String, Object> sysInfo, String sysID)
			throws SQLException {
		return basicDao.updateTable("SystemTable", sysInfo, "SYSID='"+sysID+"'");
	}

	@Override
	/**
	 * 获取系统列表
	 */
	public List<Map<String, Object>> getSysList() throws SQLException {
		String sql = "SELECT * FROM SystemTable";
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 获取系统总条数
	 */
	public int getSysCount() throws SQLException{
		String sql = "SELECT COUNT(1) count FROM SystemTable";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}

	@Override
	/**
	 * 分页获取系统信息    根据创建时间排序
	 */
	public List<Map<String, Object>> getSysListByPage(int pageNo, int pageSize)
			throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY STIME desc) AS rowNum FROM SystemTable) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		System.out.println(sql);
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 根据关键字查询系统列表
	 */
	public List<Map<String, Object>> getSysListByKeyWord(int pageNo, int pageSize, String keyWord) throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY STIME desc) AS rowNum FROM SystemTable WHERE SYSNAME LIKE '%"+keyWord+"%') AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		System.out.println(sql);
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	//关键字查询系统总数
	public int countSysByKeyWord(String keyWord) throws SQLException {
		String sql = "SELECT count(1) count FROM SystemTable WHERE SYSNAME LIKE '"+keyWord+"'";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}
	
	@Override
	//根据ID获取系统信息
	public Map<String, Object> getSysInfoByID(String sysID) throws SQLException {
		String sql = "SELECT * FROM SystemTable WHERE sysID = '"+sysID+"'";
		List<Map<String, Object>> sysList = SqlserJdbcUtil.execSQL(sql);
		if(sysList.size() > 0){
			return sysList.get(0);
		}
		return null;
	}
	

}
