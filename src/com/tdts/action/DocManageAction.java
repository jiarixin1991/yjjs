package com.tdts.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tdts.service.DocManageService;
import com.tdts.service.impl.DocManageServiceImpl;

public class DocManageAction extends BaseAction {

	DocManageService docManage = new DocManageServiceImpl();
	private String jsonStr;
	private String docTable;    //条目表名
	private String docID;     //条目ID
	private String keyWord;   //搜索关键字
	private String docIDs;
	
	
	/**
	 * 分页获取条目信息
	 * @return
	 */
	public String getDocList(){

        System.out.println("分页获取条目列表。。。");
		List<Map<String, Object>> docList = docManage.getDocByPage(docTable, page, limit);
		int countNum = docManage.getAllDocCount(docTable);
		Map<String, Object> docMap = new HashMap<String, Object>();
		docMap.put("result", docList);
		docMap.put("count", countNum);
		jsonStr = JSON.toJSONString(docMap);
		return SUCCESS;
	}
	
	/**
	 * 删除条目
	 */
	public String delDoc(){
		System.out.println("删除条目。。。");
		int res = docManage.delDoc(docTable, docID);
		if(res == 0){
			jsonStr = "{\"result\": 0, \"msg\": \"删除成功！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"删除失败！\"}";
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量删除条目
	 */
	public String batchDelDoc(){
		System.out.println("批量删除条目。。。");
		String[] docs = docIDs.split(", ");
		List<String> docIds = new ArrayList<String>(Arrays.asList(docs));   //数组转换成集合
		//System.out.println(docIds);
		int res = docManage.delDocBatch(docTable, docIds);
		if(res == 0){
			jsonStr = "{\"result\": 0, \"msg\": \"批量删除成功！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"批量删除失败！\"}";
		}
		return SUCCESS;
	}
	
	/**
	 * 搜索条目
	 */
	public String searchDoc(){
		System.out.println("搜索条目。。。");
		System.out.println("搜索关键字："+keyWord);
		List<Map<String, Object>> docList = docManage.getDocByKeyWord(docTable, keyWord, page, limit);
		int countNum = docManage.countDocByKeyWord(docTable, keyWord);
		Map<String, Object> docMap = new HashMap<String, Object>();
		docMap.put("result", docList);
		docMap.put("count", countNum);
		jsonStr = JSON.toJSONString(docMap);
		return SUCCESS;
	}
	
	/**
	 * 修改条目
	 */
	public String updateDocInfo(){
		System.out.println("修改条目信息。。。");
		Map<String, Object> docInfo = new HashMap<String, Object>();
		
		//TODO  接收条目信息，存入map中
		
		int res = docManage.updateDoc(docTable, docInfo, docID);
		if(res == 0){
			jsonStr = "{\"result\": 0, \"msg\": \"修改成功！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"修改失败！\"}";
		}
		
		return SUCCESS;
	}
	
	
	
	
	/********getter/setter*******/
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public String getDocTable() {
		return docTable;
	}
	public void setDocTable(String docTable) {
		this.docTable = docTable;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getDocIDs() {
		return docIDs;
	}

	public void setDocIDs(String docIDs) {
		this.docIDs = docIDs;
	}
}
