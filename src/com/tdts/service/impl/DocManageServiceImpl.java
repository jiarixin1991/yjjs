package com.tdts.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.FileManager;
import com.tdts.dao.DocManageDAO;
import com.tdts.dao.FilesManageDAO;
import com.tdts.dao.impl.DocManageDAOImpl;
import com.tdts.dao.impl.FilesManageDAOImpl;
import com.tdts.service.DocManageService;
import com.tdts.util.SqlserJdbcUtil;

public class DocManageServiceImpl implements DocManageService {

	DocManageDAO docManage = new DocManageDAOImpl();
	FilesManageDAO fileManage = new FilesManageDAOImpl();
	@Override
	/**
	 * 添加条目
	 */
	public int addDoc(String tableName, Map<String, Object> docInfo) {
		try {
			return docManage.addDoc(tableName, docInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 批量添加条目
	 */
	public int addDocBatch(String tableName, List<String> fields,
			List<Map<String, Object>> docInfos) {
		try {
			return docManage.addDocBatch(tableName, fields, docInfos);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 删除条目
	 */
	public int delDoc(String tableName, String docId) {
		try {
			return docManage.delDoc(tableName, docId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 编辑条目
	 */
	public int updateDoc(String tableName, Map<String, Object> docInfo,
			String docID) {
		try {
			return docManage.updateDoc(tableName, docInfo, docID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 获取所有条目
	 */
	public List<Map<String, Object>> getAllDoc(String tableName) {
		try {
			return docManage.getAllDoc(tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获取条目总数
	 */
	public int getAllDocCount(String tableName) {
		try {
			return docManage.getAllDocCount(tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 分页获取条目
	 */
	public List<Map<String, Object>> getDocByPage(String tableName, int pageNo,
			int pageSize) {
		try {
			return docManage.getDocByPage(tableName, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字搜索条目
	public List<Map<String, Object>> getDocByKeyWord(String tableName,
			String keyWord, int page, int limit) {
		try {
			return docManage.getDocByKeyWord(tableName, keyWord, page, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字搜索条目数量
	public int countDocByKeyWord(String tableName, String keyWord) {
		try {
			return docManage.countDocByKeyWord(tableName, keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	//根据ID批量删除条目
	public int delDocBatch(String tableName, List<String> docIDs) {
		try {
			return docManage.delDocBatch(tableName, docIDs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
