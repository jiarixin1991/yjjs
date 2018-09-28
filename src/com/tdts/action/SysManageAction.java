package com.tdts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.tdts.service.SysManageService;
import com.tdts.service.impl.SysManageServiceImpl;

public class SysManageAction extends BaseAction {
	
	private SysManageService sysManage = new SysManageServiceImpl();
	private String jsonStr;
	private String sysID;
	private String keyWord;
	
	/**
	 * 获取系统列表
	 * @return
	 */
	public String getSystem() {
		System.out.println("获取系统列表。。。");
		List<Map<String, Object>> list = sysManage.getSysListByPage(page, limit);
		int countNum = sysManage.getSysCount();
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("result", list);
		resultList.put("count", countNum);
		jsonStr = JSON.toJSONString(resultList);
		System.out.println("数据："+jsonStr);
		
		return Action.SUCCESS;
	}
	
	/**
	 * 根据ID获取系统信息
	 */
	public String getSysInfoByID(){
		System.out.println("根据ID获取系统信息。。。");
		Map<String, Object> sysInfo = sysManage.getSysInfoByID(sysID);
		jsonStr = JSON.toJSONString(sysInfo);
		return Action.SUCCESS;
	}
	
	/**
	 * 修改系统信息
	 */
	public String updateSysInfo(){
		System.out.println("修改系统信息。。。");
		Map<String, Object> sysInfo = new HashMap<String, Object>();
		
		
		int res = sysManage.updateSystem(sysInfo, sysID);
		if(res == 0){
			jsonStr = "{\"result\":0,\"msg\":\"修改成功！\"}";
		} else {
			jsonStr = "{\"result\":1,\"msg\":\"修改失败！\"}";
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 根据关键字查询系统
	 */
	public String getSysByKeyWord(){
		System.out.println("关键字："+keyWord);
		List<Map<String, Object>> sysList = sysManage.getSysByKeyWord(page, limit, keyWord);
		int countNum = sysManage.countSysByKeyWord(keyWord);
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("result", sysList);
		resultList.put("count", countNum);
		jsonStr = JSON.toJSONString(resultList);
		
		return Action.SUCCESS;
	}
	

	/************setter/getter*************/
	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getSysID() {
		return sysID;
	}

	public void setSysID(String sysID) {
		this.sysID = sysID;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	

}
