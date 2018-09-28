package core.db;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBUtility {


    /**
     * @param sql SQL文本
     * @return 扫行结果
     * @author LeonSu
     * @time $time$
     * @method 执行SQL语句
     * @version V1.0.0
     * @description
     */
    public static List<Map<String, Object>> execSQL(String sql) {
        try (Connection conn = DBConnection.getConnection()) {
//            PreparedStatement state = conn.prepareStatement(sql);
            QueryRunner qr = new QueryRunner();
            List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MethodName:execSQL
     * <p>
     * Description:带参数的查询
     * </p>
     *
     * @param conn   连接
     * @param sql    查询语句
     * @param params 参数值
     * @return
     * @author DuXiao
     */
    public static List<Map<String, Object>> execSQL(Connection conn,
                                                    String sql, Object[] params) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryRunner qr = null;
        try {
            qr = new QueryRunner();
            list = qr.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return list;
        }
        return list;
    }

    /**
     * @param * @param null
     * @return
     * @author
     * @time $time$
     * @method
     * @version V1.0.0
     * @description
     */
    public static int execSQLWithTrans(String sql) {
        return execSQLWithTrans(sql, null, null);
    }

    /**
     * @param * @param null
     * @return
     * @author
     * @time $time$
     * @method
     * @version V1.0.0
     * @description
     */
    public static int execSQLWithTrans(Connection conn, String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 执行Update、Delete等SQL语句，带参数，使用事务 执行成功，返回0，否则返回1
     */
    public static int execSQLWithTrans(Connection conn, String sql,
                                       List<String> params, List<Map<String, Object>> values)
            throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (values != null && params != null) {
                for (Map<String, Object> map : values) {
                    for (int i = 0; i < params.size(); i++) {
                        if (map.get(params.get(i)) == null) {
                            ps.setString(i + 1, "");
                        } else {
                            ps.setString(i + 1, map.get(params.get(i))
                                    .toString().toUpperCase().trim());
                        }
                    }
                    ps.executeUpdate();
                }
            }
            return 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return 1;
        }
    }

    /**
     * @return 0：SQLServer；1：MySQL；2：Oracle
     */
    public static byte getDBType() {
        return DBConnection.getType();
    }

    public static ResultSetMetaData getResultSetMetaData(String sql) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return ps.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int execSQLWithTrans(String sql,
                                       List<String> params, List<Map<String, Object>> values) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            if (values != null && params != null) {
                for (Map<String, Object> map : values) {
                    for (int i = 0; i < params.size(); i++) {
                        if (map.get(params.get(i)) == null) {
                            ps.setString(i + 1, "");
                        } else {
                            ps.setString(i + 1, map.get(params.get(i)).toString()
                                    .toUpperCase().trim());
                        }
                    }
                    ps.executeUpdate();
                }
            } else {
                ps.executeUpdate();
            }
            conn.commit();
            return 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    return 1;
                }
            }
            return 1;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return 1;
                }
            }
        }
    }

    public static int execSQLWithTrans(Connection conn, String sql,
                                       List<ColumnInfo> colList, Map<String, Object> value) {
        // TODO Auto-generated method stub
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (value != null && colList != null) {
                for (int i = 0; i < colList.size(); i++) {
                    if (value.get(colList.get(i).getColumnName()) == null) {
                        ps.setString(i + 1, "");
                    } else {
                        ps.setString(i + 1,
                                value.get(colList.get(i).getColumnName())
                                        .toString().toUpperCase().trim());
                    }
                }
                ps.executeUpdate();
            }
            return 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        DBConnection.getConnection();
    }
}
