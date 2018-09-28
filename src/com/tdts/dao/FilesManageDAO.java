package com.tdts.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FilesManageDAO {

	/**
	 * 原件挂接  （挂接：docID为条目ID； 取消挂接：docID为空）
	 * @param fileID
	 * @param docID
	 * @return
	 * @throws SQLException
	 */
	int hangUpFile(String fileID, String docID) throws SQLException;
	
	/**
	 * 添加原件
	 * @param fileInfo
	 * @return
	 * @throws SQLException
	 */
	int addFile(Map<String, Object> fileInfo) throws SQLException;
	
	/**
	 * 批量添加原件
	 * @param fields
	 * @param filesInfo
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int addFilesBatch(List<String> fields, List<Map<String, Object>> filesInfo) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 查询原件  (范围待定)
	 * @param fileId
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> getFileInfo(String fileId) throws SQLException;
	
	/**
	 * 查询本系统内的所有原件
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getFilesList(String sysID) throws SQLException;

	/**
	 * 根据条目ID查询已挂接文件
	 * @param sysID
	 * @param docID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> listFileHasHanged(String sysID, String docID) throws SQLException;
	
	/**
	 * 获取条目下已挂接文件数量
	 */
	int countFileHasHanged(String sysID, String docID) throws SQLException;

	/**
	 * 查询系统下未挂接文件总数
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	int countFileNotHanged(String sysID) throws SQLException;

	/**
	 * 查询系统下未挂接文件  分页
	 * @param sysID
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> pageFileNotHanged(String sysID, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 关键字搜索未挂接原件总数
	 */
	int countFileByKeyWord(String sysID, String keyWord) throws SQLException;
	
	/**
	 * 分页  关键字搜索未挂接原件
	 */
	List<Map<String, Object>> pageFileByKeyWord(String sysID, int pageNo, int pageSize, String keyWord) throws SQLException;
	
	/**
	 * 查询原件总条数
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	int getFilesCount(String sysID) throws SQLException;
	
	/**
	 * 分页查询原件信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getFilesByPage(int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 分页查询本系统内的原件信息
	 * @param pageNo
	 * @param pageSize
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getFilesByPage(int pageNo, int pageSize, String sysID) throws SQLException;

	/**
	 * 删除原件
	 * @param fileID
	 * @return
	 * @throws SQLException
	 */
	int delFile(String fileID) throws SQLException;
	
	/**
	 * 批量删除原件
	 * @param filesID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int batchDelFile(List<String> filesID) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 修改原件
	 * @param fileID
	 * @param fileInfo
	 * @return
	 * @throws SQLException
	 */
	int updateFile(String fileID, Map<String, Object> fileInfo) throws SQLException;
	
	/**
	 * 根据关键字查询所有原件
	 * @param keyWord
	 * @param sysID
	 * @param pageno
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getAllFileBykeyWord(String keyWord, String sysID, int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 根据关键字获取所有原件总数
	 * @param keyWord
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	int countAllFileByKeyWord(String keyWord, String sysID) throws SQLException;
}
