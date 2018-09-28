package com.tdts.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 条目模板表操作
 *
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-15 11:09
 **/
public interface BasicFieldDao {
    /**
     * 保存字段 一个字段存一次
     * @param params
     * @return
     */
    public int saveTempField(Map<String, Object> params);



    public int saveTempFieldList(Connection conn,String templateId,List<String> params, List<Map<String, Object>> list);
    /**
     * 修改字段
     * @param templateId
     * @param params
     * @return
     */
    public int updateTempField(String templateId, Map<String, Object> params);

    /**
     * 删除字段
     * @param templateId
     * @return
     */
    public int deleteTempField(String templateId);

    /**
     * 删除字通过字段名+模板ID
     * @param templateId
     * @param FieldName
     * @return
     */
    public int deleteTempFieldByFieldName(String templateId, String FieldName);

    /**
     * 通过模板ID 查询模板所有字段
     * @param templateId
     * @return
     */

    public List<Map<String, Object>> findBasicFieldByTempId(String templateId);

    /**
     * 查询默认模板tempId = 0 初始模板
     * @return
     */
    public List<Map<String, Object>> findBasicField();

    /**
     * 通过字段名+模板ID 查询当前字段信息
     * @param templateId
     * @param FieldName
     * @return
     */
    public List<Map<String, Object>> findbasicFieldByFieldName(String templateId, String FieldName);


}
