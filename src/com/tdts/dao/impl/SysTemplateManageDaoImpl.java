package com.tdts.dao.impl;

import com.tdts.dao.SysTemplateManageDao;
import com.tdts.util.ApiConstants;
import com.tdts.util.StrUtil;
import core.db.DBConnection;
import core.db.DBUtility;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static com.tdts.action.test.generateShortUuid;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-15 11:39
 **/

public class SysTemplateManageDaoImpl extends BasicDaoImpl implements SysTemplateManageDao {
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

    @Override
    public List<Map<String, Object>> findDocFrame(String tableName) {
        String sql = "SELECT * FROM DOCFRAME WHERE TABLENAME = '" + tableName + "'";
        List<Map<String, Object>> list = DBUtility.execSQL(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> findDocFieldByFieldName(String tableName, Map<String, Object> params) {
        String fieldName = StrUtil.toString(params.get("fieldName"));
        String sql = "SELECT * FROM DOCFRAME WHERE TABLENAME = '" + tableName + "' AND fieldName = '" + fieldName + "'";
        System.err.println(sql);
        return DBUtility.execSQL(sql);
    }

    @Override
    public List<Map<String, Object>> findDocFieldByFrameId(String tableName, Map<String, Object> params) {
        String enFieldName = StrUtil.toString(params.get("en_fieldName"));
        String fieldName = StrUtil.toString(params.get("fieldName"));
        String frameId = StrUtil.toString(params.get("frameId"));

        //String sql = "SELECT * FROM DOCFRAME WHERE TABLENAME = '" + tableName + "' AND eN_FIELDNAME = '" + enFieldName + "' AND FIELDNAME = '" + fieldName + "'";
        String sql = "SELECT * FROM DOCFRAME WHERE TABLENAME = '" + tableName + "' AND FRAMEID = '" + frameId + "'";
        System.err.println(sql);
        return DBUtility.execSQL(sql);
    }

    @Override
    public List<Map<String, Object>> findDocFieldByFieldName(String tableName, Map<String, Object> params, int pageSize, int currentPage, String sort, String sortType) {
        int startSize = 0;
        int endSize = 0;
        if ((currentPage - 1) * pageSize >= 0) {
            startSize = (currentPage - 1) * pageSize;
        }
        endSize += startSize + pageSize;
        String orderStr = " ";
        if (!"".equals(toString(sort))) {
            orderStr = orderStr + " order by " + sort;
            if (!"".equals(toString(sortType))) {
                orderStr = orderStr + " " + sortType;
            }
        }
        System.err.println("startSize = " + startSize + "---" + "endSize = " + endSize);
        System.err.println(orderStr);
        String sql = "SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY FIELDNAME DESC) AS n from DocFrame WHERE TABLENAME =  '" + tableName + "') as doc" +
                " WHERE  doc.n BETWEEN " + startSize + "  and " + endSize + orderStr;
        String sql2 = "SELECT top " + pageSize + " * FROM (SELECT *,row_number() OVER (ORDER BY FIELDNAME DESC )n FROM DocFrame WHERE TABLENAME =  '" + tableName + "') " +
                "AS doc WHERE doc.n>" + startSize + orderStr;
        System.err.println(sql);
        return DBUtility.execSQL(sql);
    }

    @Override
    public int totalRows(String tableName, Map<String, Object> params) {
        String sql = "select COUNT(*) AS num FROM DocFrame WHERE TABLENAME = '" + tableName + "'";
        List<Map<String, Object>> list = DBUtility.execSQL(sql);
        if (list != null) {
            int num = Integer.parseInt(list.get(0).get("num").toString());
            return num;
        }
        return 0;
    }


    @Override
    public int updateDocField(Connection conn, String tableName, Map<String, Object> params, String frameId) {

        String sql = "UPDATE DocFrame SET ";
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        String sqlStr = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = toString(params.get(key));
            sqlStr += key + " = '" + value + "' , ";
        }
        sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
        sql += sqlStr + "where FRAMEID = '" + frameId + "'";
        System.err.println(sql);
        return DBUtility.execSQLWithTrans(conn, sql);
    }

    @Override
    public int deleteDocField(Connection conn, String tableName, Map<String, Object> params, String frameId) {
        String sql = "DELETE FROM DocFrame WHERE FRAMEID = '" + frameId + "'";

        return 0;
    }

    /**
     * 一条的
     *
     * @param conn
     * @param tableName
     * @return
     */
    @Override
    public int saveDocField(Connection conn, String tableName, Map<String, Object> params) {
        String FIELDNAME = toString(params.get("FIELDNAME"));
        String FIELDTYPE = toString(params.get("FIELDTYPE"));
        String FIELDSIZE = toString(params.get("FIELDSIZE"));
        String CN_FIELDNAME = toString(params.get("CN_FIELDNAME"));
        String USED = toString(params.get("USED"));
        String ISFILL = toString(params.get("ISFILL"));
        String ISSECRET = toString(params.get("ISSECRET"));
        String ISREPEAT = toString(params.get("ISREPEAT"));
        String FIELDSTATE = toString(params.get("FIELDSTATE"));
        String sql = "INSERT INTO DocFrame(FRAMEID,TABLENAME,FIELDNAME,FIELDTYPE,FIELDSIZE,CN_FIELDNAME,USED,ISFILL,ISSECRET,ISREPEAT,FIELDSTATE) VALUES('" + StrUtil.getUUID() + "','" + tableName + "','" + FIELDNAME + "','" + FIELDTYPE + "','" + FIELDSIZE + "','" + CN_FIELDNAME + "','" + USED + "','" + ISFILL + "','','" + ISSECRET + "','" + ISREPEAT + "','" + FIELDSTATE + "')";
        return DBUtility.execSQLWithTrans(conn, sql);
    }

    /**
     * 多条的
     */
    @Override
    public int saveAllDocField(Connection conn, String tableName, List<String> values, List<Map<String, Object>> list) {
        String sql = "INSERT INTO DocFrame(FRAMEID,TABLENAME,FIELDNAME,FIELDTYPE,FIELDSIZE,CN_FIELDNAME,USED,ISFILL,ISSECRET,ISREPEAT,FIELDSTATE) " +
                " VALUES(dbo.fun_getUUID32(NEWID()),'" + tableName + "',?,?,?,?,?,?,?,?,?)";
        try {

            return DBUtility.execSQLWithTrans(conn, sql, values, list);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 1;
    }
   /* public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        SysTemplateManageDao t = new SysTemplateManageDaoImpl();
        //int fd = t.totalRows("fd", null);
        String jsonStr = "{\"cn_fieldName\":\"中文名1\",\"fieldName\":\"1\",\"fieldnum\":\"12\",\"type-name\":\"i\",\"shemi\":\"0\",\"not-empty\":\"0\"}";
        //JSONObject j = JSONObject.fromObject(jsonStr);
        JSONArray j = JSONArray.fromObject(jsonStr);
        //List<Map<String, Object>> docFrame = t.findDocFieldByFieldName("doc123", j);
        //List<Map<String, Object>> docFrame = t.findDocFrame("doc123");
        //List<Map<String, Object>> docFrame = t.findDocFieldByFieldName("doc123", j, 5, 1, "fieldname", "desc");
        Connection conn = DBUtility.getConnection();

        //int documentXXX = t.BasicFieldToDocFrame(conn, "documentXXX", "0");
        List<String> values = new ArrayList<String>();
        values.add("");

        t.saveAllDocField(conn, "docXXXdd", values, j);

        //System.err.println("docFrame:"+docFrame);
    }*/

    @Override
    public int BasicFieldToDocFrame(Connection conn, String tableName, String templateId) {
        String sql = "INSERT INTO DocFrame (TABLENAME, FIELDNAME, FIELDTYPE, FIELDSIZE, CN_FIELDNAME\n" +
                ", USED, ISSECRET, ISREPEAT, FIELDSTATE, FRAMEID)\n" +
                "SELECT '" + tableName + "' AS TABLENAME, FIELDNAME, FIELDTYPE, FIELDSIZE, CN_FIELDNAME\n" +
                ", USED, ISSECRET, ISREPEAT, FIELDSTATE\n" +
                ", dbo.fun_getUUID32(NEWID()) FROM BasicField WHERE TemplateId = '" + templateId + "'";
        System.err.println(sql);
        return DBUtility.execSQLWithTrans(conn, sql);
    }

    /**
     * 字符串工具
     *
     * @param obj
     * @return
     */
    public String toString(Object obj) {
        return (obj == null ? "" : obj.toString());
    }
}
