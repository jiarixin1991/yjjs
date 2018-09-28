package com.tdts.service;

import java.util.List;
import java.util.Map;

public interface DocManageService {

	/**
	 * 添加条目
	 * @param tableName
	 * @param docInfo
	 * @return
	 */
	int addDoc(String tableName, Map<String, Object> docInfo); 
	
	/**
	 * 批量添加条目
	 * @param tableName
	 * @param fields
	 * @param docInfos
	 * @return
	 */
	int addDocBatch(String tableName, List<String> fields, List<Map<String, Object>> docInfos);
	
	/**
	 * 删除条目
	 * @param tableName
	 * @param docId
	 * @return
	 */
	int delDoc(String tableName, String docId);
	
	/**
	 * 修改条目
	 * @param tableName
	 * @param docInfo
	 * @param docID
	 * @return
	 */
	int updateDoc(String tableName, Map<String, Object> docInfo, String docID);
	
	/**
	 * 获取所有条目
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getAllDoc(String tableName);
	
	/**
	 * 获取条目总数
	 * @param tableName
	 * @return
	 */
	int getAllDocCount(String tableName);
	
	/**
	 * 分页获取条目
	 * @param tableName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> getDocByPage(String tableName, int pageNo, int pageSize);
	
	/**
	 * 根据关键字搜索条目
	 */
	List<Map<String, Object>> getDocByKeyWord(String tableName, String keyWord, int page, int limit);
	
	/**
	 * 根据关键字搜索条目数量
	 */
	int countDocByKeyWord(String tableName, String keyWord);
	
	/**
	 * 根据ID批量删除条目
	 */
	int delDocBatch(String tableName, List<String> docIDs);
}
