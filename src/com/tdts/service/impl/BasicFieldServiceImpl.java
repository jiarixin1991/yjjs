package com.tdts.service.impl;

import com.tdts.dao.BasicFieldDao;
import com.tdts.dao.TemplateTableDao;
import com.tdts.dao.impl.BasicFieldDaoImpl;
import com.tdts.dao.impl.TemplateTableDaoImpl;
import com.tdts.service.BasicFieldService;
import com.tdts.util.StrUtil;
import core.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 16:42
 **/
public class BasicFieldServiceImpl implements BasicFieldService {
    private BasicFieldDao bfd = new BasicFieldDaoImpl();
    private TemplateTableDao ttd = new TemplateTableDaoImpl();

    @Override
    public int saveTempField(Map<String, Object> params) {

        return 0;
    }


    @Override
    public int saveTempFieldList(String templateId, List<Map<String, Object>> list
            , Map<String, Object> map) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        //加入英文字段名
        try {

            for (Map<String, Object> stringMap : list) {
                String uuid8 = StrUtil.generateShortUuid();
                String fieldName = "name";
                fieldName += uuid8;
                System.err.println(fieldName);
                stringMap.put("fieldName", fieldName);
            }
            //预编译的值
            List<String> listStr = new ArrayList<>();
            listStr.add("fieldName");
            listStr.add("fieldType");
            listStr.add("fieldSize");
            listStr.add("cn_fieldName");
            listStr.add("used");
            listStr.add("isFill");
            listStr.add("isSecret");
            listStr.add("isRepeat");
            listStr.add("fieldState");
            listStr.add("remarks");
            System.err.println(list);
            int i = bfd.saveTempFieldList(conn, templateId, listStr, list);
            if (i != 0) {
                conn.rollback();
                return 1;
            }
            int num = ttd.saveTemp(map);
            if (num != 0) {
                conn.rollback();
                return 1;
            }

            conn.commit();
            return 0;

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
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
        return bfd.findBasicField();
    }

    @Override
    public List<Map<String, Object>> findbasicFieldByFieldName(String templateId, String FieldName) {
        return null;
    }
}
