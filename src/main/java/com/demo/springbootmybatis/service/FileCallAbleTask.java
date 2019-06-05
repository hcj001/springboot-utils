package com.demo.springbootmybatis.service;

import com.demo.springbootmybatis.dto.ReturnValue;
import com.demo.springbootmybatis.entity.ReturnCodeAndMsgEnum;
import com.demo.springbootmybatis.utils.FileUpAndDownUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;

public class FileCallAbleTask implements Callable {

    private MultipartFile file;

    public FileCallAbleTask(MultipartFile file) {
        this.file = file;
    }

    @Override
    public ReturnValue call() throws Exception {
        String filename = UUID.randomUUID().toString().replace("-","")+".jpg";
        try {
            FileUpAndDownUtil.uploadFile(file.getInputStream(),filename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return new ReturnValue<>(ReturnCodeAndMsgEnum.SUCCESS);
    }
}
