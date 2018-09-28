package com.tdts.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.FilesManageDAO;
import com.tdts.dao.impl.FilesManageDAOImpl;
import com.tdts.service.FilesManageService;

public class FilesManageServiceImpl implements FilesManageService {

	FilesManageDAO fileManage = new FilesManageDAOImpl();

	@Override
	/**
	 * 原件挂接
	 */
	public int hangUpFile(String fileID, String docID) {
		try {
			return fileManage.hangUpFile(fileID, docID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 原件取消挂接
	 */
	public int cancleHangUp(String fileID) {
		try {
			return fileManage.hangUpFile(fileID, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	/**
	 * 添加原件
	 */
	public int addFile(Map<String, Object> fileInfo){
		try {
			return fileManage.addFile(fileInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	/**
	 * 批量添加原件
	 */
	public int addFilesBatch(List<String> fields, List<Map<String, Object>> filesInfo) {
		try {
			return fileManage.addFilesBatch(fields, filesInfo);
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
	 * 删除原件
	 */
	public int delFile(String fileID) {
		try {
			return fileManage.delFile(fileID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 批量删除原件
	 */
	public int batchDelFiles(List<String> filesID) {
		try {
			return fileManage.batchDelFile(filesID);
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
	 * 查询本系统内的所有原件
	 */
	public List<Map<String, Object>> getFilesList(String sysID){
		try {
			return fileManage.getFilesList(sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获取原件总数
	 */
	public int getFilesCount(String sysID) {
		try {
			return fileManage.getFilesCount(sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 分页查询原件信息
	 */
	public List<Map<String, Object>> getFilesByPage(int pageNo, int limit, String sysID) {
		try {
			return fileManage.getFilesByPage(pageNo, limit, sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 修改原件信息
	 */
	public int updateFile(String fileID, Map<String, Object> fileInfo) {
		try {
			return fileManage.updateFile(fileID, fileInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 获取已经挂接的文件
	 */
	public List<Map<String, Object>> getListHasHanged(String sysID,
			String docID) {
		try {
			return fileManage.listFileHasHanged(sysID, docID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	/**
	 * 获取条目下已挂接文件数量
	 */
	public int countFileHasHanged(String sysID, String docID) {
		try {
			return fileManage.countFileHasHanged(sysID, docID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 获取未挂接文件
	 */
	public List<Map<String, Object>> pageFileNotHanged(String sysID,
			int pageNo, int pageSize) {
		try {
			return fileManage.pageFileNotHanged(sysID, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 未挂接文件数量
	 */
	public int countFileNotHanged(String sysID) {
		try {
			return fileManage.countFileNotHanged(sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	//根据文件ID获取文件内容
	public Map<String, Object> getFileInfoByID(String fileID) {
		try {
			return fileManage.getFileInfo(fileID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//关键字查询原件总数
	public int countFileByKeyWord(String sysID, String keyWord) {
		try {
			return fileManage.countFileByKeyWord(sysID, keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	//关键字分页查询原件
	public List<Map<String, Object>> listFileByKeyWord(String sysID,
			int pageNo, int pageSize, String keyWord) {
		try {
			return fileManage.pageFileByKeyWord(sysID, pageNo, pageSize, keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字搜索原件
	public List<Map<String, Object>> getAllFileByKeyWord(String keyWord,
			String sysID, int pageNo, int pageSize) {
		try {
			return fileManage.getAllFileBykeyWord(keyWord, sysID, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字搜索文件总数
	public int countAllFileByKeyWord(String keyWord, String sysID) {
		try {
			return fileManage.countAllFileByKeyWord(keyWord, sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
