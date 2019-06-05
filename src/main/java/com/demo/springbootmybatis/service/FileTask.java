package com.demo.springbootmybatis.service;

import com.demo.springbootmybatis.utils.FileUpAndDownUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FileTask implements Runnable {

    private MultipartFile file;

    public FileTask(MultipartFile file) {
        this.file = file;
    }

    @Override
    public void run() {
            String filename = UUID.randomUUID().toString().replace("-","")+".jpg";
            try {
                FileUpAndDownUtil.uploadFile(file.getInputStream(),filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
