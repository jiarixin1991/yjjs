package com.tdts.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdts.dao.BasicDao;
import com.tdts.dao.SecretManageDAO;
import com.tdts.util.SqlserJdbcUtil;

public class SecretManageDAOImpl implements SecretManageDAO {

	BasicDao basicDao = new BasicDaoImpl();
	
	@Override
	/**
	 * 添加涉密信息
	 */
	public int addSecretInfo(Map<String, Object> secretInfo)
			throws SQLException {
		return basicDao.saveTable("IsSecret", secretInfo);
	}
	
	/**
	 * 批量添加涉密内容
	 * @param fields添加的内容包含哪些字段；  secrets多条内容集合
	 */
	public int addSecretInfoBatch(List<String> fields, List<Map<String, Object>> secrets) throws SQLException, ClassNotFoundException, IOException{
		Connection conn = SqlserJdbcUtil.getConnection();
        conn.setAutoCommit(false);
		try{
			String field = "INSERT INTO IsSecret (";
			String value = ") VALUES (";
			for(int i=0; i<fields.size(); i++){
				field += fields.get(i) + ", ";
				value += "?, ";
			}
			String sql = field.substring(0, field.length()-2) + value.substring(0, value.length()-2) + ")";
			System.out.println(sql);
			
			//String sql2 = "INSERT INTO IsSecret (SecId, TABLENAME, SECRETNAME) values (?, ?, ?)";
			int res = SqlserJdbcUtil.execSQLWithTrans(conn, sql, fields, secrets);
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
	 * 删除涉密信息
	 */
	public int delSecretInfo(String secID) throws SQLException {
		String sql = "DELETE FROM IsSecret where SecId = '"+secID+"'";
		return SqlserJdbcUtil.execSQLWithTrans(sql);
	}
	
	/**
	 * 清空涉密表信息
	 */
	public int clearSecretInfo() throws SQLException{
		String sql = "DELETE FROM IsSecret";
		return SqlserJdbcUtil.execSQLWithTrans(sql);
	}
	
	/**
	 * 修改涉密信息
	 */
	public int updateSecretInfo(String secID, Map<String, Object> secInfo) throws SQLException{
		return basicDao.updateTable("IsSecret", secInfo, "SceId = '"+secID+"'");
	}

	@Override
	/**
	 * 获取所有涉密信息
	 */
	public List<Map<String, Object>> getSecretInfo() throws SQLException {
		String sql = "select * from IsSecret";
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 获取涉密信息总条数
	 */
	public int getSecretCount() throws SQLException{
		String sql = "SELECT COUNT(1) count FROM IsSecret";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}

	@Override
	/**
	 * 分页查询涉密信息
	 */
	public List<Map<String, Object>> getSecretByPage(int pageNo, int pageSize)
			throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY STIME desc) AS rowNum FROM IsSecret) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		return SqlserJdbcUtil.execSQL(sql);
	}


}
