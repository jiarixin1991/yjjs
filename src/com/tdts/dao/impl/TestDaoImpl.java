package com.tdts.dao.impl;

import com.tdts.dao.TestDao;
import com.tdts.util.SqlserJdbcUtil;

import java.sql.SQLException;

public class TestDaoImpl implements TestDao {
    //private  SqlserJdbcUtil strsql = new SqlserJdbcUtil();
    @Override
    public void demoTest() {
        String sql = "alter table Students add gongling int NULL";
        try {
            int i = SqlserJdbcUtil.execSQLWithTrans(sql);
            System.out.println("结果（Y0/N1）"+i);
            System.err.println("结果（Y0/N1）"+i);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {

        TestDao t = new TestDaoImpl();
        t.demoTest();

    }
}
