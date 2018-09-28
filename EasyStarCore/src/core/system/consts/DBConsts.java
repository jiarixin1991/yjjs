package core.system.consts;

public class DBConsts {
    /**
     * 数据库配置文件说明
     * 如果是普通文件，则此配置文件必须在classes目录下，例如：MsSql-config.xml
     * 如果是url，则需要把core单独部署，例如：http://127.0.0.1/core/GetDBConfigAction，主机地址必须是IP
     * 如果是空，则从core的DBConnection类中获取
     */
    public static final String DB_CONFIG_FILENAME = "MsSql-config.xml";
    //pu static final String DB_CONFIG_URL = "http://127.0.0.1/core/GetDBConfigAction";

    public static final byte DB_SQLSERVER = 0;
    public static final byte DB_MYSQL = 1;
    public static final byte DB_ORACLE = 2;
}
