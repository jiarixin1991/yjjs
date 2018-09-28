package com.tdts.service.impl;

import com.tdts.dao.BasicFieldDao;
import com.tdts.dao.DataBaseOperationDao;
import com.tdts.dao.SysTemplateManageDao;
import com.tdts.dao.impl.BasicFieldDaoImpl;
import com.tdts.dao.impl.DataBaseOperationDaoImpl;
import com.tdts.dao.impl.SysTemplateManageDaoImpl;
import com.tdts.service.SysTemplateManageService;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-20 10:16
 **/

public class SysTemplateManageServiceImpl implements SysTemplateManageService {
    private SysTemplateManageDao stmd = new SysTemplateManageDaoImpl();
    private BasicFieldDao bfd = new BasicFieldDaoImpl();
    private DataBaseOperationDao dbo = new DataBaseOperationDaoImpl();

    /**
     * 创建对应系统模板
     * TODO 创建表固定字段还没有处理需要做出修改考虑 key  createTemplate 方法cretae语句还没有加入主键判断
     * @param conn
     * @param docTableName 创建的表名
     * @param templateId   模板的ID
     * @param list         数据集合
     * @return
     */
    @Override
    public int CreateTemplate(Connection conn, String docTableName, String templateId, List<Map<String, Object>> list) {

        int template = dbo.createTemplate(conn, docTableName, list);
        if (template != 0) {
            return template;
        }
        //bfd.saveTempFieldList(conn, docTableName,templateId, list);
        int i = stmd.BasicFieldToDocFrame(conn, docTableName, templateId);
        if (i != 0) {
            return i;
        }
        return 0;
    }

    /**
     * 新增模板字段
     *
     * @param conn
     * @param docTableName
     * @param params
     * @return
     */
    @Override
    public int newCreateField(Connection conn, String docTableName, Map<String, Object> params) {
        int field = dbo.createField(conn, docTableName, params);
        if (field != 0) {
            return field;
        }
        int i = stmd.saveDocField(conn, docTableName, params);

        if (i != 0) {
            return i;
        }

        return 0;
    }

    @Override
    public int updateField(Connection conn, String docTableName, String frameId, Map<String, Object> params) {
        int i = dbo.updateField(conn, docTableName, params);
        if (i != 0) {
            return i;
        }
        int num = stmd.updateDocField(conn, docTableName, params, frameId);
        if (num != 0) {
            return num;
        }

        return 0;
    }

    public int deleteField(Connection conn, String docTableName, String frameId, Map<String, Object> params) {
        int i = dbo.deleteField(conn, docTableName, params);
        if (i != 0) {
            return i;
        }
        int num = stmd.deleteDocField(conn, docTableName, params, frameId);
        if (num != 0) {
            return num;
        }
        return 0;
    }

    /**
     * 查看对应系统的所有字段
     *
     * @param tableName 对应的是哪个系统下的字段表名
     * @return 字段集合
     */
    public List<Map<String, Object>> findDocFrame(String tableName) {

        return stmd.findDocFrame(tableName);

    }



}
