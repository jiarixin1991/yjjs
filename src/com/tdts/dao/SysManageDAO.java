package com.tdts.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SysManageDAO {

	/**
	 * 添加系统
	 * @param sysInfo
	 * @return
	 * @throws SQLException
	 */
	int addSystem(Map<String, Object> sysInfo) throws SQLException; 
	
	/**
	 * 删除系统
	 * @param sysID
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	int delSystem(String sysID) throws SQLException, ClassNotFoundException, IOException;
	
	/**
	 * 修改系统
	 * @param sysInfo
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	int updateSystem(Map<String, Object> sysInfo, String sysID) throws SQLException;
	
	/**
	 * 查询系统
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getSysList() throws SQLException;
	
	/**
	 * 获取系统总条数
	 * @return
	 * @throws SQLException
	 */
	int getSysCount() throws SQLException;
	
	/**
	 * 分页查询系统列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getSysListByPage(int pageNo, int pageSize) throws SQLException;
	
	/**
	 * 根据关键字查询系统列表
	 */
	List<Map<String, Object>> getSysListByKeyWord(int pageNo, int pageSize, String keyWord) throws SQLException;
	
	/**
	 * 关键字查询系统总数
	 */
	int countSysByKeyWord(String keyWord) throws SQLException;
	
	/**
	 * 根据ID获取系统信息
	 * @param sysID
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> getSysInfoByID(String sysID) throws SQLException;
}
