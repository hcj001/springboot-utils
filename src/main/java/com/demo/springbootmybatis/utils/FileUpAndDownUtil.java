package com.demo.springbootmybatis.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.*;

public class FileUpAndDownUtil {

    public static final String targetFilePath = "F:\\test\\uploadTest";
    public static void uploadFile(InputStream fis, String fileName) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File targetFile = new File(targetFilePath+File.separator+fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);
            IOUtils.copy(fis,fos);
        } catch (IOException e) {
            throw new IOException(e);
        }finally {
            fos.close();
        }

    }

}
