package com.tdts.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 16:41
 **/
public interface BasicFieldService {

    /**
     * 保存字段 一个字段存一次
     *
     * @param params
     * @return
     */
    public int saveTempField(Map<String, Object> params);


    public int saveTempFieldList(String templateId, List<Map<String, Object>> list,
                                 Map<String, Object> map) throws SQLException, IOException, ClassNotFoundException;

    /**
     * 修改字段
     *
     * @param templateId
     * @param params
     * @return
     */
    public int updateTempField(String templateId, Map<String, Object> params);

    /**
     * 删除字段
     *
     * @param templateId
     * @return
     */
    public int deleteTempField(String templateId);

    /**
     * 删除字通过字段名+模板ID
     *
     * @param templateId
     * @param FieldName
     * @return
     */
    public int deleteTempFieldByFieldName(String templateId, String FieldName);

    /**
     * 通过模板ID 查询模板所有字段
     *
     * @param templateId
     * @return
     */

    public List<Map<String, Object>> findBasicFieldByTempId(String templateId);

    /**
     * 查询默认模板tempId = 0 初始模板
     *
     * @return
     */
    public List<Map<String, Object>> findBasicField();

    /**
     * 通过字段名+模板ID 查询当前字段信息
     *
     * @param templateId
     * @param FieldName
     * @return
     */
    public List<Map<String, Object>> findbasicFieldByFieldName(String templateId, String FieldName);


}
