package com.demo.springbootmybatis.controller;

import com.demo.springbootmybatis.entity.ResponseCodeEnum;
import com.demo.springbootmybatis.entity.ResponseResultUtil;
import com.demo.springbootmybatis.entity.ResponseResultVo;
import com.demo.springbootmybatis.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全
 * 局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理未知的异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseResultVo globalExceptionHandler(Exception e) {
        this.logger.error(e.getMessage(),e);
        return new ResponseResultUtil().error(ResponseCodeEnum.ERROR);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseResultVo ServiceExceptionHandler(ServiceException e) {
        this.logger.error(e.getMessage(),e);
        return new ResponseResultUtil().error(e.getCode(),e.getMessage());
    }

}
