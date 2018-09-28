package com.tdts.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 11:15
 * 数据库操作（创建表，修改表，删除表）
 **/
public interface DataBaseOperationDao {

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
    public int createField(Connection conn, String docTableName, Map<String, Object> params);

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
     * TODO 整个表删除暂时不写（后续问清楚了有在写）
     */

}
