package Dao;

import java.util.List;
import java.util.Map;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-09-26 09:01
 * @return 原件管理
 **/
public interface FilesManageDAO {

    //1.提供原件挂接时的数据  一个原件绑定一个条目  一个条目绑多个原件
    public List<Map<String, Object>> findFilesBySysId(String sysId, Map<String, Object> params, int pageSize,
                                                      int currentPage, String sort, String sortType);

    //1.1 count 提供原件挂接时的数据
    public int totalRows(String sysId, Map<String, Object> params);
//2.保存条目挂接的原件 目前是点一个ajax直接保存一个

//3.查询以挂接的

//4.取消挂接

//5.查询一以挂接的带事务的

//6.删除条目时删除挂接的原件

//8.批量删除、单个删除

//7.原件全查


}
