package com.demo.springbootmybatis.entity;

public enum ReturnCodeAndMsgEnum {
    SUCCESS(0,"OK"),
    No_Data(-1, "no data"),
    SYSTEM_ERROR(10004, "system error");

    private String msg;
    private int code;

    private ReturnCodeAndMsgEnum(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
