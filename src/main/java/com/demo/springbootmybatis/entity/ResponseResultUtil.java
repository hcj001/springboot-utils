package com.demo.springbootmybatis.entity;

public class ResponseResultUtil<T> {

    public ResponseResultVo success(int code, String message, T t) {
        return new ResponseResultVo(code, message, t);
    }

    public ResponseResultVo success(T t) {
        int code = ResponseCodeEnum.SUCCESS.getCode();
        String message = ResponseCodeEnum.SUCCESS.getMessage();
        return this.success(code,message,t);
    }

    public ResponseResultVo success() {
        return this.success(null);
    }

    public ResponseResultVo error(ResponseCodeEnum responseCode) {
        return new ResponseResultVo(responseCode.getCode(),responseCode.getMessage(),null);
    }

    public ResponseResultVo error(int code,String message) {
        return new ResponseResultVo(code,message,null);
    }
}
