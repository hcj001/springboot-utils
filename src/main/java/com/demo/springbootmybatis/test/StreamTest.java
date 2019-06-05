package com.demo.springbootmybatis.test;

import com.demo.springbootmybatis.entity.Apple;
import com.demo.springbootmybatis.utils.StreamUtil;

import java.io.IOException;

public class StreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "F:\\test\\uploadTest\\test.txt";
        String path2 = "F:\\test\\uploadTest\\test2.txt";
//        StreamUtil.testRead(path);
//        StreamUtil.testRead2(path);
//        StreamUtil.test3(path,path2);

//        StreamUtil.testBuffer(path);
        Apple apple = new Apple("red",200.00);
        StreamUtil.getObject(path,apple);

    }
}
