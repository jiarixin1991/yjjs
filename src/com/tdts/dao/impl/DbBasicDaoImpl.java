package com.tdts.dao.impl;

import com.tdts.dao.DbBasicDao;
import core.db.DBUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-09-25 10:53
 **/
public class DbBasicDaoImpl implements DbBasicDao {
    @Override
    public int save_table(Connection conn, String tableName, Map<String, Object> tableMap) {
        String insertSQL = "insert into " + tableName + "(";
        // 处理参数
        Set<String> mapKeySet = tableMap.keySet();
        Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
        String keys = "";
        String values = "";
        while (it.hasNext()) {
            String key = it.next();
            keys += key.toUpperCase() + ",";
            String value = toString(tableMap.get(key));// 有了键可以通过map集合的get方法获取其对应的值。
            values += "'" + value + "',";
        }
        keys = keys.substring(0, keys.length() - 1);// 去掉最后一个，
        values = values.substring(0, values.length() - 1);
        insertSQL = insertSQL + keys + ") values(" + values + ")";
        System.out.println("insertSQL:" + insertSQL);
        return DBUtility.execSQLWithTrans(conn, insertSQL);
    }

    // 增
    @Override
    public int saveTable(String tableName, Map<String, Object> tableMap)
            throws SQLException {
        String insertSQL = "insert into " + tableName + "(";
        // 处理参数
        Set<String> mapKeySet = tableMap.keySet();
        Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
        String keys = "";
        String values = "";
        while (it.hasNext()) {
            String key = it.next();
            keys += key.toUpperCase() + ",";
            String value = toString(tableMap.get(key));// 有了键可以通过map集合的get方法获取其对应的值。
            values += "'" + value + "',";
        }
        keys = keys.substring(0, keys.length() - 1);// 去掉最后一个，
        values = values.substring(0, values.length() - 1);
        insertSQL = insertSQL + keys + ") values(" + values + ")";
        return DBUtility.execSQLWithTrans(insertSQL);

    }

    // 删（条件）
    @Override
    public int deleteTable(String tableName, Map<String, Object> params)
            throws SQLException {

        String deleteSQL = "delete from " + tableName + " where ";
        // 处理参数
        Set<String> mapKeySet = params.keySet();
        Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
        String pamas = "";
        while (it.hasNext()) {
            String key = it.next();
            String value = toString(params.get(key));
            pamas += key + "='" + value + "' and ";
        }
        pamas = pamas.substring(0, pamas.length() - 5);
        deleteSQL = deleteSQL + pamas;
        System.out.println(deleteSQL);
        return DBUtility.execSQLWithTrans(deleteSQL);

    }

    // 删（拼接好的条件语句）
    @Override
    public int deleteTable(String tableName, String params) throws SQLException {
        String deleteSQL = "delete from " + tableName + " ";
        if (params.contains("where")) {
            deleteSQL = deleteSQL + params;
        } else {
            deleteSQL = deleteSQL + " where " + params;
        }
        return DBUtility.execSQLWithTrans(deleteSQL);
    }

    // 修改 tableIDStr为拼接串（例： USERID = "123"）
    @Override
    public int updateTable(String tableName, Map<String, Object> tableMap,
                           String tableIDStr) throws SQLException {
        String updateSQL = "update " + tableName + " set ";
        // 处理参数
        Set<String> mapKeySet = tableMap.keySet();
        Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
        String pamas = "";
        while (it.hasNext()) {
            String key = it.next();
            String value = toString(tableMap.get(key));
            pamas += key + "='" + value + "',";
        }
        pamas = pamas.substring(0, pamas.length() - 1);
        updateSQL = updateSQL + pamas + " where " + tableIDStr;
        System.out.println(updateSQL);
        return DBUtility.execSQLWithTrans(updateSQL);

    }

    @Override
    public int update_table(Connection conn, String tableName, Map<String, Object> tableMap,
                            String tableIDStr) throws SQLException {
        String updateSQL = "update " + tableName + " set ";
        // 处理参数
        Set<String> mapKeySet = tableMap.keySet();
        Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
        String pamas = "";
        while (it.hasNext()) {
            String key = it.next();
            String value = toString(tableMap.get(key));
            pamas += key + "='" + value + "',";
        }
        pamas = pamas.substring(0, pamas.length() - 1);
        updateSQL = updateSQL + pamas + " where " + tableIDStr;
        System.out.println(updateSQL);
        return DBUtility.execSQLWithTrans(conn, updateSQL);

    }

    // 根据id查 tableIDStr为拼接串（例： USERID = "123"）
    @Override
    public List<Map<String, Object>> queryById(String tableName,
                                               String tableIdStr) throws SQLException {
        String querySQL = "select * from " + tableName + " where " + tableIdStr;
        return DBUtility.execSQL(querySQL);
    }

    // 查询所有（不分页）
    @Override
    public List<Map<String, Object>> queryAll(String tableName, String sort,
                                              String sortType) throws SQLException {
        String querySQL = "select * from " + tableName;
        if (!("").equals(toString(sort))) {
            querySQL = querySQL + " order by " + sort;
            if (!("").equals(toString(sortType))) {
                querySQL = querySQL + " " + sortType;
            }
        }
        return DBUtility.execSQL(querySQL);
    }

    // 查（不分页、带参数）
    @Override
    public List<Map<String, Object>> queryAll(String tableName,
                                              Map<String, Object> params, String sort, String sortType)
            throws SQLException {
        String querySQL = "select * from " + tableName;
        if (params != null && !params.isEmpty()) {
            querySQL = querySQL + " where ";
            // 处理参数
            Set<String> mapKeySet = params.keySet();
            Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
            String pamas = "";
            while (it.hasNext()) {
                String key = it.next();
                String value = toString(params.get(key));
                pamas += key + "='" + value + "' and ";
            }
            pamas = pamas.substring(0, pamas.length() - 5);
            querySQL = querySQL + pamas;
            if (!("").equals(toString(sort))) {
                querySQL = querySQL + " order by " + sort;
                if (!("").equals(toString(sortType))) {
                    querySQL = querySQL + " " + sortType;
                }
            }
        }
        System.out.println(querySQL);
        return DBUtility.execSQL(querySQL);
    }

    // 查（分页） tableID 表ID(只是id名 例：USERID)
   /* @Override
    public List<Map<String, Object>> queryAllTable(String tableName,
                                                   int pageSize, int currentPage, String sort, String sortType,
                                                   String tableID) throws SQLException {
        return queryAllTable(tableName, pageSize, currentPage, null, sort,
                sortType, tableID);
    }*/

    // 查（分页、参数）

    /**
     * tableName 表名 pageSize 每页条数 currentPage 当前页 params 查询参数 sort 排序 sortType
     * tableID 表ID(只是id名 例：USERID)
     */
   /* @Override
    public List<Map<String, Object>> queryAllTable(String tableName,
                                                   int pageSize, int currentPage, Map<String, Object> params,
                                                   String sort, String sortType, String tableID) throws SQLException {
        // 获取数据库类型
        String jdbcType = DBUtility.getJdbcType();
        String orderStr = "";
        if (!("").equals(toString(sort))) {
            orderStr = orderStr + " order by " + sort;
            if (!("").equals(toString(sortType))) {
                orderStr = orderStr + " " + sortType;
            }
        }
        int startSize = 0;
        if ((currentPage - 1) >= 0) {
            startSize = (currentPage - 1) * pageSize;// 开始位置
        }


        if (jdbcType.equals("sqlserver")) {// sqlserver分页

            if (params == null || params.isEmpty()) {// 不带参数
                String querySQL = "SELECT TOP " + pageSize + " * FROM "
                        + tableName + " WHERE (" + tableID
                        + " NOT IN (SELECT TOP " + startSize + " " + tableID
                        + " FROM " + tableName + " " + orderStr + "))" + orderStr;
                System.out.println(querySQL);
                return DBUtility.execSQL(querySQL);

            } else {// 带参数
                // 处理参数
                Set<String> mapKeySet = params.keySet();
                Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
                String pamas = "";
                while (it.hasNext()) {
                    String key = it.next();
                    String value = toString(params.get(key));
                    pamas += key + "='" + value + "' and ";
                }
                pamas = pamas.substring(0, pamas.length() - 5);
                String querySQL = "SELECT TOP " + pageSize + " * FROM "
                        + tableName + " WHERE (" + tableID
                        + " NOT IN (SELECT TOP " + startSize + " " + tableID
                        + " FROM " + tableName + " " + orderStr + ")) and "
                        + pamas + " " + orderStr;
                System.out.println(querySQL);
                return DBUtility.execSQL(querySQL);
            }

        } else if (jdbcType.equals("mysql")) {// mysql分页

            if (params == null || params.isEmpty()) {// 不带参数
                String querySQL = "SELECT * FROM " + tableName + " " + orderStr
                        + " LIMIT " + startSize + "," + pageSize;
                System.out.println(querySQL);
                return DBUtility.execSQL(querySQL);
            } else {
                // 处理参数
                Set<String> mapKeySet = params.keySet();
                Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
                String pamas = "";
                while (it.hasNext()) {
                    String key = it.next();
                    String value = toString(params.get(key));
                    pamas += key + "='" + value + "' and ";
                }
                pamas = pamas.substring(0, pamas.length() - 5);

                String querySQL = "SELECT * FROM " + tableName + " WHERE "
                        + pamas + " " + orderStr + " LIMIT " + startSize + ","
                        + pageSize;
                System.out.println(querySQL);
                return DBUtility.execSQL(querySQL);
            }

        } else if (jdbcType.equals("oracle")) {// oracle分页

            int endSize = currentPage * pageSize;// 开始位置
            startSize = startSize + 1;
            if (params == null || params.isEmpty()) {// 不带参数
                String querySQL = "select * from (select A.*,rownum rn from (select * from "
                        + tableName
                        + " "
                        + orderStr
                        + ")A where rownum <= "
                        + endSize
                        + ")tl where rn >= "
                        + startSize
                        + " "
                        + orderStr;
                return DBUtility.execSQL(querySQL);
            } else {
                // 处理参数
                Set<String> mapKeySet = params.keySet();
                Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
                String pamas = "";
                while (it.hasNext()) {
                    String key = it.next();
                    String value = toString(params.get(key));
                    pamas += key + "='" + value + "' and ";
                }
                pamas = pamas.substring(0, pamas.length() - 5);
                String querySQL = "select * from (select A.*,rownum rn from (select * from "
                        + tableName
                        + " where "
                        + pamas
                        + " "
                        + orderStr
                        + ")A where rownum <= "
                        + endSize
                        + ")tl where rn >= "
                        + startSize + " " + orderStr;
                return DBUtility.execSQL(querySQL);
            }
        }
        return null;
    }*/

    // 获取条数（带参数）
    @Override
    public int getCount(String tableName, Map<String, Object> params)
            throws SQLException {
        List<Map<String, Object>> list = null;
        String sql = "";
        if (params != null && !params.isEmpty()) {
            // 处理参数
            Set<String> mapKeySet = params.keySet();
            Iterator<String> it = mapKeySet.iterator();// 有了Set集合，就可以获取其迭代器。
            String pamas = "";
            while (it.hasNext()) {
                String key = it.next();
                String value = toString(params.get(key));
                pamas += key + "='" + value + "' and ";
            }
            pamas = pamas.substring(0, pamas.length() - 5);
            sql = "SELECT COUNT(*) FROM " + tableName + " where " + pamas;
        } else {
            sql = "SELECT COUNT(*) FROM " + tableName;
        }
        list = DBUtility.execSQL(sql);
        return Integer.valueOf(toString(list.get(0).get("COUNT(*)")));
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
