package com.tdts.junit;

import com.tdts.dao.BasicFieldDao;
import com.tdts.dao.SysTemplateManageDao;
import com.tdts.dao.TemplateTableDao;
import com.tdts.dao.impl.BasicFieldDaoImpl;
import com.tdts.dao.impl.SysTemplateManageDaoImpl;
import com.tdts.dao.impl.TemplateTableDaoImpl;
import com.tdts.util.SqlserJdbcUtil;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-20 10:34
 * DAO层测试
 **/
public class testDaoJunit {//extends StrutsTestCase
    //private TemplateManageDao tmd = new TemplateManageDaoImpl();
    private BasicFieldDao bfd = new BasicFieldDaoImpl();
    private TemplateTableDao tptd = new TemplateTableDaoImpl();

    /**
     * 测试 TemplateManageDaoimpl.java  saveAllDocField
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testA() throws SQLException, IOException, ClassNotFoundException {
        SysTemplateManageDao t = new SysTemplateManageDaoImpl();
        /*String jsonStr = "{\"cn_fieldName\":\"5\",\"fieldName\":\"1\",\"fieldnum\":\"12\",\"type-name\":\"i\",\"shemi\":\"0\",\"not-empty\":\"0\"}";*/

        String jsonStr = "[{\"fieldname\":\"name1\",\"cn-fieldname\":\"功率为各位\",\"fieldnum\":\"12\",\"type-name\":\"i\",\"shemi\":\"0\",\"fileType\":\"0\"},{\"fieldname\":\"name2\",\"cn-fieldname\":\"却纷纷去\",\"fieldnum\":\"6556\",\"type-name\":\"v\",\"shemi\":\"1\",\"fileType\":\"300\"},{\"fieldname\":\"name3\",\"cn-fieldname\":\"隔行如有空3\",\"fieldnum\":\"\",\"type-name\":\"d\",\"shemi\":\"0\",\"fileType\":\"0\"}]";
        //JSONObject j = JSONObject.fromObject(jsonStr);
        JSONArray j = JSONArray.fromObject(jsonStr);
        Connection conn = SqlserJdbcUtil.getConnection();
        List<String> values = new ArrayList<String>();
        values.add("fieldname");
        values.add("fieldtype");
        values.add("fieldsize");
        values.add("cn-fieldname");
        values.add("used");
        values.add("isfill");
        values.add("issecret");
        values.add("isrepeat");
        values.add("fieldstate");
        System.err.println(values);
        t.saveAllDocField(conn, "docXXXdd2", values, j);
    }


}
