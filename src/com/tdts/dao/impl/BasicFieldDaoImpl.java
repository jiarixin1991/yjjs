package com.tdts.dao.impl;

import com.tdts.dao.BasicFieldDao;
import core.db.DBUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-15 11:39
 **/
public class BasicFieldDaoImpl implements BasicFieldDao {
    @Override
    public int saveTempField(Map<String, Object> params) {
        return 0;

    }

    @Override
    public int saveTempFieldList(Connection conn, String templateId, List<String> params, List<Map<String, Object>> list) {

        String sql = "INSERT INTO BasicField(BASICID,TEMPLATEID,FIELDNAME,FIELDTYPE,FIELDSIZE,CN_FIELDNAME,USED,ISFILL,ISSECRET,ISREPEAT,FIELDSTATE,REMARKS) " +
                " VALUES(dbo.fun_getUUID32(NEWID()),'" + templateId + "',?,?,?,?,?,?,?,?,?,?)";
        /*List<String> params = new ArrayList<>();
        params.add("FIELDNAME");
        params.add("FIELDTYPE");
        params.add("FIELDSIZE");
        params.add("CN_FIELDNAME");
        params.add("USED");
        params.add("ISFILL");
        params.add("ISSECRET");
        params.add("ISREPEAT");
        params.add("FIELDSTATE");
        params.add("REMARKS");*/
        try {
            DBUtility.execSQLWithTrans(conn, sql, params, list);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    @Override
    public int updateTempField(String templateId, Map<String, Object> params) {
        return 0;
    }

    @Override
    public int deleteTempField(String templateId) {
        return 0;
    }

    @Override
    public int deleteTempFieldByFieldName(String templateId, String FieldName) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> findBasicFieldByTempId(String templateId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findBasicField() {
        String sql = "SELECT * FROM BasicField WHERE TemplateId = '0' ";
        return DBUtility.execSQL(sql);
    }

    @Override
    public List<Map<String, Object>> findbasicFieldByFieldName(String templateId, String FieldName) {
        return null;
    }
}
