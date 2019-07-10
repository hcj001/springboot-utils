package com.demo.springbootmybatis.exception;

import com.demo.springbootmybatis.entity.ResponseCodeEnum;

public class UserException extends ServiceException {
    public UserException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}
