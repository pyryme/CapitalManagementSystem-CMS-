package com.xxx.cms.bean;


import com.xxx.cms.constant.ResultConst;

public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(Integer code, String message, Object data) {
//        this指的是这个类里面的成员变量，没有this指的是参数的变量
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(String msg, Object data) {
        return new Result(ResultConst.SUCCESS, msg, data);
    }

    public static Result fail(String msg, Object data) {
        return new Result(ResultConst.FAIL, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}