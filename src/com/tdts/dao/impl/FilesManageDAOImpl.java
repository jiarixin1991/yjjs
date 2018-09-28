package com.tdts.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.BasicDao;
import com.tdts.dao.FilesManageDAO;
import com.tdts.util.SqlserJdbcUtil;

public class FilesManageDAOImpl implements FilesManageDAO {

	BasicDao basicDao = new BasicDaoImpl();
	

	@Override
	/**
	 * 原件挂接,  修改files表条目ID   （挂接：docID为条目ID； 取消挂接：docID为空）
	 */
	public int hangUpFile(String fileID, String docID) throws SQLException {
		String sql = "UPDATE Files SET DOCID = '"+docID+"' WHERE FILEID = '"+fileID+"'";
		return SqlserJdbcUtil.execSQLWithTrans(sql);
	}
	
	@Override
	/**
	 * 添加原件
	 */
	public int addFile(Map<String, Object> fileInfo) throws SQLException{
		return basicDao.saveTable("Files", fileInfo);
	}
	
	@Override
	/**
	 * 批量添加原件
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public int addFilesBatch(List<String> fields, List<Map<String, Object>> filesInfo) throws SQLException, ClassNotFoundException, IOException{
		Connection conn = SqlserJdbcUtil.getConnection();
        conn.setAutoCommit(false);
		try{
			String field = "INSERT INTO Files (";
			String value = ") VALUES (";
			for(int i=0; i<fields.size(); i++){
				field += fields.get(i) + ",";
				value += "?,";
			}
			String sql = field.substring(0, field.length()-1) + value.substring(0, value.length()-1) + ")";
			System.out.println("批量添加原件："+sql);
			
			int res = SqlserJdbcUtil.execSQLWithTrans(conn, sql, fields, filesInfo);
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
	 * 根据原件ID查询原件信息
	 */
	public Map<String, Object> getFileInfo(String fileId) throws SQLException {
		String sql = "SELECT * FROM Files where fileId = '"+fileId+"'";
		List<Map<String, Object>> list = SqlserJdbcUtil.execSQL(sql);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	/**
	 * 查询系统下所有文件
	 */
	public List<Map<String, Object>> getFilesList(String sysID) throws SQLException {
		String sql = "SELECT * FROM Files WHERE SYSID = '"+sysID+"'";
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	/**
	 * 查询该条目已挂接文件
	 */
	public List<Map<String, Object>> listFileHasHanged(String sysID, String docID) throws SQLException {
		String sql = "SELECT * FROM Files WHERE SYSID = '"+sysID+"' AND DOCID='"+docID+"'";
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 获取条目下已挂接文件数量
	 */
	public int countFileHasHanged(String sysID, String docID) throws SQLException {
		String sql = "SELECT count(1) count FROM Files WHERE SYSID = '"+sysID+"' AND DOCID='"+docID+"'";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}
	
	@Override
	/**
	 * 查询系统下未挂接文件总数
	 */
	public int countFileNotHanged(String sysID) throws SQLException {
		String sql = "SELECT count(1) count FROM Files WHERE SYSID='"+sysID+"' AND DOCID is null OR len(DOCID)=0";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}

	@Override
	/**
	 * 查询系统下未挂接文件  分页
	 */
	public List<Map<String, Object>> pageFileNotHanged(String sysID, int pageNo, int pageSize) throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY RegDate desc) AS rowNum FROM Files WHERE sysID = '"+sysID+"' AND DOCID is null OR len(DOCID)=0) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		System.out.println(sql);
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 关键字搜索未挂接原件总数
	 */
	public int countFileByKeyWord(String sysID, String keyWord) throws SQLException {
		String sql = "SELECT count(1) count FROM Files WHERE SYSID='"+sysID+"' AND FILENAME LIKE '%"+keyWord+"%' AND DOCID is null OR len(DOCID)=0";
		System.out.println(sql);
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}
	
	@Override
	/**
	 * 分页  关键字搜索未挂接原件
	 */
	public List<Map<String, Object>> pageFileByKeyWord(String sysID, int pageNo, int pageSize, String keyWord) throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY RegDate desc) AS rowNum FROM Files WHERE sysID = '"+sysID+"' AND FILENAME LIKE '%"+keyWord+"%' AND DOCID is null OR len(DOCID)=0) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		System.out.println(sql);
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 查询原件总条数
	 */
	public int getFilesCount(String sysID) throws SQLException {
		String sql = "SELECT count(*) count FROM Files where sysID = '"+sysID+"'";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}
	
	@Override
	/**
	 * 分页查询原件信息
	 */
	public List<Map<String, Object>> getFilesByPage(int pageNo, int pageSize) throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY REGDATE desc) AS rowNum FROM Files) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+"";
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 分页查询原件信息
	 */
	public List<Map<String, Object>> getFilesByPage(int pageNo, int pageSize, String sysID) throws SQLException {
		String sql = "SELECT top "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY REGDATE desc) AS rowNum FROM Files) AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+" AND SYSID = '"+sysID+"'";
		return SqlserJdbcUtil.execSQL(sql);
	}
	
	@Override
	/**
	 * 删除原件
	 */
	public int delFile(String fileID) throws SQLException {
		String sql = "DELETE FROM Files WHERE FILEID='"+fileID+"'";
		return SqlserJdbcUtil.execSQLWithTrans(sql);
	}
	
	@Override
	/**
	 * 批量删除原件
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public int batchDelFile(List<String> filesID) throws SQLException, ClassNotFoundException, IOException{
		Connection conn = SqlserJdbcUtil.getConnection();
		conn.setAutoCommit(false);
		int ret = 1;
		String sql ;
		try{
			sql = "DELETE FROM Files WHERE FILEID = ?";
			ret = SqlserJdbcUtil.execSQLWithTrans(conn, sql, "FILEID", filesID);
			if(ret == 1){
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
	 * 修改原件
	 */
	public int updateFile(String fileID, Map<String, Object> fileInfo) throws SQLException {
		return basicDao.updateTable("Files", fileInfo, "FILEID = '"+fileID+"'");
	}

	@Override
	//根据关键字查询所有原件
	public List<Map<String, Object>> getAllFileBykeyWord(String keyWord,
			String sysID, int pageNo, int pageSize) throws SQLException {
		String sql = "SELECT TOP "+pageSize+"* FROM "
				+ "(SELECT *, ROW_NUMBER() OVER(ORDER BY REGDATE desc) AS rowNum FROM Files WHERE SYSID='"+sysID+"' AND FILENAME LIKE '%"+keyWord+"%') AS b "
				+ "WHERE rowNum > "+(pageNo - 1) * pageSize+" AND SYSID = '"+sysID+"'";
		return SqlserJdbcUtil.execSQL(sql);
	}

	@Override
	//根据关键字查询所有原件总数
	public int countAllFileByKeyWord(String keyWord, String sysID)
			throws SQLException {
		String sql = "SELECT COUNT(1) count FROM Files WHERE SYSID='"+sysID+"' AND FILENAME LIKE '%"+keyWord+"%'";
		return Integer.parseInt(SqlserJdbcUtil.execSQL(sql).get(0).get("count").toString());
	}


}
