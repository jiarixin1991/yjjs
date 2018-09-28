package com.tdts.service;

import java.util.List;
import java.util.Map;

public interface SysManageService {

	/**
	 * 添加系统
	 * @param sysInfo
	 * @return
	 */
	int addSystem(Map<String, Object> sysInfo); 
	
	/**
	 * 删除系统
	 * @param sysID
	 * @return
	 */
	int delSystem(String sysID);
	
	/**
	 * 修改系统
	 * @param sysInfo
	 * @param sysID
	 * @return
	 */
	int updateSystem(Map<String, Object> sysInfo, String sysID);
	
	/**
	 * 查询系统
	 * @return
	 */
	List<Map<String, Object>> getSysList();
	
	/**
	 * 获取系统总条数
	 * @return
	 */
	int getSysCount();
	
	/**
	 * 分页查询系统列表
	 * @param pageNo
	 * @param limit
	 * @return
	 */
	List<Map<String, Object>> getSysListByPage(int page, int limit);
	
	/**
	 * 根据ID获取系统信息
	 */
	Map<String, Object> getSysInfoByID(String sysID);
	
	/**
	 * 根据关键字查询系统
	 */
	List<Map<String, Object>> getSysByKeyWord(int page, int limit, String keyWord);
	
	/**
	 * 根据关键字查询系统总数
	 */
	int countSysByKeyWord(String keyWord);
}
