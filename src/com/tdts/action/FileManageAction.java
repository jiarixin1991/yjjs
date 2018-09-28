package com.tdts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.tdts.service.FilesManageService;
import com.tdts.service.impl.FilesManageServiceImpl;

public class FileManageAction extends BaseAction {

	private FilesManageService fileManage = new FilesManageServiceImpl();
	private String sysID;    //系统ID
	private String docTableName;  //条目表名
	private String docID;   //条目ID
	private String jsonStr;
	private String fileID;
	private String keyWord;
	/***
	 * 获取所有文件
	 * @return
	 */
	public String getFiles(){
		System.out.println("获取系统内所有原件。。。");
		getResponse().setCharacterEncoding("utf-8");       
		getResponse().setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = getResponse().getWriter();
			List<Map<String, Object>> fileList = fileManage.getFilesList(sysID);
			String jsonStr = JSON.toJSONString(fileList);
			System.out.println(jsonStr);
			out.print(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	/***
	 * 获取已挂接文件
	 * @return
	 */
	public String getListHasHanged(){
		System.out.println("获取已经挂接的原件列表。。。");
		List<Map<String, Object>> fileList = fileManage.getListHasHanged(sysID, docID);
		int countNum = fileManage.countFileHasHanged(sysID, docID);
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("result", fileList);
		resultList.put("count", countNum);
		jsonStr = JSON.toJSONString(resultList);
		
		return Action.SUCCESS;
	}
	
	/**
	 * 获取未挂接文件
	 */
	public String getFileNotHanged(){
		System.out.println("获取未挂接文件。。。");
		System.out.println("关键字："+keyWord);
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		int countNum = 0;
		if(keyWord == null || "".equals(keyWord)){   //查询所有未挂接文件
			fileList = fileManage.pageFileNotHanged(sysID, page, limit);
			countNum = fileManage.countFileNotHanged(sysID);
		
		} else {      //搜索未挂接文件
			fileList = fileManage.listFileByKeyWord(sysID, page, limit, keyWord);
			countNum = fileManage.countFileByKeyWord(sysID, keyWord);

		}
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("result", fileList);
		resultList.put("count", countNum);
		jsonStr = JSON.toJSONString(resultList);
		System.out.println("未挂接数据："+jsonStr);
		return Action.SUCCESS;
	}

	/**
	 * 根据文件ID获取文件內容
	 */
	public String getFileInfoByID(){
		System.out.println("根据文件ID获取文件內容。。。");
		System.out.println(fileID);
		Map<String, Object> fileInfo = fileManage.getFileInfoByID(fileID);
		jsonStr = JSON.toJSONString(fileInfo);
		return Action.SUCCESS;
	}
	
	/**
	 * 取消文件挂接
	 * @return
	 */
	public String cancleHanged(){
		System.out.println("取消文件挂接。。。");
		int res = fileManage.cancleHangUp(fileID);
		//int res = 1;
		if(res == 0){
			jsonStr = "{\"result\":0, \"msg\":\"取消挂接成功!\"}";
		} else {
			jsonStr = "{\"result\":1, \"msg\":\"取消挂接失败！\"}";
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 搜索原件
	 */
	public String searchFile(){
		System.out.println("根据"+keyWord+"搜索原件。。。");
		List<Map<String, Object>> fileList = fileManage.getAllFileByKeyWord(keyWord, sysID, page, limit);
		int countNum = fileManage.countAllFileByKeyWord(keyWord, sysID);
		Map<String, Object> resultList = new HashMap<String, Object>();
		resultList.put("result", fileList);
		resultList.put("count", countNum);
		jsonStr = JSON.toJSONString(resultList);
		
		return SUCCESS;
	}

/************getter/setter***********/
	public String getSysID() {
		return sysID;
	}

	public void setSysID(String sysID) {
		this.sysID = sysID;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public String getDocTableName() {
		return docTableName;
	}

	public void setDocTableName(String docTableName) {
		this.docTableName = docTableName;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
