package core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ConfigUtility {
    public InputStream readDbConfig(String fileName) {
        FileInputStream fis = null;
        try {
            String path = URLDecoder.decode(this.getClass().getClassLoader().getResource("/").getPath(), "utf-8");
            fis = new FileInputStream(path + fileName);
            return fis;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
