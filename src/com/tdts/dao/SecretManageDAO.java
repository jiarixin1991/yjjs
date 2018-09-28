package com.tdts.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SecretManageDAO {

	//添加涉密内容
	int addSecretInfo(Map<String, Object> secretInfo) throws SQLException;
	
	//批量添加涉密内容
	int addSecretInfoBatch(List<String> fields, List<Map<String, Object>> secrets) throws SQLException, ClassNotFoundException, IOException;
	
	//删除涉密内容
	int delSecretInfo(String secID) throws SQLException;
	
	//清空涉密表
	int clearSecretInfo() throws SQLException;
	
	//修改涉密信息
	int updateSecretInfo(String secID, Map<String, Object> secInfo) throws SQLException;
	
	//查询涉密信息
	List<Map<String, Object>> getSecretInfo() throws SQLException;
	
	//获取涉密信息总条数
	int getSecretCount() throws SQLException;
	
	//分页查询涉密信息
	List<Map<String, Object>> getSecretByPage(int pageNo, int pageSize) throws SQLException;
}
