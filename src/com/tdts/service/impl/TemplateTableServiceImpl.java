package com.tdts.service.impl;

import com.tdts.dao.TemplateTableDao;
import com.tdts.dao.impl.TemplateTableDaoImpl;
import com.tdts.service.TemplateTableService;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 11:38
 **/
public class TemplateTableServiceImpl implements TemplateTableService {
    private TemplateTableDao ttd = new TemplateTableDaoImpl();
    @Override
    public int saveTemp(Map<String, Object> param) {

        return ttd.saveTemp(param);
    }

    @Override
    public int updateTempByTempId(String tempId, Map<String, Object> params) {

        return ttd.updateTempByTempId(tempId, params);
    }

    @Override
    public int deleteTemp(Connection conn, String tempId) {

        return ttd.deleteTemp(conn, tempId);
    }

    @Override
    public List<Map<String, Object>> findAllTemp() {

        return ttd.findAllTemp();
    }

    @Override
    public List<Map<String, Object>> findTempByTempId(String tempId) {

        return ttd.findTempByTempId(tempId);
    }
}
