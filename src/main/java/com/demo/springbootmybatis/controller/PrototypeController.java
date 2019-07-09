package com.demo.springbootmybatis.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/prototype")
@Controller
@Scope("prototype")
public class PrototypeController {

    private int index = 0;
    private static int staticIndex = 0;

    /**
     * 多例模式下，每访问一次接口，创建一个controller对象。
     * 所以非静态成员变量index一直都为0，而静态成员变量被每个对象共享，访问一次加一次
     *
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "index:"+(index++)+",staticIndex:"+(staticIndex++);
    }

}
