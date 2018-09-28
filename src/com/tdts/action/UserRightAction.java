package com.tdts.action;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.tdts.service.UserRightService;
import com.tdts.service.impl.UserRightServiceImpl;

public class UserRightAction extends BaseAction {

	private UserRightService userRight = new UserRightServiceImpl();
	private String username;
	private String password;
	private String jsonStr;
	
	private String menuID;
	
	/**
	 * 用户登录
	 */
	public String userLogin(){
		System.out.println("用户登录。。。");
		List<Map<String, Object>> userList = userRight.getUserInfo(username);
		if(userList.size() <= 0){
			jsonStr = "{\"result\": 1, \"msg\": \"该用户不存在！\"}";
		} else {
			jsonStr = "{\"result\": 1, \"msg\": \"密码输入有误！\"}";
			OUT:	
				for(Map<String, Object> user: userList){
					String pw = user.get("USERPW").toString().trim();
					if(password.equals(pw)){
						jsonStr = "{\"result\": 0, \"msg\": \"登录成功！\"}";
						break OUT;       //跳出for循环
					}
				}
		}
		System.out.println(jsonStr);
		return SUCCESS;
	}
	
	/**
	 * 获取菜单
	 */
	public String getMenuInfo(){
		System.out.println("获取菜单。。。");
		String userID = "001";
		List<Map<String, Object>> menuList = userRight.getMenuInfo(userID);
		jsonStr = JSON.toJSONString(menuList);
		return SUCCESS;
	}
	
	/**
	 * 获取功能权限
	 * @param args
	 */
	public String getBtnRight(){
		System.out.println("获取功能权限。。。");
		String userID = "001";
		List<Map<String, Object>> btnRightList = userRight.getBtnRight(userID);
		jsonStr = JSON.toJSONString(btnRightList);
		return SUCCESS;
	}
	
	/**
	 * 获取菜单下功能权限
	 * @param args
	 */
	public String getBtnRightOfMenu(){
		System.out.println("getBtnRightOfMenu......");
		String userID = "001";
		List<Map<String, Object>> btnRightList = userRight.getBtnRightOfMenu(userID, userID);
		jsonStr = JSON.toJSONString(btnRightList);
		return SUCCESS;
	}
	
	/**
	 * 获取权限树
	 * @param args
	 */
	public String getRightTree(){
		System.out.println("获取树。。。");
		List<Map<String, Object>> rightList = userRight.getRightTree();
		jsonStr = JSON.toJSONString(rightList);
		return SUCCESS;
	}
	
	/**
	 * 根据用户ID获取权限
	 * @param args
	 */
	public String getRightTreeByUserID(){
		System.out.println("获取用户权限。。。");
		String userID = "001";
		List<Map<String, Object>> rightList = userRight.getRightTreeByUserID(userID);
		jsonStr = JSON.toJSONString(rightList);
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		UserRightService userRight = new UserRightServiceImpl();
		/*//		List<Map<String, Object>> menuList = userRight.getMenuInfo("001");  //获取菜单
//		List<Map<String, Object>> menuList = userRight.getAllRightByUserID("001");    //获取菜单及按钮权限
//		List<Map<String, Object>> menuList = userRight.getBtnRight("001");   //获取用户功能权限
		List<Map<String, Object>> menuList = userRight.getBtnRightOfMenu("001", "MINFO00000000000A000");   //获取菜单下功能权限
		boolean flag = userRight.checkUserRight("001", "MINFO00000000000A003");     //验证权限
		System.out.println(flag);
		System.out.println(menuList);*/
		
		List<Map<String, Object>> rightList = userRight.getMenuInfo("001");
		System.out.println(rightList);
		
		
		
		
		
		
		
		
		
	}
	/***********getter/setter**********/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}
}
