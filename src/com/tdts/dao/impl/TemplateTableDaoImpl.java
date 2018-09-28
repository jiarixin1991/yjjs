package com.tdts.dao.impl;

import com.tdts.dao.TemplateTableDao;
import com.tdts.util.ApiConstants;
import com.tdts.util.StrUtil;
import core.db.DBConnection;
import core.db.DBUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-15 11:39
 **/
public class TemplateTableDaoImpl extends DbBasicDaoImpl implements TemplateTableDao {

    @Override
    public int saveTemp(Map<String, Object> param) {
        //DBUtility

        try {
            return this.saveTable(ApiConstants.TEMPLATETABLE, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;

    }

    @Override
    public int updateTempByTempId(String tempId, Map<String, Object> params) {
        String tableStr = "tempId = " + tempId;

        try {
            this.updateTable(ApiConstants.TEMPLATETABLE, params, tableStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int deleteTemp(Connection conn, String tempId) {
        String sql = "DELETE FROM TemplateTable WHERE TempId = " + tempId;
        //String sql = "DROP  TABLE Students";
        //System.err.println("sql:["+sql+"]");
        StrUtil.syse(sql);
        return DBUtility.execSQLWithTrans(conn, sql);

    }

    @Override
    public List<Map<String, Object>> findAllTemp() {
        try {
            List<Map<String, Object>> list = this.queryAll(ApiConstants.TEMPLATETABLE, "", "");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> findTempByTempId(String tempId) {
        String sql = "SELECT * FROM TEMPLATETABLE WHERE tempId = " + tempId;
        List<Map<String, Object>> list = DBUtility.execSQL(sql);
        return list;
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        TemplateTableDao t = new TemplateTableDaoImpl();

        Connection conn = null;
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        try {
            t.deleteTemp(conn, "3");

            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }


    }
}
