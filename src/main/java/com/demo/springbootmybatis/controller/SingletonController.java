package com.demo.springbootmybatis.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/singleton")
@Controller
@Scope("singleton")
public class SingletonController {

    private int index = 0;
    private static int staticIndex = 0;

    /**
     * 单例模式下：单例模式下，只创建一个controller，每次访问接口都用的同一个对象，
     * 所以非静态成员变量index会一直加1
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "index:"+(index++)+",staticIndex:"+(staticIndex++);
    }

}
