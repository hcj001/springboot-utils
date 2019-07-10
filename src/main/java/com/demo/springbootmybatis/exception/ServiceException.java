package com.demo.springbootmybatis.exception;

import com.demo.springbootmybatis.entity.ResponseCodeEnum;

public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(ResponseCodeEnum responseCodeEnum) {
        this(responseCodeEnum.getMessage(),responseCodeEnum.getCode());
    }
    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return this.code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
