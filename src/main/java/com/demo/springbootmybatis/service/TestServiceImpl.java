package com.demo.springbootmybatis.service;

import com.demo.springbootmybatis.dto.ReturnValue;
import com.demo.springbootmybatis.entity.ReturnCodeAndMsgEnum;
import com.demo.springbootmybatis.utils.FileUpAndDownUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class TestServiceImpl implements TestService{


    @Override
    public ReturnValue uploadFileTest(MultipartFile zipFile) {

        String filename = UUID.randomUUID().toString().replace("-","")+".jpg";
        try {
            FileUpAndDownUtil.uploadFile(zipFile.getInputStream(),filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new ReturnValue(-1,e.getMessage());
        }
        return new ReturnValue(ReturnCodeAndMsgEnum.SUCCESS,null);
    }

    @Override
    public ReturnValue uploadFilesTest(List<MultipartFile> files) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        StringBuilder msg = new StringBuilder();
        for(int i=0;i<files.size();i++) {
            if(files != null && !files.get(i).isEmpty()) {
                FileTask task = new FileTask(files.get(i));
                executorService.submit(task);
            } else {
                msg.append("第"+(i+1)+"个文件为空");
            }
        }
        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (true) {
            if(executorService.isTerminated()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ReturnValue(ReturnCodeAndMsgEnum.SUCCESS);
    }

}
