package com.tdts.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 11:38
 **/
public interface TemplateTableService {
    /**
     * 保存模板
     *
     * @param param
     * @return
     */
    public int saveTemp(Map<String, Object> param);

    /**
     * 修改模板
     *
     * @param tempId
     * @param params
     * @return
     */
    public int updateTempByTempId(String tempId, Map<String, Object> params);

    /**
     * 删除模板
     *
     * @param tempId
     * @return
     */
    public int deleteTemp(Connection conn, String tempId);

    /**
     * 查询所有模板
     *
     * @return
     */
    public List<Map<String, Object>> findAllTemp();

    /**
     * 通过ID查对应模板
     *
     * @param tempId
     * @return
     */
    public List<Map<String, Object>> findTempByTempId(String tempId);


}
