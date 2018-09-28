package Dao.impl;

import Dao.FilesManageDAO;
import core.db.DBUtility;

import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-09-26 09:28
 **/
public class FilesManageDAOImpl implements FilesManageDAO {
    //1.提供原件挂接时的数据  一个原件绑定一个条目  一个条目绑多个原件
    @Override
    public List<Map<String, Object>> findFilesBySysId(String sysId, Map<String, Object> params, int pageSize, int currentPage, String sort, String sortType) {
        String str = "";
        if (params != null) {
            if (params.containsKey("FILENAME")) {
                str = "AND f.FILENAME LIKE '%" + params.get("FILENAME").toString() + "%'";
            }
        }

        String sql = "SELECT * FROM Files AS f WHERE  NOT EXISTS(SELECT * FROM FilesRight AS r WHERE r.fileId = f.FILEID )" +
                " AND f.STATUS = '0'   AND f.SYSID = '" + sysId + "'" + str;
        return DBUtility.execSQL(sql);
    }

    @Override
    public int totalRows(String sysId, Map<String, Object> params) {
        String str = "";
        if (params != null) {
            if (params.containsKey("FILENAME")) {
                str = "AND f.FILENAME LIKE '%" + params.get("FILENAME").toString() + "%'";
            }
        }

        String sql = "SELECT COUNT(*) AS num FROM Files AS f WHERE  NOT EXISTS(SELECT * FROM FilesRight AS r WHERE r.fileId = f.FILEID )" +
                " AND f.STATUS = '0'   AND f.SYSID = '" + sysId + "'" + str;
        List<Map<String, Object>> list = DBUtility.execSQL(sql);
        if (list != null) {
            //return list.get(0);
        }
        return 0;
    }


}
