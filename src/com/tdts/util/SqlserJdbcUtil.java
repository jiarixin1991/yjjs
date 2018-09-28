package com.tdts.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 配置数据源
 *
 * @author CJL
 */
public class SqlserJdbcUtil {

    public static Connection getConnection() throws IOException,
            ClassNotFoundException, SQLException {
        // 1) 读取配置文件
        Properties properties = new Properties();

        String path = SqlserJdbcUtil.class.getResource("/").getPath();
        String websiteURL = (path.replace("/build/classes", "").replace("%20",
                " ") + "jdbc.properties").replaceFirst("/", "");
        properties.load(new FileInputStream(websiteURL));
        // 2) 获取配置文件中的必要的信息
        String driverClass = properties.getProperty("driverClass");
        String url = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        // 3) 注册驱动 , 加载驱动类
        Class.forName(driverClass);
        // 4) 通过驱动管理器获取连接(需要url,用户,密码)
        return DriverManager.getConnection(url, user, password);
    }

    /*
     * 关闭数据库连接，注意关闭的顺序
     */
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
                ps = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Map<String, Object>> execSQL(String sql)
            throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            conn.close();
        }
        return list;
    }

    public static List<Map<String, Object>> execSQL(String sql, Object[] params)
            throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            conn.close();
        }
        return list;
    }

    public static List<Map<String, Object>> execSQL(Connection conn,
                                                    String sql, Object[] params) throws SQLException {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            conn.close();
        }
        return list;
    }

    public static List<Map<String, Object>> execSQL(Connection conn, String sql)
            throws SQLException {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            conn.close();
        }
        return list;
    }

    public static int execSQLWithTrans(String sql) throws SQLException {
        return execSQLWithTrans(sql, null);
    }

    public static int execSQLWithTrans(String sql, Object[] params)
            throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setString(i + 1, params[i].toString()
                            .trim());
                }
            }
            ps.executeUpdate();// 必须先执行保存换房记录的操作，因为要从原学生信息中得到旧房间号
            conn.commit();
            return 0;
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();

            return 1;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public static int execSQLWithTrans(Connection conn, String sql)
            throws SQLException {
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
                                    .toString().trim());
                        }
                    }
                    ps.executeUpdate();
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 执行Update、Delete等SQL语句，带参数，使用事务 执行成功，返回0，否则返回1
     */
    public static int execSQLWithTrans(Connection conn, String sql, String ID, List<String> params) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params != null && ID != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setString(1, ID);
                    ps.setString(2, params.get(i).toString().trim());
                    ps.executeUpdate();
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    //根据ID批量删除
    public static int execSQLWithTrans(Connection conn, String sql, List<String> params) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setString(1, params.get(i).toString().trim());
                ps.executeUpdate();
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }


    public static int execSQLWithTrans(String sql, List<String> params,
                                       List<Map<String, Object>> values) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            if (values != null && params != null) {
                for (Map<String, Object> map : values) {
                    for (int i = 0; i < params.size(); i++) {
                        if (map.get(params.get(i)) == null) {
                            ps.setString(i + 1, "");
                        } else {
                            ps.setString(i + 1, map.get(params.get(i))
                                    .toString().trim());
                        }
                    }
                    ps.executeUpdate();
                }
            }
            conn.commit();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            return 1;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    /**
     * 执行SELECT语句，不使用事务
     *
     * @return List<String>
     */
    public static List<Object> execSQLToList(String sql) throws SQLException {
        Connection conn = null;
        List<Object> list = new ArrayList<Object>();
        try {
            conn = getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new ColumnListHandler(1));
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        } finally {
            conn.close();
        }
        return list;
    }

    // 获得驱动类类型
    public static String getJdbcType() {

        // 1) 读取配置文件
        Properties properties = new Properties();

        String path = SqlserJdbcUtil.class.getResource("/").getPath();
        String websiteURL1 = (path.replace("/build/classes", "").replace("%20",
                " ") + "jdbc.properties").replaceFirst("/", "");
        try {
            properties.load(new FileInputStream(websiteURL1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2) 获取配置文件中的必要的信息
        String url = properties.getProperty("jdbcUrl");
        String[] urlObj = url.split(":");
        return urlObj[1].toString();
    }

}
