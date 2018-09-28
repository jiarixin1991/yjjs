package com.tdts.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tdts.dao.SecretManageDAO;
import com.tdts.dao.impl.SecretManageDAOImpl;
import com.tdts.service.SecManageService;

public class SecManageServiceImpl implements SecManageService {

	SecretManageDAO secManage = new SecretManageDAOImpl();
	
	@Override
	/**
	 * 添加涉密信息
	 */
	public int addSecretInfo(Map<String, Object> secretInfo) {
		try {
			return secManage.addSecretInfo(secretInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 批量添加涉密信息
	 */
	public int addSecretInfoBatch (List<String> fields, List<Map<String, Object>> secrets){
		try {
			return secManage.addSecretInfoBatch(fields, secrets);
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
	 * 删除涉密信息
	 */
	public int delSecretInfo(String secID) {
		try {
			return secManage.delSecretInfo(secID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	@Override
	/**
	 * 清空涉密表信息
	 */
	public int clearSecretInfo(){
		try {
			return secManage.clearSecretInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 更新涉密信息
	 */
	public int updateSecretInfo(String secID, Map<String, Object> secInfo) {
		try {
			return secManage.updateSecretInfo(secID, secInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	/**
	 * 获取所有涉密信息
	 */
	public List<Map<String, Object>> getSecretInfo() {
		try {
			return secManage.getSecretInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获取涉密信息总数
	 */
	public int getSecretCount() {
		try {
			return secManage.getSecretCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 分页获取涉密信息
	 */
	public List<Map<String, Object>> getSecretByPage(int pageNo, int limit) {
		try {
			return secManage.getSecretByPage(pageNo, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
