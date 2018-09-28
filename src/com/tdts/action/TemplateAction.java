package com.tdts.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.Action;
import com.tdts.service.BasicFieldService;
import com.tdts.service.impl.BasicFieldServiceImpl;
import com.tdts.util.StrUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模板操作
 *
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-21 15:34
 **/
public class TemplateAction extends BaseAction {
    private BasicFieldService bfs = new BasicFieldServiceImpl();
    private String TempName;//模板名称
    private String jsonSend;//返回值
    private JSONObject jsonStr = JSONObject.fromObject("{}");
    private Map<String, Object> mapSend = new HashMap<>();
    private String jsonReceive;//接收数据
    private String params;
    private String data;
    private String formdata;

    /**
     * 查询固定字段
     * 查询模板固定字段
     *
     * @return
     */
    public String findFixedField() {
        try {
            //查出固定字段
            List<Map<String, Object>> basicField = bfs.findBasicField();
            if (null == basicField) {//获取字段出现错误
                mapSend.put("code", 4);
                mapSend.put("errorDesc", "查询出错");

            } else {
                //有数据
                mapSend.put("code", 0);
                mapSend.put("count", basicField.size());
                mapSend.put("values", basicField);
            }
        } finally {
            //返回的固定字段数据
            jsonSend = JSON.toJSONString(mapSend);
        }


        return Action.SUCCESS;
    }

    /**
     * 通过模板名称或模板ID 查询模板信息
     * 可能会要用分页
     *
     * @return
     */
    public String findTemplateByTempName() {

        return Action.SUCCESS;
    }


    /**
     * 创建条目模板字段（多行）
     * 操作两个表 创建模板表TemplateTable 和 basicField 表
     */

    public String createTemplateField() {
        //接收的数据
        JSONObject jsonstr = JSONObject.fromObject(jsonReceive);
        String userId = jsonstr.getString("userid");
        TempName = jsonstr.getString("TempName");
        if (jsonReceive == null) {
            mapSend.put("code", 1);
            mapSend.put("errorDesc", "参数不全");
        } else {
            if (!jsonstr.containsKey("userid")) {
                mapSend.put("code", 2);
                mapSend.put("errorDesc", "没有权限信息，不能创建");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String stime = sdf.format(new Date());
                String uuid = StrUtil.getUUID();
                dataMap.put("TempId", uuid);
                dataMap.put("TempName", TempName);
                dataMap.put("Stime", stime);

                //获取模板数据集合
                params = jsonstr.getString("params");

                JSONArray jsonList = JSONArray.fromObject(this.params);

                try {
                    int i = bfs.saveTempFieldList(uuid, jsonList, dataMap);
                    if (i != 0) {
                        mapSend.put("code", 1);
                        mapSend.put("errorDesc", "创建失败");
                    } else {
                        //创建成功！！！
                        mapSend.put("code", 0);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        jsonSend = JSONObject.fromObject(mapSend).toString();
        return Action.SUCCESS;
    }

    /**
     * 修改模板
     *
     * @return
     */
    public String updateTemplateField() {

        return Action.SUCCESS;
    }


    /**
     * 新增条目模板字段（一行）
     */
    public String newCreateTemplateField() {

        return Action.SUCCESS;
    }

    /**
     * 删除模板字段
     *
     * @return
     */

    public String deleteTemplateField() {

        return Action.SUCCESS;
    }

    /**
     * TODO 删除模板（暂定）
     */

    public String deleteTemplate() {


        return Action.SUCCESS;
    }

    public String getTempName() {
        return TempName;
    }

    public void setTempName(String tempName) {
        TempName = tempName;
    }

    public String getJsonSend() {
        return jsonSend;
    }

    public void setJsonSend(String jsonSend) {
        this.jsonSend = jsonSend;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {

        this.params = params;
    }

    public JSONObject getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(JSONObject jsonStr) {
        this.jsonStr = jsonStr;
    }

    public Map<String, Object> getMapSend() {
        return mapSend;
    }

    public void setMapSend(Map<String, Object> mapSend) {
        this.mapSend = mapSend;
    }

    public String getJsonReceive() {
        return jsonReceive;
    }

    public void setJsonReceive(String jsonReceive) {
        this.jsonReceive = jsonReceive;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }
}
