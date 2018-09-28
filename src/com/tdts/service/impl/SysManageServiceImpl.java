package com.tdts.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.SysManageDAO;
import com.tdts.dao.impl.SysManageDAOImpl;
import com.tdts.service.SysManageService;

public class SysManageServiceImpl implements SysManageService {

	SysManageDAO sysManage = new SysManageDAOImpl();
	
	@Override
	/**
	 * 添加系统
	 */
	public int addSystem(Map<String, Object> sysInfo) {
		try {
			return sysManage.addSystem(sysInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 删除系统
	 */
	public int delSystem(String sysID) {
		try {
			return sysManage.delSystem(sysID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 更新系统
	 */
	public int updateSystem(Map<String, Object> sysInfo, String sysID) {
		try {
			return sysManage.updateSystem(sysInfo, sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 获取所有系统信息
	 */
	public List<Map<String, Object>> getSysList() {
		try {
			return sysManage.getSysList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获取系统总数
	 */
	public int getSysCount() {
		try {
			return sysManage.getSysCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 分页获取系统信息
	 */
	public List<Map<String, Object>> getSysListByPage(int page, int limit) {
		try {
			return sysManage.getSysListByPage(page, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据ID获取系统信息
	public Map<String, Object> getSysInfoByID(String sysID) {
		try {
			return sysManage.getSysInfoByID(sysID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字获取系统列表
	public List<Map<String, Object>> getSysByKeyWord(int page, int limit,
			String keyWord) {
		try {
			return sysManage.getSysListByKeyWord(page, limit, keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	//根据关键字查询系统总数
	public int countSysByKeyWord(String keyWord) {
		try {
			return sysManage.countSysByKeyWord(keyWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
