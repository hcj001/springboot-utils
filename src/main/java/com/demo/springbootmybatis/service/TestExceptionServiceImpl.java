package com.demo.springbootmybatis.service;

import com.demo.springbootmybatis.entity.ResponseCodeEnum;
import com.demo.springbootmybatis.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestExceptionServiceImpl implements TestExceptionService {

    private static final Logger logger = LoggerFactory.getLogger(TestExceptionServiceImpl.class);

    @Override
    public void test() {
        try {
            System.out.println("test...");
            throw new NullPointerException("随便抛出一个异常");
        }catch (Exception e) {
            logger.error("异常测试",e);
            throw new UserException(ResponseCodeEnum.USER_EXCEPTION);
        }
    }
}
