package com.demo.springbootmybatis.dto;

import com.demo.springbootmybatis.entity.ReturnCodeAndMsgEnum;
import freemarker.core.ReturnInstruction;

import java.io.Serializable;

public class ReturnValue<T> implements Serializable {

    private static final long serialVersionUID = -1959544190118740608L;
    private int code;
    private String msg;
    private T data;

    public ReturnValue() {
        this.code = 0;
        this.data = null;
        this.msg = "";
    }
    public ReturnValue(int code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ReturnValue(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public ReturnValue(ReturnCodeAndMsgEnum codeAndMsgEnum) {
        this(codeAndMsgEnum.getCode(),codeAndMsgEnum.getMsg(),null);
    }

    public ReturnValue(ReturnCodeAndMsgEnum codeAndMsgEnum,T data) {
        this(codeAndMsgEnum.getCode(),codeAndMsgEnum.getMsg(),data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
