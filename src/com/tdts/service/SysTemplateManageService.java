package com.tdts.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-20 10:05
 **/
public interface SysTemplateManageService {
    /**
     * 创建系统模板
     * @param conn
     * @param docTableName 创建的表名
     * @param templateId 模板的名称
     * @param list  数据集合
     * @return
     */
    public int CreateTemplate(Connection conn, String docTableName,String templateId, List<Map<String, Object>> list);

    /**
     * 新增系统模板字段
     * @param conn
     * @param docTableName 表名
     * @param params 数据
     * @return
     */
    public int newCreateField(Connection conn, String docTableName,Map<String , Object> params);

    /**
     * 修改系统模板
     */
    public int updateField(Connection conn, String docTableName,String frameId, Map<String, Object> params);
    /**
     * 删除系统模板
     */
    public int deleteField(Connection conn, String docTableName, String frameId, Map<String, Object> params);
    /**
     * 查看对应系统的所有字段
     *
     * @param tableName 对应的是哪个系统下的字段表名
     * @return 字段集合
     */
    public List<Map<String, Object>> findDocFrame(String tableName);


}
