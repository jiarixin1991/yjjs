package com.tdts.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author JRX
 * 模板操作Dao
 */

public interface SysTemplateManageDao {


    /**
     * 创建条目模板
     *
     * @param conn
     * @param docTableName
     * @param list
     * @return 0.成功、1.失败
     */
    public int createTemplate(Connection conn, String docTableName, List<Map<String, Object>> list);

    /**
     * 新增模板字段
     *
     * @param docTableName
     * @param conn
     * @param params
     * @return 0.成功、1.失败
     */
    public int createField(Connection conn, String docTableName,Map<String , Object> params);

    /**
     * 修改字段信息 字段名称 字段类型 字段是否为空等
     *
     * @param docTableName
     * @param conn
     * @param params
     * @return 0.成功、1.失败
     */
    public int updateField(Connection conn, String docTableName, Map<String, Object> params);

    /**
     * 删除字段
     *
     * @param docTableName
     * @param conn
     * @param params
     * @return 0.成功、1.失败
     */
    public int deleteField(Connection conn, String docTableName, Map<String, Object> params);

    /**
     * 查看对应系统的所有字段
     *
     * @param tableName 对应的是哪个系统下的字段表名
     * @return 字段集合
     */
    public List<Map<String, Object>> findDocFrame(String tableName);

    /**
     * 查看对应字段信息通过表名 字段名查找
     *
     * @param tableName          对应的是哪个系统下的字段表名
     * @param params(FieldName等) 中文名唯一
     * @return 通过字段信息查找对应当前字段的信息
     */
    public List<Map<String, Object>> findDocFieldByFieldName(String tableName, Map<String, Object> params);

    /**
     * 查看对应字段信息通过表名 字段ID查找
     *
     * @param tableName          对应的是哪个系统下的字段表名
     * @param params(FrameId等)
     * @return 通过字段信息查找对应当前字段的信息
     */
    public List<Map<String, Object>> findDocFieldByFrameId(String tableName, Map<String, Object> params);

    /**
     * 查询分页
     *
     * @param tableName   对应的是哪个系统下的字段表名
     * @param params
     * @param pageSize
     * @param currentPage
     * @param sort
     * @param sortType
     * @return 分页数据
     */
    public List<Map<String, Object>> findDocFieldByFieldName(String tableName, Map<String, Object> params, int pageSize, int currentPage, String sort, String sortType);

    /**
     * 总条数
     *
     * @param tableName 对应的是哪个系统下的字段表名
     * @param params
     * @return 查询分页的数据总数
     */
    public int totalRows(String tableName, Map<String, Object> params);

    /**
     * 修改当前系统模板字段信息
     *
     * @param tableName
     * @param params
     * @return
     */
    public int updateDocField(Connection conn,String tableName, Map<String, Object> params,String frameId);


    /**
     * 删除当前系统模板字段信息
     *
     * @param tableName
     * @param params
     * @return
     */
    public int deleteDocField(Connection conn,String tableName, Map<String, Object> params, String frameId);

    /**
     * 新增当前系统模板字段
     *
     * @param tableName
     * @param params
     * @return
     */
    public int saveDocField(Connection conn,String tableName, Map<String, Object> params);

    /**
     * 保存所有字段
     * @param conn
     * @param tableName
     * @param list
     * @return
     */
    public int saveAllDocField(Connection conn,String tableName,List<String> values, List<Map<String, Object>> list);
    /**
     * 从模板表写入条目记录表
     *
     * @param tableName
     * @return
     */
    public int BasicFieldToDocFrame(Connection conn,String tableName,String templateId);

}
