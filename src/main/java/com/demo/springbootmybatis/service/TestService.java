package com.demo.springbootmybatis.service;

import com.demo.springbootmybatis.dto.ReturnValue;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestService {

    public ReturnValue uploadFileTest(MultipartFile zipFile);
    public ReturnValue uploadFilesTest(List<MultipartFile> files);
//    public ReturnValue uploadFilesCallableTest(List<MultipartFile> files);
}
