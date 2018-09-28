package com.tdts.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

public class md5FileUploadAction extends BaseAction {
    private String fileMd5;
    private String chunk;
    private String chunkSize;
    private String fileName;
    private String guid;
    private String jsonStr;
    private String fileSuffix;

    //MD5上传
    public String md5FileUpload() throws UnsupportedEncodingException {
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(fac);
        sfu.setHeaderEncoding("UTF-8");
        //单个文件最大值
        sfu.setFileSizeMax(15 * 1024 * 1024L);
        //整个request的最大值
        sfu.setSizeMax(15 * 1024 * 1024L);
        sfu.setHeaderEncoding("UTF-8");


        //保存路径
        String realPath = getRequest().getSession().getServletContext().getRealPath("/upload");
        //获取配置文件system.properties信息
        ResourceBundle systemRes = ResourceBundle.getBundle("system");
        //存储路径
        String savePath = systemRes.getString("savePath");
        System.out.println("存储路径:" + savePath);
        //判断提交上传数据是否是表单形式
        if (!ServletFileUpload.isMultipartContent(getRequest())) {
            System.out.println("判断是表单还是单独上传文件:" + ServletFileUpload.isMultipartContent(getRequest()));

            return null;
        }
        List<FileItem> items = null;
        //解析传递的数据
        try {
            items = sfu.parseRequest(getRequest());
            //System.out.println("items:" + items);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String fileMd5 = null;
        String chunk = null;
        Map<String, Object> map = new HashMap<>();
        for (FileItem item : items) {
            //非表单域
            if (item.isFormField()) {//保存有用的数据
                String name = item.getFieldName();
                String value = "";
                value = item.getString("UTF-8");
                System.out.println(name + "=" + value);
                map.put(name, value);
                if (name.equals("fileMd5")) {
                    fileMd5 = item.getString("utf-8");
                }
                if (name.equals("chunk")) {
                    chunk = item.getString("utf-8");
                }
            } else {//非表单域的信息
                System.err.println("GUID:" + map.get("guid"));
                System.err.println("fileMd5:" + fileMd5);
                File fileParent = new File(savePath + "\\" + fileMd5 + map.get("guid"));//item.getName()文件名
                //文件夹不存在创建
                if (!fileParent.exists()) {
                    fileParent.mkdir();
                }
                String fileName = item.getName();
                System.out.println("fileName:" + fileName);
                if (fileName.trim().equals("") || fileName == null) {
                    System.err.println("名字为空！！！");
                    continue;

                }
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                //创建文件
                File file;
                if (map.get("chunks") != null) {//分片
                    file = new File(fileParent, (String) map.get("chunk"));
                    System.err.println("file:" + file);
                    System.err.println("保存" + fileName + "文件chunk:" + map.get("chunk"));
                } else {//没分片 直接下标为0
                    file = new File(fileParent, "0");
                }
                try {
                    FileUtils.copyInputStreamToFile(item.getInputStream(), file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        return null;
    }

    //md5校验
    public String Md5Check() {
        String realPath = getRequest().getSession().getServletContext().getRealPath("/upload");
        //获取配置文件system.properties信息
        ResourceBundle systemRes = ResourceBundle.getBundle("system");
        //存储路径
        String savePath = systemRes.getString("savePath");
        File file = new File(savePath + "\\" + fileMd5 + guid + "\\" + chunk);

        if (file.exists() && file.length() == Integer.parseInt(chunkSize)) {
            jsonStr = "{\"ifExist\":0}";//上传过
        } else {
            jsonStr = "{\"ifExist\":1}";//没上传过，或上传不完整
        }

        return Action.SUCCESS;
    }

    //合并文件
    public String mergeFiles() throws IOException {
        ResourceBundle systemRes = ResourceBundle.getBundle("system");
        //临时文件目录
        String realPath = getRequest().getSession().getServletContext().getRealPath("/upload");
        //正式文件存放目录
        String savePath = systemRes.getString("savePath");
        //获取临时文件目录
        System.err.println("fileMd5-----"+fileMd5);
        File file = new File(savePath + "/" + fileMd5 + guid);


        File[] fileArray = file.listFiles(new FileFilter() {
            // 排除目录只要文件
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }
                return true;
            }
        });
        System.err.println("fileArray-----"+fileArray);
        //转成集合,便于排序
        List<File> fileList = new ArrayList<>(Arrays.asList(fileArray));
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File o1, File o2) {
                if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                    return -1;
                }
                return 1;
            }
        });
        System.err.println("fileSuffix："+fileSuffix);
        File outPutFile = new File(savePath + "/" + UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix);
        //创建文件
        outPutFile.createNewFile();
        //输出流
        FileChannel outChannel = new FileOutputStream(outPutFile).getChannel();
        FileChannel inChannel;
        for (File file1 : fileList) {

            inChannel = new FileInputStream(file1).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            //删除分片
            file1.delete();
        }
        outChannel.close();
        //清除文件夹
        if (file.isDirectory() && file.exists()) {
            file.delete();
        }
        System.err.println("合并成功1");
        System.err.println("文件路径:" + outPutFile.getPath());
        jsonStr = "0";
        return Action.SUCCESS;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getChunk() {
        return chunk;
    }

    public void setChunk(String chunk) {
        this.chunk = chunk;
    }

    public String getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(String chunkSize) {
        this.chunkSize = chunkSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
}
