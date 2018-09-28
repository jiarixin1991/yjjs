package core.db;

import com.sun.istack.internal.NotNull;
import core.config.ConfigUtility;
import core.system.consts.DBConsts;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库连接池
 * public方法：
 *  getConnection()
 *  getConnection(String fileName)
 * @author LeonSu
 * @date 2018-09-26
 */
public class DBConnection {
    //    /**
//     * 初始化连接池代码块
//     */
    static {
        getConnection();
    }

//    public static final byte DB_SQLSERVER = 0;
//    public static final byte DB_MYSQL = 1;
//    public static final byte DB_ORACLE = 2;

    /**
     * 数据库类型
     */
    private static byte dbType = -1;

    public static void setType(byte value) {
        dbType = value;
    }

    public static byte getType() {
        return dbType;
    }

    private static Properties properties = null;

    private static Properties loadPropertiesFromFile(String fileName) {
        FileInputStream fis = null;
        try {
            DBUtility dbUtility = new DBUtility();
            String path = dbUtility.getClass().getResource("/").getPath();
            path = URLDecoder.decode(path, "utf-8");
            fis = new FileInputStream(path + fileName);
            Properties properties = new Properties();
            properties.loadFromXML(fis);
            return properties;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Properties loadPropertiesFromUrl(String urlName) {
        try {
            URL url = new URL(urlName);
            //打开连接获取连接对象
            URLConnection connection = url.openConnection();
            //能够进行远程写操作
            connection.setDoOutput(true);

            //接收返回响应信息
            StringBuilder response = new StringBuilder();
            Scanner in = new Scanner(connection.getInputStream());
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
            ByteArrayInputStream stream = new ByteArrayInputStream(response.toString().getBytes());
            Properties properties = new Properties();
            properties.loadFromXML(stream);
            return properties;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Properties loadPropertiesFromClass() {
        ConfigUtility configUtility = new ConfigUtility();
        InputStream inputStream = configUtility.readDbConfig(DBConsts.DB_CONFIG_FILENAME);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        Properties properties = null;
        try {
            properties = new Properties();
            properties.loadFromXML(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static PoolProperties setPoolProperties() {
        if (null == properties) {
            return null;
        }
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDbProperties(properties);

        poolProperties.setUrl(properties.getProperty("url"));
        poolProperties.setUsername(properties.getProperty("username"));
        poolProperties.setPassword(properties.getProperty("password"));
        poolProperties.setDriverClassName(properties.getProperty("driverClassName"));
        if (null != properties.getProperty("maxActive") && !properties.getProperty("maxActive").isEmpty()) {
            poolProperties.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
        }
        if (null != properties.getProperty("maxIdle") && !properties.getProperty("maxIdle").isEmpty()) {
            poolProperties.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        }
        if (null != properties.getProperty("minIdle") && !properties.getProperty("minIdle").isEmpty()) {
            poolProperties.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        }
        if (null != properties.getProperty("initialSize") && !properties.getProperty("initialSize").isEmpty()) {
            poolProperties.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
        }
        poolProperties.setValidationQuery(properties.getProperty("validationQuery"));

        if (properties.getProperty("url").contains("sqlserver")) {
            setType(DBConsts.DB_SQLSERVER);
        }
        if (properties.getProperty("url").contains("mysql")) {
            setType(DBConsts.DB_MYSQL);
        }
        if (properties.getProperty("url").contains("oracle")) {
            setType(DBConsts.DB_ORACLE);
        }

        return poolProperties;
    }

    private static boolean isURL(String url) {
        //转换为小写
        url = url.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(url.trim());
        return mat.matches();//判断是否匹配
    }

    private static PoolProperties getPoolProperties(String fileName) {
        if (null == properties) {
            if (!fileName.isEmpty()) {
                if (!isURL(fileName)) {
                    properties = loadPropertiesFromFile(fileName);
                } else {
                    properties = loadPropertiesFromUrl(fileName);
                }
            } else {
                properties = loadPropertiesFromClass();
            }
        }
        if (null == properties) {
            return null;
        }
        return setPoolProperties();
//            File f = new File( path + fileName);
//            SAXReader reader = new SAXReader();
//            Document doc = null;
//            doc = reader.read(f);
//            Element root = doc.getRootElement();
//            Element foo;// = (Element)root.selectSingleNode("properties");
//            Iterator<Element> iterator =  root.elementIterator();
//            PoolProperties poolProperties = new PoolProperties();
//            while (iterator.hasNext()) {
//                foo = (Element)iterator.next();
//                if (foo.attribute("key") == null)
//                    continue;;
//                if ("driverClassName".equals(foo.attribute("key").getValue()))
//                    poolProperties.setDriverClassName(foo.getTextTrim());
//                if ("url".equals(foo.attribute("key").getValue()))
//                    poolProperties.setUrl(foo.getTextTrim());
//                if ("username".equals(foo.attribute("key").getValue()))
//                    poolProperties.setUsername(foo.getTextTrim());
//                if ("password".equals(foo.attribute("key").getValue()))
//                    poolProperties.setPassword(foo.getTextTrim());
//                if ("maxIdle".equals(foo.attribute("key").getValue()))
//                    poolProperties.setMaxIdle(Integer.valueOf(foo.getTextTrim()));
////                if ("minIdle".equals(foo.attribute("key").getValue()))
////                    poolProperties.setMinIdle(Integer.valueOf(foo.getTextTrim()));
//                if ("maxActive".equals(foo.attribute("key").getValue()))
//                    poolProperties.setMaxActive(Integer.valueOf(foo.getTextTrim()));
//                if ("initialSize".equals(foo.attribute("key").getValue()))
//                    poolProperties.setInitialSize(Integer.valueOf(foo.getTextTrim()));
//                if ("testOnBorrow".equals(foo.attribute("key").getValue()))
//                    poolProperties.setTestOnBorrow(Boolean.getBoolean(foo.getTextTrim()));
//                if ("validationQuery".equals(foo.attribute("key").getValue()))
//                    poolProperties.setValidationQuery(foo.getTextTrim());
//            }

//            if (poolProperties.getUrl().contains("sqlserver"))
//                DBType.setType(DBType.DB_SQLSERVER);
//            if (poolProperties.getUrl().contains("mysql"))
//                DBType.setType(DBType.DB_MYSQL);
//            if (poolProperties.getUrl().contains("oracle"))
//                DBType.setType(DBType.DB_ORACLE);
    }


    public static Connection getConnection() {
        return getConnection("");
    }

    /**
     * 获取数据库连接池
     *
     * @param fileName
     *  数据库的配置文件名，包括扩展名，不含路径
     *  获取数据库配置的完整url
     * @return 成功返回Connection对象，失败返回null;
     */
    public static Connection getConnection(@NotNull String fileName) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        if (null == fileName) {
            dataSource.setPoolProperties(getPoolProperties(""));
        } else {
            dataSource.setPoolProperties(getPoolProperties(fileName));
        }
//        dataSource.setPoolProperties(getPoolProperties(DB_CONFIG_URL));

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
