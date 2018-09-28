package com.tdts.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tdts.dao.BasicDao;
import com.tdts.dao.DocManageDAO;
import com.tdts.util.SqlserJdbcUtil;

public class DocManageDAOImpl implements DocManageDAO {

	BasicDao basicDao = new BasicDaoImpl();
	
	 public static void main(String[] args) {
		DocManageDAO docManage = new DocManageDAOImpl();
		try {
			//int res = docManage.delDoc("Document123456789", "001");
			List<String> docIDs = new ArrayList<String>();
			docIDs.add("001");
			docIDs.add("002");
			int res2 = docManage.delDocBatch("Document123456789", docIDs);
			//System.out.println("单个删除："+res);
			System.out.println("多个删除："+res2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * 添加条目
	 */
	public int addDoc(String tableName, Map<String, Object> docInfo)
			throws SQLException {
		return basicDao.saveTable(tableName, docInfo);
	}

	@Override
	/**
	 * 批量添加条目
	 */
	public int addDocBatch(String tableName, List<String> fields,
			List<Map<String, Object>> docInfos) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = SqlserJdbcUtil.getConnection();
        conn.setAutoCommit(false);
		try{
			String field = "INSERT INTO "+tableName+" (";
			String value = ") VALUES (";
			for(int i=0; i<fields.size(); i++){
				field += fields.get(i) + ", ";
				value += "?, ";
			}
			String sql = field.substring(0, field.length()-2) + value.substring(0, value.length()-2) + ")";
			System.out.println(sql);
			
			int res = SqlserJdbcUtil.execSQLWithTrans(conn, sql, fields, docInfos);
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
	//批量删除条目     (取消要删除条目下挂接的文件)
	public int delDocBatch(String tableName, List<String> docIDs)
			throws SQLException, ClassNotFoundException, IOException{
		String sql = "";
		Connection conn = SqlserJdbcUtil.getConnection();
		conn.setAutoCommit(false);
		int res = 1;
		try{
			sql = "DELETE FROM "+tableName+" WHERE DOCID = ?";
			res = SqlserJdbcUtil.execSQLWithTrans(conn, sql, docIDs);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			sql = "UPDATE Files SET DOCID = null WHERE DOCID = ?";
			res = SqlserJdbcUtil.execSQLWithTrans(conn, sql, docIDs);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			conn.commit();
			return 0;
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
	}
	
	@Override
	/**
	 * 删除条目      (条目下挂接的文件取消挂接状态)
	 */
	public int delDoc(String tableName, String docId) throws SQLException, ClassNotFoundException, IOException {
		String sql = "";
		Connection conn = SqlserJdbcUtil.getConnection();
		conn.setAutoCommit(false);
		int res = 1;
		try{
			sql = "DELETE FROM "+tableName+" WHERE docId = '"+docId+"'";
			res = SqlserJdbcUtil.execSQLWithTrans(conn, sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			sql = "UPDATE Files SET DOCID = null WHERE DOCID = '"+docId+"'";
			res = SqlserJdbcUtil.execSQLWithTrans(conn, sql);
			if(res == 1){
				conn.rollback();
				return 1;
			}
			conn.commit();
			return 0;
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
	}

	@Override
	/**
	 * 修改条目信息
	 */
	public int updateDoc(String tableName, Map<String, Object> docInfo, String docID)
			throws SQLException {
		String param = "docID='"+docID+"'";
		return basicDao.updateTable(tableName, docInfo, param);
	}
	
	@Override
	/**
	 * 根据条目名称查询条目信息
	 */
	public List<Map<String, Object>> getDocInfoByDocName(String tableName, String docName) throws SQLException {
		String sql = "SELECT * FROM "+tableName+" WHERE DOCNAME LIKE '%"+docName+"'%";
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	/**
	 * 获取所有条目信息
	 */
	public List<Map<String, Object>> getAllDoc(String tableName)
			throws SQLException {
		String sql = "SELECT * FROM "+tableName;
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	/**
	 * 获取条目总数
	 */
	public int getAllDocCount(String tableName) throws SQLException {
		String sql = "SELECT COUNT(1) count FROM "+tableName;
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}

	@Override
	/**
	 * 分页获取条目信息
	 */
	public List<Map<String, Object>> getDocByPage(String tableName, int pageNo,
			int pageSize) throws SQLException {
		String sql = "SELECT TOP "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY STIME desc) AS rowNum FROM "+tableName+") AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	//根据关键字搜索条目
	public List<Map<String, Object>> getDocByKeyWord(String tableName,
			String keyWord, int pageNo, int pageSize) throws SQLException {
		String sql = "SELECT TOP "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY STIME desc) AS rowNum FROM "+tableName+" WHERE DOCNAME LIKE '%"+keyWord+"%') AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		return null;
	}

	@Override
	//关键字搜索条目数量
	public int countDocByKeyWord(String tableName, String keyWord)
			throws SQLException {
		String sql = "SELECT COUNT(1) count FROM "+tableName+" WHERE DOCNAME LIKE '%"+keyWord+"%'";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}

}
