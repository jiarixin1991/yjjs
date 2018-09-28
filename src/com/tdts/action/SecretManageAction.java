package com.tdts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.tdts.service.SecManageService;
import com.tdts.service.impl.SecManageServiceImpl;
import com.tdts.util.StrUtil;


public class SecretManageAction extends BaseAction {

	/**涉密关键字管理**/
	SecManageService secManage = new SecManageServiceImpl();
	private String jsonStr; 
	private String secretWord;
	
	/**
	 * 获取关键字集合
	 * @return
	 */
	public String getSecretList(){
		System.out.println("获取涉密关键字。。。");
		List<Map<String, Object>> secList = secManage.getSecretByPage(page, limit);
		int countNum = secManage.getSecretCount();
		Map<String, Object> secMap = new HashMap<String, Object>();
		secMap.put("result", secList);
		secMap.put("count", countNum);
		jsonStr = JSON.toJSONString(secMap);
		
		return Action.SUCCESS;
	}
	
	/**
	 * 手动批量添加涉密关键字         (前台输入框添加关键字，先用集合/用固定字符隔开的字符串保存，集合传到后台，批量添加)
	 */
	public String addSecretWordList(){
		System.out.println("批量添加涉密关键字。。。");
		List<String> fieldList = new ArrayList<String>();     //字段集合
		List<Map<String, Object>> secList = new ArrayList<Map<String,Object>>();       //涉密关键字集合
		fieldList.add("SecId");
		fieldList.add("SECRETNAME");
		
		String uuid = StrUtil.getUUID();
		Map<String, Object> secMap = new HashMap<String, Object>();
		secMap.put("SecId", uuid);
		secMap.put("SECRETNAME", "");
		secList.add(secMap);
		
		int res = secManage.addSecretInfoBatch(fieldList, secList);
		if(res == 0){
			jsonStr = "{\"result\": 0, \"msg\": \"添加成功！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"添加失败！\"}";
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 添加单个涉密关键字
	 */
	public String addSecretWord(){
		System.out.println("添加单个涉密关键字。。。");
		Map<String, Object> secMap = new HashMap<String, Object>();
		String uuid = StrUtil.getUUID();
		secMap.put("SecId", uuid);
		secMap.put("SECRETNAME", secretWord);
		int res = secManage.addSecretInfo(secMap);
		if(res == 0){
			jsonStr = "{\"result\": 0, \"msg\": \"添加成功！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"添加失败！\"}";
		}
		return Action.SUCCESS;
	}
	
	/*******getter/setter********/
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getSecretWord() {
		return secretWord;
	}

	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}
	
}
