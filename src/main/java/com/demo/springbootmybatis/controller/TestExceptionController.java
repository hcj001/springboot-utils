package com.demo.springbootmybatis.controller;

import com.demo.springbootmybatis.entity.ResponseResultUtil;
import com.demo.springbootmybatis.entity.ResponseResultVo;
import com.demo.springbootmybatis.service.TestExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestExceptionController {

    @Autowired
    TestExceptionService testExceptionService;

    @RequestMapping("ex")
    @ResponseBody
    public ResponseResultVo test() {
        testExceptionService.test();
        return new ResponseResultUtil().success();
    }
}
