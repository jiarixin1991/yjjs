package com.tdts.dao.impl;

import com.tdts.dao.DataBaseOperationDao;
import com.tdts.dao.SysTemplateManageDao;
import com.tdts.util.StrUtil;
import core.db.DBConnection;
import core.db.DBUtility;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 11:17
 **/
public class DataBaseOperationDaoImpl implements DataBaseOperationDao {
    /**
     * TODO 默认字段还没有加入 主键primary key 还没加
     *
     * @param conn
     * @param docTableName
     * @param list
     * @return
     */
    @Override
    public int createTemplate(Connection conn, String docTableName, List<Map<String, Object>> list) {
        String sql = "create table " + docTableName + "(";
        String sqlStr = "";
        for (Map<String, Object> params : list) {
            //boolean b = params.containsValue("cn-fieldname");

            //String cnFieldName = StrUtil.toString(params.get("cn-fieldname"));
            String cnFieldName = "name" + StrUtil.generateShortUuid();
            String fileType = StrUtil.toString(params.get("type-name"));
            String notEmpty = StrUtil.toString(params.get("not-empty"));

            if (cnFieldName != "" && cnFieldName != null && cnFieldName != "null") {
                sqlStr += cnFieldName + " ";
            } else {
                return 1;
            }
            if ("v".equals(fileType)) {
                int fieldnum = Integer.parseInt((String) params.get("fieldnum"));
                sqlStr += " varchar" + "(" + fieldnum + ")";
            } else if ("i".equals(fileType)) {
                sqlStr += " int" + " ";

            } else if ("d".equals(fileType)) {
                sqlStr += " char(8) " + " ";
            } else {
                return 1;
            }
            if ("0".equals(notEmpty)) {
                sqlStr += " not null , ";
            } else {
                sqlStr += " , ";
            }
            //StrUtil.syse(sqlStr);
        }
        int index = sqlStr.lastIndexOf(",");
        sqlStr = sqlStr.substring(0, index - 1);
        //StrUtil.syse(sqlStr);
        sql += sqlStr;
        sql += ")";
        StrUtil.syse(sql);

        return DBUtility.execSQLWithTrans(conn, sql);
    }

    /*public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String jsonStr = "[{\"cn-fieldname\":\"name\",\"en-fieldname\":\"功率为各位\",\"fieldnum\":\"12\",\"type-name\":\"i\",\"shemi\":\"0\",\"fileType\":\"0\"},{\"cn-fieldname\":\"name2\",\"en-fieldname\":\"却纷纷去\",\"fieldnum\":\"6556\",\"type-name\":\"v\",\"shemi\":\"1\",\"fileType\":\"300\"},{\"cn-fieldname\":\"name3\",\"en-fieldname\":\"3123\",\"fieldnum\":\"\",\"type-name\":\"d\",\"shemi\":\"0\",\"fileType\":\"0\"}]";
        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        System.out.println(jsonArray);
        TemplateManageDao t = new TemplateManageDaoImpl();
        Connection conn = DBUtility.getConnection();
        //List<?> list = jsonArray.toList(jsonArray);
        //System.err.println(list);
        //conn.setAutoCommit(false);
        int num = t.createTemplate(conn, "admin", jsonArray);
        //conn.rollback();
        System.out.println("num:" + num);

    }*/

    @Override
    public int createField(Connection conn, String docTableName, Map<String, Object> params) {
        String sql = "  alter table " + docTableName + " add ";
        String sqlStr = "";
        //String cnFieldName = StrUtil.toString(params.get("en-fieldname"));
        String enFieldName = "name" + StrUtil.generateShortUuid();
        String fileType = StrUtil.toString(params.get("type-name"));
        String notEmpty = StrUtil.toString(params.get("not-empty"));

        if (enFieldName != "" && enFieldName != null && enFieldName != "null") {
            sqlStr += enFieldName + " ";
        } else {
            return 1;
        }
        if ("v".equals(fileType)) {
            int fieldnum = Integer.parseInt((String) params.get("fieldnum"));
            sqlStr += " varchar" + "(" + fieldnum + ")";
        } else if ("i".equals(fileType)) {
            sqlStr += " int" + " ";

        } else if ("d".equals(fileType)) {
            sqlStr += " char(8) " + " ";
        } else {
            return 1;
        }
        if ("0".equals(notEmpty)) {
            sqlStr += " not null ";
        } else {
            sqlStr += " ";
        }
        sql += sqlStr;
        System.err.println(sql);
        int num = DBUtility.execSQLWithTrans(conn, sql);
        return num;
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String jsonStr = "{\"cn-fieldname\":\"namer2igaCL3\",\"en-fieldname\":\"name1\",\"fieldnum\":\"12\",\"type-name\":\"i\",\"shemi\":\"0\",\"not-empty\":\"0\"}";
        JSONObject j = JSONObject.fromObject(jsonStr);
        SysTemplateManageDao t = new SysTemplateManageDaoImpl();
        Connection conn = DBConnection.getConnection();
        //List<?> list = jsonArray.toList(jsonArray);
        //System.err.println(list);
        //conn.setAutoCommit(false);
        //int num = t.createField(conn, "admin", j);
        //int num = t.updateField(conn, "admin", j);
        int num = t.deleteField(conn, "vwf", j);
        //conn.rollback();

        System.out.println("num:" + num);


    }

    @Override
    public int updateField(Connection conn, String docTableName, Map<String, Object> params) {
        String sql = "alter table " + docTableName + " alter column  ";
        String enFieldName = StrUtil.toString(params.get("en-fieldname"));
        String fileType = StrUtil.toString(params.get("type-name"));
        String notEmpty = StrUtil.toString(params.get("not-empty"));
        sql += enFieldName;
        if ("v".equals(fileType)) {
            int fieldnum = Integer.parseInt((String) params.get("fieldnum"));
            sql += " varchar" + "(" + fieldnum + ")";
        } else if ("i".equals(fileType)) {
            sql += " int" + " ";

        } else if ("d".equals(fileType)) {
            sql += " char(8) " + " ";
        } else {
            return 1;
        }
        if ("0".equals(notEmpty)) {
            sql += " not null ";
        } else {
            sql += " ";
        }
        System.err.println(sql);
        return DBUtility.execSQLWithTrans(conn, sql);
    }

    @Override
    public int deleteField(Connection conn, String docTableName, Map<String, Object> params) {
        String sql = "alter table " + docTableName + " drop column ";
        String enFieldName = StrUtil.toString(params.get("en-fieldname"));
        sql += enFieldName;
        System.err.println(sql);
        return DBUtility.execSQLWithTrans(conn, sql);
    }
}
