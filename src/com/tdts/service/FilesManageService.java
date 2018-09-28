package com.tdts.service;

import java.util.List;
import java.util.Map;

public interface FilesManageService {

	/**
	 * 原件挂接
	 * @param fileID
	 * @param docID
	 * @return
	 */
	int hangUpFile(String fileID, String docID);
	
	/**
	 * 原件取消挂接
	 * @param fileID
	 * @return
	 */
	int cancleHangUp (String fileID);
	
	/**
	 * 添加原件
	 * @param fileInfo
	 * @return
	 */
	int addFile(Map<String, Object> fileInfo);
	
	/**
	 * 批量添加原件
	 * @param fields
	 * @param filesInfo
	 * @return
	 */
	int addFilesBatch(List<String> fields, List<Map<String, Object>> filesInfo);
	
	/**
	 * 删除原件
	 * @param fileID
	 * @return
	 */
	int delFile(String fileID);
	
	/**
	 * 批量删除
	 * @param filesID
	 * @return
	 */
	int batchDelFiles(List<String> filesID);
	
	/**
	 * 查询本系统内的所有原件
	 * @param sysID
	 * @return
	 */
	List<Map<String, Object>> getFilesList(String sysID);
	
	/***
	 * 查询原件总条数
	 * @param sysID
	 * @return
	 */
	int getFilesCount(String sysID);
	
	/***
	 * 分页查询原件信息
	 * @param pageNo
	 * @param limit
	 * @param sysID
	 * @return
	 */
	List<Map<String, Object>> getFilesByPage(int pageNo, int limit, String sysID);
	
	/***
	 * 修改原件信息
	 * @param fileID
	 * @param fileInfo
	 * @return
	 */
	int updateFile(String fileID, Map<String, Object> fileInfo);
	
	/***
	 * 获取已经挂接的原件信息
	 * @param sysID
	 * @param docID
	 * @return
	 */
	List<Map<String, Object>> getListHasHanged(String sysID, String docID);
	
	/**
	 * 获取条目下已挂接文件数量
	 */
	int countFileHasHanged(String sysID, String docID);
	
	/**
	 * 获取未挂接的文件
	 */
	List<Map<String, Object>> pageFileNotHanged(String sysID, int pageNo, int pageSize);
	
	/**
	 * 获取未挂接文件总数
	 */
	int countFileNotHanged(String sysID);
	
	/**
	 * 根绝文件ID获取文件信息
	 */
	Map<String, Object> getFileInfoByID(String fileID);
	
	/**
	 * 根据关键字查询未挂接文件总数
	 */
	int countFileByKeyWord(String sysID, String keyWord);
	
	/**
	 * 根据关键字分页查询未挂接文件
	 */
	List<Map<String, Object>> listFileByKeyWord(String sysID, int pageNo, int pageSize, String keyWord);
	
	/**
	 * 根据关键字搜索原件
	 * @param keyWord
	 * @param sysID
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> getAllFileByKeyWord(String keyWord, String sysID, int pageNo, int pageSize);
	
	
	int countAllFileByKeyWord(String keyWord, String sysID);
}
