package com.tdts.service;

import java.util.List;
import java.util.Map;

public interface SecManageService {

	//添加涉密内容
	int addSecretInfo(Map<String, Object> secretInfo);
	
	//批量添加涉密内容
	int addSecretInfoBatch (List<String> fields, List<Map<String, Object>> secrets);
	
	//删除涉密内容
	int delSecretInfo(String secID);
	
	//清空涉密表信息
	int clearSecretInfo();
	
	//修改涉密信息
	int updateSecretInfo(String secID, Map<String, Object> secInfo);
	
	//查询涉密信息
	List<Map<String, Object>> getSecretInfo();
	
	//获取涉密信息总条数
	int getSecretCount();
	
	//分页查询涉密信息
	List<Map<String, Object>> getSecretByPage(int pageNo, int pageSize);
}
