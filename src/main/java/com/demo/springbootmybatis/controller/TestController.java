package com.demo.springbootmybatis.controller;

import com.demo.springbootmybatis.dto.ReturnValue;
import com.demo.springbootmybatis.entity.ReturnCodeAndMsgEnum;
import com.demo.springbootmybatis.service.TestService;
import com.demo.springbootmybatis.utils.EsaypoiUtil;
import com.demo.springbootmybatis.utils.PdfTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/index")
    public String index() {
        return "label";
    }
    @GetMapping("/index2")
    public String index2() {
        return "label2";
    }
    @RequestMapping("/file")
    public String file() {
        return "file";
    }
    @GetMapping("/down.pdf")
    Object test() throws Exception {
        //模板名字
        String templateName = "/templates/钢筋安装0001.pdf";
        Map<String,Object> map = new HashMap<>();
        Map<String,String> fields = new HashMap<>();
        fields.put("checkPart","胡从军001");
        fields.put("title","管片混凝土原材料及配合比设计检验批质量验收记录");
        fields.put("gjLong","+0.2，+0.3，-0.2，-0.1，+0.2");
        map.put("fields",fields);
        //获取字节数组
        byte[] bytes = PdfTest.fillTemplate2(templateName,map);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        String templatePath = "/templates/test.xls";
        String fileName = "test";
        Map<String,Object> data = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("dept","开发部"+i);
            map.put("name","zhongmin"+i);
            map.put("age","20");
            List<Map<String,String>> list2 = new ArrayList<>();
            for(int j=0;i<5;i++) {
                Map<String,String> map2 = new HashMap<>();
                map2.put("age","20");
                list2.add(map2);
            }
            map.put("sublist",list2);
            list.add(map);
        }
        data.put("name","胡从军");
        data.put("list",list);

        EsaypoiUtil.export(response,templatePath,fileName,data);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ReturnValue uploadFileTest(@RequestParam("uploadFile") MultipartFile zipFile) {
        return testService.uploadFileTest(zipFile);
    }
    @RequestMapping("/batch1/upload")
    @ResponseBody
    public ReturnValue uploadFilesTest(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("uploadFile");
        testService.uploadFilesTest(files);
        long end = System.currentTimeMillis();
        return new ReturnValue(ReturnCodeAndMsgEnum.SUCCESS,end-start);
    }
    @RequestMapping("/batch2/upload")
    @ResponseBody
    public ReturnValue uploadFiles2Test(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("uploadFile");
        for (MultipartFile file : files) {
            testService.uploadFileTest(file);
        }
        long end = System.currentTimeMillis();
        return new ReturnValue(ReturnCodeAndMsgEnum.SUCCESS,end-start);
    }

//    @RequestMapping("/batch3/upload")
//    @ResponseBody
//    public ReturnValue uploadFiles3Test(HttpServletRequest request) {
//        long start = System.currentTimeMillis();
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("uploadFile");
//        testService.uploadFilesCallableTest(files);
//        long end = System.currentTimeMillis();
//        return new ReturnValue(ReturnCodeAndMsgEnum.SUCCESS,end-start);
//    }


}
