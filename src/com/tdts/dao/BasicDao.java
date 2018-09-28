package com.tdts.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BasicDao {
	/**
	 * 带事务conn 增
	 */
	public int save_table(Connection conn, String tableName, Map<String, Object> tableMap);


	/**
	 * 保存信息
	 * @param tableName			表名
	 * @param archivesbureauMap 参数（map<数据库字段,值>）
	 */
	public int saveTable(String tableName, Map<String, Object> tableMap) throws SQLException;
	
	/**
	 * 删除信息
	 * @param tableName		表名
	 * @param params		参数 	map<数据库字段,值>
	 */
	public int deleteTable(String tableName, Map<String, Object> params) throws SQLException;
	
	/**
	 * 删除信息
	 * @param tableName		表名
	 * @param params		拼接好的条件语句
	 */
	public int deleteTable(String tableName, String params) throws SQLException;
	
	/**
	 * 修改信息
	 * @param tableName			表名
	 * @param archivesbureauMap 参数（map<要修改数据库字段,值>）
	 */
	public int updateTable(String tableName, Map<String, Object> tableMap, String param) throws SQLException;
	
	public int update_table(Connection conn, String tableName, Map<String, Object> tableMap,
                            String tableIDStr) throws SQLException;
	
	/**
	 * 根据ID查询信息
	 * @param tableName        表名
	 */
	public List<Map<String, Object>> queryById(String tableName, String tableId) throws SQLException;
	
	/**
	 * 查询全部信息（不分页）
	 * @param tableName		表名
	 * @param sort			排序字段
	 * @param sortType		排序类型（asc正序    desc倒序）
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryAll(String tableName, String sort, String sortType) throws SQLException;
	
	/**
	 * 根据传入值查询信息（不分页）
	 * @param tableName		表名
	 * @param params		参数 	map<数据库字段,值>
	 * @param sort			排序字段
	 * @param sortType		排序类型（asc正序    desc倒序）
	 */
	public List<Map<String, Object>> queryAll(String tableName, Map<String, Object> params, String sort, String sortType) throws SQLException;
	
	/**
	 * 查询全部信息（分页）
	 * @param tableName		表名
	 * @param pageSize		每页条数
	 * @param currentPage	当前页数
	 * @param sort			排序字段
	 * @param sortType		排序类型（asc正序    desc倒序）
	 * @return
	 */
	public List<Map<String, Object>> queryAllTable(String tableName, int pageSize, int currentPage, String sort, String sortType, String tableID) throws SQLException;
	
	/**
	 * 根据传入值查询信息（分页）
	 * @param tableName		表名
	 * @param pageSize		每页条数
	 * @param currentPage	当前页数
	 * @param params		参数 	map<数据库字段,值>
	 * @param sort			排序字段
	 * @param sortType		排序类型（asc正序    desc倒序）
	 * @return
	 */
	public List<Map<String, Object>> queryAllTable(String tableName, int pageSize, int currentPage, Map<String, Object> params, String sort, String sortType, String tableID) throws SQLException;
	
	/**
	 * 
	 * @param tableName		表名
	 * @param params		参数 	map<数据库字段,值>
	 * @return
	 * @throws SQLException
	 */
	public int getCount(String tableName, Map<String, Object> params) throws SQLException;

}
