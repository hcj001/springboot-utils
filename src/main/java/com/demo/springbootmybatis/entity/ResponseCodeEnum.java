package com.demo.springbootmybatis.entity;

import org.omg.PortableInterceptor.USER_EXCEPTION;

/**
 * 响应码配置枚举
 */
public enum ResponseCodeEnum {

    SUCCESS(200,"成功！"),
    ERROR(666, "操作失败"),
    USER_EXCEPTION(999,"用户不存在");
    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public final Integer getCode() {
        return this.code;
    }
    public final String getMessage() {
        return this.message;
    }
}
