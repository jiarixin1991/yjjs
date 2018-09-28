package com.tdts.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DocManageDAO {

	/**
	 * 条目管理
	 */
	
	/**
	 * 添加条目
	 * @param tableName
	 * @param docInfo
	 * @return
	 * @throws SQLException
	 */
	int addDoc(String tableName, Map<String, Object> docInfo) throws SQLException; 
	
	/**
	 * 批量添加条目
	 * @param tableName
	 * @param fields
	 * @param docInfos
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int addDocBatch(String tableName, List<String> fields, List<Map<String, Object>> docInfos) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 删除条目
	 * @param tableName
	 * @param docId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int delDoc(String tableName, String docId) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 批量删除条目
	 * @param tableName
	 * @param docIDs
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int delDocBatch(String tableName, List<String> docIDs) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 修改条目
	 * @param tableName
	 * @param docInfo
	 * @param docID
	 * @return
	 * @throws SQLException
	 */
	int updateDoc(String tableName, Map<String, Object> docInfo, String docID) throws SQLException;
	
	/**
	 * 根据条目名称查询条目信息
	 * @param tableName
	 * @param docName
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getDocInfoByDocName(String tableName, String docName) throws SQLException;
	
	/**
	 * 获取所有条目
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getAllDoc(String tableName) throws SQLException;
	
	/**
	 * 获取条目总数
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	int getAllDocCount(String tableName) throws SQLException;
	
	/**
	 * 分页获取条目
	 * @param tableName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getDocByPage(String tableName, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 根据关键字搜索条目
	 * @param tableName
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getDocByKeyWord(String tableName, String keyWord, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 关键字搜索条目数量
	 * @param tableName
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	int countDocByKeyWord(String tableName, String keyWord) throws SQLException;
	
}
